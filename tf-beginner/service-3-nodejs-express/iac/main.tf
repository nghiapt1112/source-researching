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

resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "nathan-tf-nodejs-express-gw1"
  #  acl    = "private"
}

resource "aws_api_gateway_rest_api" "nathan-apigw" {
  name        = "ServerlessExample"
  description = "Terraform Serverless Application Example"
}


resource "aws_api_gateway_method" "proxy_root" {
  rest_api_id   = "${aws_api_gateway_rest_api.nathan-apigw.id}"
  resource_id   = "${aws_api_gateway_rest_api.nathan-apigw.root_resource_id}"
  http_method   = "ANY"
  authorization = "NONE"
}

#resource "aws_api_gateway_integration" "lambda_root" {
#  rest_api_id = "${aws_api_gateway_rest_api.nathan-apigw.id}"
#  resource_id = "${aws_api_gateway_method.proxy_root.resource_id}"
#  http_method = "${aws_api_gateway_method.proxy_root.http_method}"
#
#  integration_http_method = "POST"
#  type                    = "AWS_PROXY"
#  uri                     = "${aws_lambda_function.lambda-service1.invoke_arn}"
#}

resource "aws_api_gateway_deployment" "example" {
  depends_on = [
    "aws_api_gateway_integration.lambda-service1",
#    "aws_api_gateway_integration.lambda-service2",
#    "aws_api_gateway_integration.lambda_root", # cai nay dang config duplicate vs service1
  ]

  rest_api_id = "${aws_api_gateway_rest_api.nathan-apigw.id}"
  stage_name  = "test"
}
variable "app_version" {
}