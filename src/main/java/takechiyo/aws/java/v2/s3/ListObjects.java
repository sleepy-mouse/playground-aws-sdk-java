//snippet-sourcedescription:[ListObjects.java demonstrates how to list objects located in a given Amazon S3 bucket.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon S3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[2/6/2020]
//snippet-sourceauthor:[scmacdon-aws]

/**
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * <p>
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 * <p>
 * http://aws.amazon.com/apache2.0/
 * <p>
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package takechiyo.aws.java.v2.s3;

// snippet-start:[s3.java2.list_objects.import]

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
// snippet-end:[s3.java2.list_objects.import]

@Slf4j
public class ListObjects {
    // snippet-start:[s3.java2.list_objects.main]
    public static void listBucketObjects(S3Client s3, String bucketName) {
        try {
            var listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();
            var res = s3.listObjects(listObjects);
            var objects = res.contents();
            for (var object : objects) {
                log.info("\n The name of the key is " + object.key());
                log.info("\n The object is " + calKb(object.size()) + " KBs");
                log.info("\n The owner is " + object.owner());
            }
        } catch (S3Exception e) {
            log.error("", e);
        }
    }

    //convert bytes to kbs
    private static long calKb(Long val) {
        return val / 1024;
    }
    // snippet-end:[s3.java2.list_objects.main]
}
