data "aws_apigatewayv2_api" "lambda" { 
  api_id        = var.apigatewayid
}

resource "aws_lambda_function" "helloworld-lambda" {
  s3_bucket     = var.bucket_name
  s3_key        = var.file_name
  function_name = var.function_name
  role          = aws_iam_role.lambda_exec.arn
  handler       = var.handler
  runtime       = var.runtime
}

resource "aws_cloudwatch_log_group" "helloworld-lambda" {
  name              = "/aws/lambda/${var.function_name}"
  retention_in_days = var.log_retention_in_days
}

resource "aws_lambda_permission" "lambda_permission" {
  statement_id  = "AllowExecutionFromAPIGateway"
  action        = "lambda:InvokeFunction"
  function_name = var.function_name
  principal     = "apigateway.amazonaws.com"
  source_arn    = data.aws_apigatewayv2_api.lambda.arn
}
