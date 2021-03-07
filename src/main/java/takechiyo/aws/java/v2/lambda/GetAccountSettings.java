// snippet-sourcedescription:[GetAccountSettings.java demonstrates how to obtain information about your account.]
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

// snippet-start:[lambda.java2.account.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.LambdaException;
// snippet-end:[lambda.java2.account.import]

@Slf4j
public class GetAccountSettings {
    // snippet-start:[lambda.java2.account.main]
    public static void getSettings(LambdaClient awsLambda) {
        try {
            var response = awsLambda.getAccountSettings();
            log.info("Total code size for your account is " + response.accountLimit().totalCodeSize() + " bytes");
        } catch (LambdaException e) {
            log.error("", e);
        }
    }
    // snippet-end:[lambda.java2.account.main]
}
