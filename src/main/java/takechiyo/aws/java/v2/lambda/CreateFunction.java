// snippet-sourcedescription:[CreateFunction.java demonstrates how to create an AWS Lambda function by using the LambdaClient object.]
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

package takechiyo.aws.java.v2.lambda;

// snippet-start:[lambda.java2.create.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.CreateFunctionRequest;
import software.amazon.awssdk.services.lambda.model.FunctionCode;
import software.amazon.awssdk.services.lambda.model.LambdaException;
import software.amazon.awssdk.services.lambda.model.Runtime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// snippet-end:[lambda.java2.create.import]

/**
 * This code example requires a ZIP or JAR that represents the code of the Lambda function.
 * If you do not have a ZIP or JAR, please refer to the following document:
 * <p>
 * https://github.com/aws-doc-sdk-examples/tree/master/javav2/usecases/creating_workflows_stepfunctions
 */
@Slf4j
public class CreateFunction {
    // snippet-start:[lambda.java2.create.main]
    public static void createLambdaFunction(LambdaClient awsLambda,
                                            String functionName,
                                            String filePath,
                                            String role,
                                            String handler) {
        try {
            var is = new FileInputStream(filePath);
            var fileToUpload = SdkBytes.fromInputStream(is);
            var code = FunctionCode.builder()
                    .zipFile(fileToUpload)
                    .build();
            var functionRequest = CreateFunctionRequest.builder()
                    .functionName(functionName)
                    .description("Created by the Lambda Java API")
                    .code(code)
                    .handler(handler)
                    .runtime(Runtime.JAVA8)
                    .role(role)
                    .build();
            var functionResponse = awsLambda.createFunction(functionRequest);
            log.info("The function ARN is " + functionResponse.functionArn());
        } catch (LambdaException | FileNotFoundException e) {
            log.error("", e);
        }
    }
    // snippet-end:[lambda.java2.create.main]
}
