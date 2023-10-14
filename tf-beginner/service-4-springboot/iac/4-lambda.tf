resource "aws_lambda_function" "example_lambda" {
  function_name = "example_lambda"
  handler       = "your.handler"
  runtime       = "java11"

  s3_bucket = aws_s3_bucket.example_bucket.bucket
  s3_key    = "path/to/your/spring-boot-lambda.jar"

  role = "arn:aws:iam::YOUR_ACCOUNT_ID:role/execution_role"

  memory_size = 512
  timeout     = 10

  environment {
    variables = {
      EXAMPLE_VAR = "example-value"
    }
  }
}

resource "aws_lambda_permission" "apigw_lambda_perm" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.example_lambda.function_name
  principal     = "apigateway.amazonaws.com"
}

resource "aws_lambda_alias" "example_lambda_alias" {
  name             = "exampleAlias"
  description      = "Example alias for our Lambda function"
  function_name    = aws_lambda_function.example_lambda.function_arn
  function_version = "$LATEST"
}
# example Lambda role
resource "aws_iam_role" "lambda_execution_role" {
  name = "lambda_execution_role"

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
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
}

resource "aws_iam_role_policy_attachment" "lambda_logging" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}
