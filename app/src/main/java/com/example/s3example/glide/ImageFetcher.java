package com.example.s3example.glide;

import android.util.Log;

import androidx.annotation.NonNull;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.example.s3example.AmazonClient;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageFetcher implements DataFetcher<InputStream> {

    private final ImageModel imageModel;
    private InputStream mInputStream;
    boolean downloadComplete = false;
    int transferId = 0;

    public ImageFetcher(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    private InputStream fetchStream(final ImageModel imageModel) {
        TransferUtility transferUtility = AmazonClient.getClient().getTransferUtility();
        TransferObserver transferObserver = transferUtility.download(imageModel.getBucketName(), imageModel.getId(), new File(imageModel.getLocalPath()));
        transferId = transferObserver.getId();

        transferObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.wtf("AWSS3", "onStateChanged = " + state);
                if (state == TransferState.COMPLETED) {
                    File initialFile = new File(imageModel.getLocalPath());
                    try {
                        mInputStream = new FileInputStream(initialFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    downloadComplete = true;
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                if (bytesTotal == 0) return;
//                final int percentage = (int) ((bytesCurrent / (float)bytesTotal) * 100);
//                Log.wtf("AWSS3", "onProgressChanged = " + percentage);
            }

            @Override
            public void onError(int id, Exception ex) {
                // do something
                Log.wtf("AWSS3", "onError");
                ex.printStackTrace();
                downloadComplete = true;
            }
        });
        while (!downloadComplete){}
        return mInputStream;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
        callback.onDataReady(fetchStream(imageModel));

    }

    @Override
    public void cleanup() {
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mInputStream = null;
            }
        }
    }

//    @Override
//    public String getId() {
//        return imageModel.getId();
//    }

    @Override
    public void cancel() {
        AmazonClient.getClient().getTransferUtility().cancel(transferId);
    }

    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
