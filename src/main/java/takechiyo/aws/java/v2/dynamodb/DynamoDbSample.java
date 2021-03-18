package takechiyo.aws.java.v2.dynamodb;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ListTablesRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.ReturnConsumedCapacity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public static Optional<QueryResponse> queryTable(DynamoDbClient ddb,
                                                     String tableName,
                                                     String partitionKeyName,
                                                     String partitionKeyVal,
                                                     String partitionAlias) {
        // Set up an alias for the partition key name in case it's a reserved word
        var attrNameAlias = Map.of(partitionAlias, partitionKeyName);
        // Set up mapping of the partition name with the value
        var attrValues = Map.of(":" + partitionKeyName, AttributeValue.builder().s(partitionKeyVal).build());
        var queryReq = QueryRequest.builder()
                .tableName(tableName)
                .returnConsumedCapacity(ReturnConsumedCapacity.INDEXES)
                .keyConditionExpression(partitionAlias + " = :" + partitionKeyName)
                .expressionAttributeNames(attrNameAlias)
                .expressionAttributeValues(attrValues)
                .build();
        try {
            return Optional.of(ddb.query(queryReq));
        } catch (DynamoDbException e) {
            log.error("", e);
            return Optional.empty();
        }
    }
}
