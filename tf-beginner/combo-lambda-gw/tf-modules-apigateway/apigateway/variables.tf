variable "integration_type" {
type = string
default = "AWS_PROXY"
description = "integration name for apigateway2"
}
variable "integration_method" {
type = string

description = "name of integration method"
}
variable "loggroup_retention" {
type = number
default = 30
description = "time for lamda log retention"
}
variable "routekey" {
    type = string
    default =  "GET /hello"
    description = "route key for api"
}
variable "apigatewayid" {
    type = string
    description = "id of apigateway"
}
variable "lambda_arn" {
    type = string
    description = "arn fecteched from lamda"
}
variable "region" {
    type = string
 description = "region where the apigateway integeration has to be configured"
}
