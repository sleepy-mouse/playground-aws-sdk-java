package takechiyo.aws.java.v2.lambda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.LambdaClientBuilder;
import takechiyo.aws.java.v2.BaseSdkTest;

class LambdaTest extends BaseSdkTest<LambdaClient, LambdaClientBuilder> {
    @BeforeEach
    public void setUp() {
        builder = LambdaClient.builder();
    }

    @Test
    public void getSettings() {
        run(GetAccountSettings::getSettings);
    }

    @Test
    public void listFunctions() {
        run(ListLambdaFunctions::listFunctions);
    }

    @Test
    public void invokeFunction() {
        run(c -> {
            var functionName = "functionName";
            LambdaInvoke.invokeFunction(c, functionName);
        });
    }

    /**
     * functionName - the name of the Lambda function.
     * filePath - the path to the ZIP or JAR where the code is located.
     * role - the role ARN that has Lambda permissions.
     * handler - the fully qualifed method name (for example, example.Handler::handleRequest).
     */
    @Test
    public void createFunction() {
        run(c -> {
            var functionName = "functionName";
            var filePath = "";
            var role = "";
            var handler = "";
            CreateFunction.createLambdaFunction(c, functionName, filePath, role, handler);
        });
    }

    @Test
    public void deleteFunction() {
        run(c -> {
            var functionName = "functionName";
            DeleteFunction.deleteLambdaFunction(c, functionName);
        });
    }
}
