package takechiyo.aws.java.v2.s3;

// snippet-start:[s3.java2.logging.complete]
// snippet-start:[s3.java2.logging.import]

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
// snippet-end:[s3.java2.logging.import]

// snippet-start:[s3.java2.logging.main]
public class S3Log {
    private static final Logger logger = LogManager.getLogger(S3Log.class);

    public static void main(String[] args) {
        System.out.println("testing logging setup for " + S3Log.class);

        Region region = Region.US_WEST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().forEach(x -> System.out.println(x.name()));

        logger.info("logging level info");
        logger.debug("logging debug stuff");
        logger.warn("logging warning");
        logger.error("logging error");
        logger.fatal("logging fatal");
    }
}
// snippet-end:[s3.java2.logging.main]
// snippet-end:[s3.java2.logging.complete]
