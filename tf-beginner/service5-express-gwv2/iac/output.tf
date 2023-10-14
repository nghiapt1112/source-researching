output "function_name" {
  description = "Name of the Lambda function."

  value = aws_lambda_function.service_1.function_name
}
output "base_url" {
  description = "Base URL for API Gateway stage."

  value = aws_apigatewayv2_stage.stage.invoke_url
}
