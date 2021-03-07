// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[DeleteFunction.java demonstrates how to delete an AWS Lambda function by using the LambdaClient object]
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

// snippet-start:[lambda.java2.DeleteFunction.complete]
package takechiyo.aws.java.v2.lambda;

// snippet-start:[lambda.java2.delete.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.DeleteFunctionRequest;
import software.amazon.awssdk.services.lambda.model.LambdaException;
// snippet-end:[lambda.java2.delete.import]

@Slf4j
public class DeleteFunction {
    // snippet-start:[lambda.java2.delete.main]
    public static void deleteLambdaFunction(LambdaClient awsLambda, String functionName) {
        try {
            //Setup an DeleteFunctionRequest
            var request = DeleteFunctionRequest.builder()
                    .functionName(functionName)
                    .build();
            awsLambda.deleteFunction(request);
            log.info("The " + functionName + " function was deleted");
        } catch (LambdaException e) {
            log.error("", e);
        }
        // snippet-end:[lambda.java2.delete.main]
    }
}
// snippet-end:[lambda.java2.DeleteFunction.complete]
