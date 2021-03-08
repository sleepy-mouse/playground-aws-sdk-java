// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[LambdaInvoke.java demonstrates how to invoke an AWS Lambda function by using the LambdaClient object]
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

// snippet-start:[lambda.java2.LambdaInvoke.complete]
package takechiyo.aws.java.v2.lambda;

// snippet-start:[lambda.java2.invoke.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvocationType;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.LambdaException;
// snippet-end:[lambda.java2.invoke.import]

@Slf4j
public class LambdaInvoke {
    /*
     Function names appear as arn:aws:lambda:us-west-2:335556666777:function:HelloFunction
     you can retrieve the value by looking at the function in the AWS Console
   */

    // snippet-start:[lambda.java2.invoke.main]
    public static void invokeFunction(LambdaClient awsLambda, String functionName) {
        try {
            //Need a SdkBytes instance for the payload
            var payload = SdkBytes.fromUtf8String("{\n" +
                    " \"Hello \": \"Paris\",\n" +
                    " \"countryCode\": \"FR\"\n" +
                    "}");

            //Setup an InvokeRequest
            var request = InvokeRequest.builder()
                    .functionName(functionName)
                    .payload(payload)
                    .invocationType(InvocationType.DRY_RUN)
                    .build();

            //Invoke the Lambda function
            var res = awsLambda.invoke(request);
            var value = res.payload().asUtf8String();
            log.info(value);
        } catch (LambdaException e) {
            log.error("", e);
        }
        // snippet-end:[lambda.java2.invoke.main]
    }
}
// snippet-end:[lambda.java2.LambdaInvoke.complete]