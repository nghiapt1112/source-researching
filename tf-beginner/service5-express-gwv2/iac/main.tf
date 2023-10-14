provider "aws" {
  region = "ap-southeast-1"
}

# S3 bucket to store Lambda function code
resource "aws_s3_bucket" "lambda_code_bucket" {
  bucket = "nathan-tf-lambda-ex-5"
#  acl    = "private"
}

# AWS Lambda function for service-1
resource "aws_lambda_function" "service_1" {
  function_name = "service-1xx1"
  handler       = "index.handler" # Assuming your entry point is an 'index.js' file with a 'handler' export
  runtime       = "nodejs18.x"

  s3_bucket = aws_s3_bucket.lambda_code_bucket.bucket
  s3_key    = "lambda.zip"

  # IAM role for the Lambda (basic execution role here)
  role = aws_iam_role.lambda_exec.arn
}

resource "aws_iam_role" "lambda_exec" {
  name = "service-1-lambda-execution-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = "sts:AssumeRole",
        Principal = {
          Service = "lambda.amazonaws.com"
        },
        Effect = "Allow",
        Sid    = ""
      }
    ]
  })
}

resource "aws_iam_role_policy_attachment" "lambda_s3_access" {
  role       = aws_iam_role.lambda_exec.name
  policy_arn = "arn:aws:iam::aws:policy/AWSLambda_FullAccess" # For simplicity, granting full Lambda access. You should scope this down in a real-world scenario.
}

# API Gateway V2
resource "aws_apigatewayv2_api" "api" {
  name          = "Service1API"
  protocol_type = "HTTP"
}

resource "aws_apigatewayv2_integration" "lambda_integration" {
  api_id           = aws_apigatewayv2_api.api.id
  integration_type = "AWS_PROXY"

  integration_uri = aws_lambda_function.service_1.invoke_arn
}

resource "aws_apigatewayv2_route" "default_route" {
  api_id    = aws_apigatewayv2_api.api.id
  route_key = "$default"
  target    = "integrations/${aws_apigatewayv2_integration.lambda_integration.id}"
}

resource "aws_lambda_permission" "apigw" {
  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.service_1.function_name
  principal     = "apigateway.amazonaws.com"

  source_arn = "${aws_apigatewayv2_api.api.execution_arn}/*/*"
}
#
#resource "aws_apigatewayv2_deployment" "deployment" {
#  api_id = aws_apigatewayv2_api.api.id
#
#  depends_on = [
#    aws_apigatewayv2_route.default_route
#  ]
#}

resource "aws_apigatewayv2_stage" "stage" {
  api_id      = aws_apigatewayv2_api.api.id
  name        = "test"
#  deployment_id = aws_apigatewayv2_deployment.deployment.id
  auto_deploy = true

  access_log_settings {
    destination_arn = aws_cloudwatch_log_group.apigw_logs.arn
    format           = "$context.identity.sourceIp - - [$context.requestTime] \"$context.httpMethod $context.routeKey $context.protocol\" $context.status $context.responseLength $context.requestId"
  }
}

resource "aws_cloudwatch_log_group" "apigw_logs" {
  name = "/aws/apigateway/service-1x1"
}
