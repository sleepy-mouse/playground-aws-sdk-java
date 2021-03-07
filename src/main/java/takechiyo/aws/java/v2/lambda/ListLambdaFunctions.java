// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-keyword:[AWS Lambda]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[05/11/2020]
// snippet-sourceauthor:[AWS-scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

// snippet-start:[lambda.java2.ListLambdaFunctions.complete]
package takechiyo.aws.java.v2.lambda;

// snippet-start:[lambda.java2.list.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.LambdaException;
// snippet-end:[lambda.java2.list.import]

@Slf4j
public class ListLambdaFunctions {
    // snippet-start:[lambda.java2.list.main]
    public static void listFunctions(LambdaClient awsLambda) {
        try {
            var functionResult = awsLambda.listFunctions();
            var list = functionResult.functions();
            for (var config : list) {
                log.info("The function name is " + config.functionName());
            }
        } catch (LambdaException e) {
            log.error("", e);
        }
        // snippet-end:[lambda.java2.list.main]
    }
}
// snippet-end:[lambda.java2.ListLambdaFunctions.complete]