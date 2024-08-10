package com.example.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent


class  Handler : RequestHandler<APIGatewayProxyRequestEvent , APIGatewayProxyResponseEvent> {
    override fun handleRequest(p0: APIGatewayProxyRequestEvent, p1: Context):APIGatewayProxyResponseEvent {
       p0.headers.forEach { (t, u) ->
           println("$t -> $u")
       }
        val apiGatewayProxyResponseEvent = APIGatewayProxyResponseEvent()
        apiGatewayProxyResponseEvent.statusCode=200
        apiGatewayProxyResponseEvent.body="I am here"
        return apiGatewayProxyResponseEvent
    }
}