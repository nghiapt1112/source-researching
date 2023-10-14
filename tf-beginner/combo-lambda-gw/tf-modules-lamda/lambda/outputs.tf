output "cloudwatch_log_group" {
  value = aws_cloudwatch_log_group.helloworld-lambda
}

output "lambda_function" {
  value = aws_lambda_function.helloworld-lambda
}

output "iam_role" {
  value = aws_iam_role.lambda_exec
}

output "iam_policy_log" {
  value = aws_iam_policy.log
}

output "lambda_arn" {
  value = aws_lambda_function.helloworld-lambda.arn
}

output "api_gateway_endpoint" {
  value = data.aws_apigatewayv2_api.lambda.api_endpoint
}
