package com.example.s3example;

import static com.example.s3example.aws.AWSKeys.AWS_ACCESS_KEY;
import static com.example.s3example.aws.AWSKeys.AWS_SECRET_KEY;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class AmazonClient {

    private AmazonS3Client s3Client;
    private TransferUtility transferUtility;
    private static AmazonClient sManager;

    private AmazonClient() {
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        s3Client = new AmazonS3Client(credentials);
        s3Client.setRegion(com.amazonaws.services.s3.model.Region.AP_Singapore.toAWSRegion());
        transferUtility = new TransferUtility(s3Client, TheApplication.mInstance);
    }

    static AmazonClient getManager() {
        if (sManager == null) {
            synchronized (AmazonClient.class) {
                if (sManager == null) {
                    sManager = new AmazonClient();
                }
            }
        }
        return sManager;
    }

    public static AmazonClient getClient() {
        return AmazonClient.getManager();
    }

    public TransferUtility getTransferUtility() {
        return transferUtility;
    }
}
