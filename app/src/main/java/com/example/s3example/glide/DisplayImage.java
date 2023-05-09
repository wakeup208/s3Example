package com.example.s3example.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.s3example.R;


import static com.example.s3example.aws.AWSKeys.BUCKET_NAME;
import static com.example.s3example.aws.AWSKeys.PHOTO_TEMP_PATH;


public class DisplayImage {

    private static DisplayImage displayImage = new DisplayImage();

    private DisplayImage() {}

    public static DisplayImage getInstance () { return displayImage;}

    public void displayImageForUser (Context context, ImageView imageView, String uniqueId) {

        ImageModel imageModel = new ImageModel();
        imageModel.setId(uniqueId);
        imageModel.setLocalPath(PHOTO_TEMP_PATH);
        imageModel.setBucketName(BUCKET_NAME);

        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                //.load("https://s3.us-east-1.amazonaws.com/ws-store-upload-nmt/1683644925831-1.jpg")
                .load(imageModel)
                .into(imageView);
    }

}
