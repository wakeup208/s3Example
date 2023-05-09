package com.example.s3example.aws;

import com.amazonaws.regions.Regions;

public class AWSKeys {
    protected static final String COGNITO_POOL_ID = "YOUR-POOL-ID-HERE";
    protected static final Regions MY_REGION = Regions.AP_SOUTHEAST_1; /*Change Region Here*/
    public static final String BUCKET_NAME = "YOUR-BUCKET_NAME-HERE";
    public static String PHOTO_TEMP_PATH = "/storage/emulated/0/test.jpg"; // store the downloaded image path
    public static String AWS_SECRET_KEY = ""; // AWS secret key
    public static String KEY = ""; // AWS download from key
    public static String AWS_ACCESS_KEY = ""; // AWS access key
}
