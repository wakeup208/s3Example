package com.example.s3example.aws;

import com.amazonaws.regions.Regions;

public class AWSKeys {
    protected static final String COGNITO_POOL_ID = "eu-north-1:86c0f70d-7923-4e24-b848-2159ed00170c";
    protected static final Regions MY_REGION = Regions.US_EAST_1; /*Change Region Here*/
    public static final String BUCKET_NAME = "ws-store-upload-nmt";
    public static String PHOTO_TEMP_PATH = "/storage/emulated/0/test.jpg"; // store the downloaded image path
    public static String AWS_SECRET_KEY = "i7TTxrTykjBcopSwrO3Yb/O7U+afQDuavSH4hhXY"; // AWS secret key
    public static String KEY = "IMG_20230509_062657_256.jpg"; // AWS download from key
    public static String AWS_ACCESS_KEY = "AKIA3KEBCIQ26RNPZEP3"; // AWS access key
}
