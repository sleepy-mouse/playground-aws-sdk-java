package takechiyo.aws.java.v2.dynamodb;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;

import java.util.Collections;
import java.util.List;

@Slf4j
public class DynamoDbSample {
    public static List<String> listTables(DynamoDbClient ddb) {
        try {
            var request = ListTablesRequest.builder().build();
            var response = ddb.listTables(request);
            var tableNames = response.tableNames();
            if (tableNames.size() > 0) {
                return tableNames;
            } else {
                log.info("No tables found!");
            }
        } catch (DynamoDbException e) {
            log.error("", e);
        }
        return Collections.emptyList();
    }
}
