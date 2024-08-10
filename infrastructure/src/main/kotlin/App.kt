import software.amazon.awscdk.App
import software.amazon.awscdk.StackProps

fun main() {
    val app = App()
    Stack(app, "jangolano", StackProps.builder().build())
    app.synth()
}