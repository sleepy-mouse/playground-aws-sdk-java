package takechiyo.aws.java.v2.s3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import takechiyo.aws.java.v2.BaseSdkTest;

class S3Test extends BaseSdkTest<S3Client, S3ClientBuilder> {
    @BeforeEach
    @Override
    public void setUp() {
        builder = S3Client.builder();
    }

    @Test
    public void testListBucketObjects() {
        run(c -> {
            var bucketName = "bucketName";
            ListObjects.listBucketObjects(c, bucketName);
        });
    }
}
