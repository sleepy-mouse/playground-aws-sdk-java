package takechiyo.aws.java.v2;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;
import software.amazon.awssdk.core.SdkClient;
import software.amazon.awssdk.regions.Region;

import java.util.function.Consumer;

public abstract class BaseSdkTest<C extends SdkClient, B extends AwsClientBuilder<B, C>> {
    protected static final Region REGION = Region.US_EAST_1;

    protected AwsClientBuilder<B, C> builder;

    protected C getClient() {
        return builder
                .region(REGION)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    protected void run(Consumer<C> testCaseBody) {
        try (var client = getClient()) {
            testCaseBody.accept(client);
        }
    }
}
