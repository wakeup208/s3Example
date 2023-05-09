package com.example.s3example.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;
import java.lang.annotation.Annotation;

/**
 * Created by ankit on 26-08-2017.
 */
@Deprecated
public class CustomGlideModule implements GlideModule {

//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//        builder.setImageDecoderEnabledForBitmaps(true);
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//        registry.replace(ImageModel.class, InputStream.class, new ImageLoader.Factory());
//    }

//    @Override
//    public String glideName() {
//        return null;
//    }
//
//    @Override
//    public Class<? extends Annotation> annotationType() {
//        return null;
//    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setImageDecoderEnabledForBitmaps(true);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                DiskCache.Factory.DEFAULT_DISK_CACHE_DIR,
                DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(ImageModel.class, InputStream.class, new ImageLoader.Factory());
    }
}
