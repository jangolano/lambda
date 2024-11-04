import software.amazon.awscdk.CfnOutput
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.aws_apigatewayv2_integrations.HttpLambdaIntegration
import software.amazon.awscdk.services.apigatewayv2.AddRoutesOptions
import software.amazon.awscdk.services.apigatewayv2.HttpApi
import software.amazon.awscdk.services.apigatewayv2.HttpMethod
import software.amazon.awscdk.services.apigatewayv2.HttpStage
import software.amazon.awscdk.services.lambda.*
import software.amazon.awscdk.services.lambda.Function
import software.constructs.Construct
import kotlin.String

class Stack(scope: Construct, id: String, props: StackProps) : Stack(scope, id, props) {

    init {

        //Define Lambda Function
        val lambda = Function.Builder.create(this, "first-lambda").functionName("first-lambda").code(Code.fromAsset("../lambdas/build/libs/lambdas-all.jar"))
            .handler("com.example.lambda.Handler").runtime(Runtime.JAVA_21).tracing(Tracing.ACTIVE)
            .build()
        val myFunctionIntegration = HttpLambdaIntegration("first-lambda-integration", lambda)


        //Define an API Gateway
        val httpApi = HttpApi(this, "my-simple-api")

        //Add a route
        httpApi.addRoutes(
            AddRoutesOptions.builder()
            .path("/hello")
            .methods(listOf(HttpMethod.GET))
            .integration(myFunctionIntegration)
            .build())

        //Create a stage
        val apiStage = HttpStage.Builder.create(this, "my-simple-api-stage")
            .httpApi(httpApi)
            .autoDeploy(true)
            .stageName("api")
            .build()

        //Generate the output
        CfnOutput.Builder.create(this, "first-endpoint").value("${apiStage.url}/hello").build()

    }
}