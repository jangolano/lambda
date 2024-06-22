import software.amazon.awscdk.Stack
import software.amazon.awscdk.StackProps
import software.amazon.awscdk.services.lambda.Code
import software.amazon.awscdk.services.lambda.Function
import software.amazon.awscdk.services.lambda.Runtime
import software.constructs.Construct

class Stack(scope: Construct, id: String, props: StackProps) : Stack(scope, id, props) {

    init {
        Function.Builder.create(this, "my-simple-lambda").code(Code.fromAsset("../lambdas/build/libs/lambdas-all.jar"))
            .handler("cdk.example.lambda.Handler").runtime(Runtime.JAVA_21)
            .build()
    }
}