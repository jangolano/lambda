import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.aws_apigatewayv2_integrations.HttpLambdaIntegration
import software.amazon.awscdk.services.apigatewayv2.AddRoutesOptions
import software.amazon.awscdk.services.apigatewayv2.HttpApi
import software.amazon.awscdk.services.apigatewayv2.HttpMethod
import software.amazon.awscdk.services.apigatewayv2.HttpStage
import software.amazon.awscdk.services.lambda.Code
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.Runtime
import software.amazon.awscdk.services.lambda.SnapStartConf
import software.constructs.Construct

class Stack(scope: Construct, id: String, props: StackProps) : Stack(scope, id, props) {

    init {
        val lambda = Function.Builder.create(this, "josh").code(Code.fromAsset("../lambdas/build/libs/lambdas-all.jar"))
            .handler("com.example.lambda.Handler").runtime(Runtime.JAVA_21).snapStart(SnapStartConf.ON_PUBLISHED_VERSIONS)
            .build()

        val httpApi = HttpApi(this, "joshs-api")
        val myFunctionIntegration = HttpLambdaIntegration("Josh-Lambda-Integration", lambda)

        httpApi.addRoutes(
            AddRoutesOptions.builder()
            .path("/hello")
            .methods(listOf(HttpMethod.GET))
            .integration(myFunctionIntegration)
            .build())

        HttpStage.Builder.create(this, "josh-stage")
            .httpApi(httpApi)
            .autoDeploy(true)
            .stageName("josh")
            .build()

    }
}