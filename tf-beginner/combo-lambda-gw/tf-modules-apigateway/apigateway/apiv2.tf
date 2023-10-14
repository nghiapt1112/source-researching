resource "aws_apigatewayv2_integration" "hello_world" {
  api_id = var.apigatewayid

  integration_uri    = var.lambda_arn
  integration_type   = var.integration_type
  integration_method = var.integration_method
  
}

resource "aws_apigatewayv2_route" "hello_world" {
  api_id = var.apigatewayid
 route_key = var.routekey
  target    = "integrations/${aws_apigatewayv2_integration.hello_world.id}"
  authorization_type = "NONE"
}

     
     
