package org.example

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.example.lambda.Handler
import org.junit.jupiter.api.Test


class HandlerTest {

   @Test
   fun `Make sure that I can invoke the handler`() {

       val handler = Handler()
       val apiGatewayProxyRequestEvent = APIGatewayProxyRequestEvent()
       apiGatewayProxyRequestEvent.headers = mapOf<String, String>("Content-Type" to "text/plain")
       apiGatewayProxyRequestEvent.body
       handler.handleRequest(apiGatewayProxyRequestEvent, null)

   }
}