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


resource "aws_s3_bucket" "lambda_bucket" {
  bucket = "nathan-tf-lambda-ex-2"
#  acl    = "private"
}


data "archive_file" "lambda_hello_world" {
  type = "zip"
  source_dir  = "../source-code"
  output_path = "../lambda-express-v2.zip"
}

resource "aws_s3_object" "lambda_hello_world" {
  bucket = aws_s3_bucket.lambda_bucket.id

#  s3_bucket = "nathan-tf-lambda-ex"
#  s3_key    = "v${var.app_version}/example.zip"

  key    = "lambda-express-v2.zip"
  source = data.archive_file.lambda_hello_world.output_path

  etag = filemd5(data.archive_file.lambda_hello_world.output_path)
}
