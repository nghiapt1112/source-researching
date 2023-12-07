#resource "aws_api_gateway_resource" "proxy-service2" {
#  rest_api_id = "${aws_api_gateway_rest_api.nathan-apigw.id}"
#  parent_id   = "${aws_api_gateway_rest_api.nathan-apigw.root_resource_id}"
#  #  path_part   = "{proxy+}" #activates proxy behavior, which means that this resource will match any request path
#  path_part   = "service2" #activates proxy behavior, which means that this resource will match any request path
#}
#
#resource "aws_api_gateway_method" "proxy-method-service2" {
#  rest_api_id   = "${aws_api_gateway_rest_api.nathan-apigw.id}"
#  resource_id   = "${aws_api_gateway_resource.proxy-service2.id}"
#  http_method   = "ANY"
#  authorization = "NONE"
#}
#
#resource "aws_api_gateway_integration" "lambda-service2" {
#  rest_api_id = "${aws_api_gateway_rest_api.nathan-apigw.id}"
#  resource_id = "${aws_api_gateway_method.proxy-method-service2.resource_id}"
#  http_method = "${aws_api_gateway_method.proxy-method-service2.http_method}"
#
#  integration_http_method = "POST"
#  #The AWS_PROXY integration type causes API gateway to call into the API of another AWS service.
#  #In this case, it will call the AWS Lambda API to create an "invocation" of the Lambda function.
#  type                    = "AWS_PROXY"
#  uri                     = "${aws_lambda_function.lambda-service2.invoke_arn}"
#}
