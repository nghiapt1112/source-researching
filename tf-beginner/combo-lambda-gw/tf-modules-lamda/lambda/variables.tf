variable "handler" {
    description = "lambda function entry point"
    type =  string
}

variable "runtime" {
    description = "lambda function runtime env"
    type = string
}

variable "bucket_name" {
    description = "Provide the details of S3 Bucket where the lambda fuction code is stored"
    type = string
}

variable "file_name" {
    description = "Provide name of the file in archived format (zip or jar) along with the extention"
    type = string
}

variable "function_name" {
    description = "Provide name to the lambda function"
    type = string
}

variable "log_retention_in_days" {
    description = "Provide the number of days for log to be available in cloudwatch for this lambda function"
    type = number
    default = 30
}

variable "apigatewayid" {
    type = string
    description = "id of apigateway"
}

variable "region" {
    type = string
    description = "name of region"
}

variable "tags" {
    type = map
}

