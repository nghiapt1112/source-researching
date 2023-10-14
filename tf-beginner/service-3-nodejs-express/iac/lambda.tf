terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "5.17.0"
    }
  }
}

provider "aws" {
  region = "ap-southeast-1"
}
#
data "archive_file" "lambda_hello_world" {
  type = "zip"

  source_dir  = "../source-code"
  output_path = "example.zip"
}

resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "nathan-tf-nodejs-express-gw1"
  #  acl    = "private"
}

resource "aws_s3_object" "lambda_hello_world" {
  bucket = aws_s3_bucket.lambda_bucket.id

  key    = "v${var.app_version}/example.zip"
  source = data.archive_file.lambda_hello_world.output_path
  etag = filemd5(data.archive_file.lambda_hello_world.output_path)
}


resource "aws_lambda_function" "example" {
  function_name = "nodejs-express-gw1"

  s3_bucket = aws_s3_bucket.lambda_bucket.id
  s3_key    = aws_s3_object.lambda_hello_world.id

  # "main" is the filename within the zip file (main.js) and "handler"
  # is the name of the property under which the handler function was
  # exported in that file.
  handler = "index.handler"
  runtime = "nodejs18.x"
#  memory_size = 1024
  timeout = 300

#  The source_code_hash attribute will change whenever you update the code contained in the archive, which lets Lambda know that there is a new version of your code available
  source_code_hash = data.archive_file.lambda_hello_world.output_base64sha256

  role = aws_iam_role.lambda_exec.arn
}

resource "aws_cloudwatch_log_group" "hello_world" {
  name = "/aws/lambda/${aws_lambda_function.example.function_name}"
  retention_in_days = 14
}

data "aws_iam_policy_document" "ts_lambda_policy" {
  statement {
    actions = [
      "logs:CreateLogStream",
      "logs:PutLogEvents"
    ]
    resources = [
      aws_cloudwatch_log_group.hello_world.arn,
      "${aws_cloudwatch_log_group.hello_world.arn}:*"
    ]
  }
}

resource "aws_iam_role_policy" "ts_lambda_role_policy" {
  policy = data.aws_iam_policy_document.ts_lambda_policy.json
  role   = aws_iam_role.lambda_exec.id
  name   = "my-lambda-policy"
}

# IAM role which dictates what other AWS services the Lambda function
# may access.
resource "aws_iam_role" "lambda_exec" {
  name = "serverless_example_lambda"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_role_policy_attachment" "lambda_policy" {
  role       = aws_iam_role.lambda_exec.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}


resource "aws_lambda_permission" "apigw" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = "${aws_lambda_function.example.function_name}"
  principal     = "apigateway.amazonaws.com"

  # The /*/* portion grants access from any method on any resource
  # within the API Gateway "REST API".
  source_arn = "${aws_api_gateway_rest_api.example.execution_arn}/*/*"
}

variable "app_version" {
}