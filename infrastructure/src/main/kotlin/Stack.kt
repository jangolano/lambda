import software.amazon.awscdk.CfnOutput
import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.aws_apigatewayv2_integrations.HttpLambdaIntegration
import software.amazon.awscdk.services.apigatewayv2.AddRoutesOptions
import software.amazon.awscdk.services.apigatewayv2.HttpApi
import software.amazon.awscdk.services.apigatewayv2.HttpMethod
import software.amazon.awscdk.services.apigatewayv2.HttpStage
import software.amazon.awscdk.services.lambda.*
import software.amazon.awscdk.services.lambda.CfnFunction.SnapStartProperty
import software.amazon.awscdk.services.lambda.Function
import software.constructs.Construct
import kotlin.String

class Stack(scope: Construct, id: String, props: StackProps) : Stack(scope, id, props) {

    init {

        //Define Lambda Functions
        val lambda = Function.Builder.create(this, "first-lambda").functionName("first-lambda").code(Code.fromAsset("../lambdas/build/libs/lambdas-all.jar"))
            .handler("com.example.lambda.Handler").runtime(Runtime.JAVA_17).tracing(Tracing.ACTIVE)
            .build()
        val myFunctionIntegration = HttpLambdaIntegration("first-lambda-integration", lambda)

        val lambda2 = Function.Builder.create(this, "second-lambda").functionName("second-lambda").code(Code.fromAsset("../lambdas/build/libs/lambdas-all.jar"))
            .handler("com.example.lambda.Handler").runtime(Runtime.JAVA_17).tracing(Tracing.ACTIVE)
            .build()

        //Define a lambda version
        val lambda2Version = Version.Builder.create(this, "second-lambda-version").lambda(lambda2).build()
        val functionAlias = Alias.Builder.create(this, "second-lambda-alias").version(lambda2Version).aliasName("snapsnart").build()
        val myFunctionIntegration2 = HttpLambdaIntegration("second-lambda-integration", functionAlias)
        SnapStartProperty.builder().applyOn("PublishedVersions").build()
        //Define API
        val httpApi = HttpApi(this, "my-simple-api")

        //Add Routes
        httpApi.addRoutes(
            AddRoutesOptions.builder()
            .path("/hello")
            .methods(listOf(HttpMethod.GET))
            .integration(myFunctionIntegration)
            .build())
        httpApi.addRoutes(
                   AddRoutesOptions.builder()
                       .path("/hello2")
                       .methods(listOf(HttpMethod.GET))
                       .integration(myFunctionIntegration2)
                       .build())
        //Add Stage
        val apiStage = HttpStage.Builder.create(this, "my-simple-api-stage")
            .httpApi(httpApi)
            .autoDeploy(true)
            .stageName("api")
            .build()
        //Show outputs
        CfnOutput.Builder.create(this, "first-endpoint").value("${apiStage.url}/hello").build()
        CfnOutput.Builder.create(this, "second-endpoint").value("${apiStage.url}/hello2").build()
    }
}