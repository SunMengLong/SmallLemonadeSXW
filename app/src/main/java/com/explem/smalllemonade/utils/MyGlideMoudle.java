package com.explem.smalllemonade.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by ${薛亚南}
 * on 2016/12/28 20：32.
 */

public class MyGlideMoudle implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        File cacheDir = context.getExternalCacheDir();//指定的是数据的缓存地址
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        int diskCacheSize = 1024 * 1024 * 200;//最多可以缓存多少字节的数据
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize))
                //设置磁盘缓存大小
                .setDiskCache(new DiskLruCacheFactory(cacheDir.getPath(), "glide", diskCacheSize))
                //设置图片解码格式
                .setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
                //设置BitmapPool缓存内存大小
                .setBitmapPool(new LruBitmapPool(memoryCacheSize));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
    }
}
