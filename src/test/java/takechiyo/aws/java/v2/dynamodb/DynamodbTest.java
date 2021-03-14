package takechiyo.aws.java.v2.dynamodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;
import takechiyo.aws.java.v2.BaseSdkTest;

@Slf4j
public class DynamodbTest extends BaseSdkTest<DynamoDbClient, DynamoDbClientBuilder> {
    @BeforeEach
    @Override
    public void setUp() {
        builder = DynamoDbClient.builder();
    }

    @Test
    public void listTables() {
        run(c -> {
            var tableNames = DynamoDbSample.listTables(c);
            tableNames.forEach(log::info);
        });
    }
}
