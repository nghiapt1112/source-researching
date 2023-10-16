#
#data "archive_file" "lambda_hello_world-service2" {
#  type = "zip"
#
#  source_dir  = "../source-code-2"
#  output_path = "example-service2.zip"
#}
#
#resource "aws_s3_object" "lambda_hello_world-service2" {
#  bucket = aws_s3_bucket.lambda_bucket.id
#
#  key    = "v${var.app_version}/service2/artifact.zip"
#  source = data.archive_file.lambda_hello_world-service2.output_path
#  etag = filemd5(data.archive_file.lambda_hello_world.output_path)
#}
#
#
#resource "aws_lambda_function" "lambda-service2" {
#  function_name = "nodejs-express-gw1-service2"
#
#  s3_bucket = aws_s3_bucket.lambda_bucket.id
#  s3_key    = aws_s3_object.lambda_hello_world-service2.id
#
#  # "main" is the filename within the zip file (main.js) and "handler"
#  # is the name of the property under which the handler function was
#  # exported in that file.
#  handler = "index.handler"
#  runtime = "nodejs18.x"
##  memory_size = 1024
#  timeout = 300
#
##  The source_code_hash attribute will change whenever you update the code contained in the archive, which lets Lambda know that there is a new version of your code available
#  source_code_hash = data.archive_file.lambda_hello_world-service2.output_base64sha256
##  source_code_hash = filebase64sha256("path/to/your/lambda/code.zip")
#
#  role = aws_iam_role.lambda_exec.arn
#}
#
#resource "aws_cloudwatch_log_group" "hello_world-service2" {
#  name = "/aws/lambda/${aws_lambda_function.lambda-service2.function_name}"
#  retention_in_days = 14
#}
#
#data "aws_iam_policy_document" "ts_lambda_policy-service2" {
#  statement {
#    actions = [
#      "logs:CreateLogStream",
#      "logs:PutLogEvents"
#    ]
#    resources = [
#      aws_cloudwatch_log_group.hello_world-service2.arn,
#      "${aws_cloudwatch_log_group.hello_world-service2.arn}:*"
#    ]
#  }
#}
#
#resource "aws_iam_role_policy" "ts_lambda_role_policy-service2" {
#  policy = data.aws_iam_policy_document.ts_lambda_policy-service2.json
#  role   = aws_iam_role.lambda_exec.id
#  name   = "my-lambda-policy"
#}
#
#
##resource "aws_iam_role_policy_attachment" "lambda_policy" {
##  role       = aws_iam_role.lambda_exec.name
##  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
##}
#
#
#resource "aws_lambda_permission" "apigw-service2" {
#  statement_id  = "AllowAPIGatewayInvoke"
#  action        = "lambda:InvokeFunction"
#  function_name = "${aws_lambda_function.lambda-service2.function_name}"
#  principal     = "apigateway.amazonaws.com"
#
#  # The /*/* portion grants access from any method on any resource
#  # within the API Gateway "REST API".
#  source_arn = "${aws_api_gateway_rest_api.nathan-apigw.execution_arn}/*/*"
##  source_arn = "${aws_api_gateway_resource.proxy-service1.id}/*/*"
#}
