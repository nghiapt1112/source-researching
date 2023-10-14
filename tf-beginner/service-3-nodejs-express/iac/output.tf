output "function_name" {
  description = "Name of the Lambda function."

  value = aws_lambda_function.express_lambda.function_name
}
output "api_gateway_url" {
  description = "The URL of the deployed API Gateway"
#  value       = "https://${aws_api_gateway_rest_api.my_api.id}.execute-api.${var.region}.amazonaws.com/${var.stage_name}/"
  value = "${aws_api_gateway_deployment.example.invoke_url}"

}
