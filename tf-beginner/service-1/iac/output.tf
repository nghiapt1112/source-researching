output "base_url" {
  value = "${aws_api_gateway_deployment.example.invoke_url}"
}

output "function_name" {
  description = "Name of the Lambda function."
  value = aws_lambda_function.example.function_name
}