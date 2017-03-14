package com.example.congbai.fundweather.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.congbai.fundweather.R;

import java.io.File;

/**
 * Created by fundmarkhua on 2017/3/13
 * Email:57525101@qq.com
 * 图片读取类简单封装
 */

public class ImageLoader {
    private static final String TAG = "ImageLoader";

    private static final int errorImg = 0;    // 加载失败图片  0 不显示
    private static final int placeHolder = 0; // 加载中图片  0 不显示
    private static final boolean skipMemoryCache = false; // 是否跳过内存缓存
    private static final boolean diskCache = false;  // 是否设置磁盘缓存策略
    private static final boolean asBitmap= false;  // 是否设置为B

    /**
     * 加载图片通用
     *
     * @param context   上下文
     * @param imgObject 图片对象
     * @param ivPic     加载目标
     */
    public static void loadImage(Context context, Object imgObject, final ImageView ivPic) {
        try {
            DrawableTypeRequest drawable = Glide.with(context).load(imgObject);
            if (errorImg != 0) {
                drawable.error(errorImg);
            }
            if (placeHolder != 0) {
                drawable.placeholder(placeHolder);
            }
            if (skipMemoryCache) {
                drawable.skipMemoryCache(skipMemoryCache);
            }
            if (diskCache) {
                drawable.diskCacheStrategy(DiskCacheStrategy.ALL);
            }
            if(asBitmap){
                drawable.asBitmap();
            }

            drawable.into(ivPic);
        } catch (Exception e) {
            Log.e(TAG, "loadImage: ", e);
        }
    }

    /**
     * 加载网络地址图片
     *
     * @param context 上下文
     * @param imgUrl  图片链接
     * @param ivPic   加载目标
     */
    public static void loadUrlImage(Context context, String imgUrl, final ImageView ivPic) {
        loadImage(context, imgUrl, ivPic);
    }

    /**
     * 加载资源图片
     *
     * @param context    上下文
     * @param resourceId 图片资源id
     * @param ivPic      加载目标
     */
    public static void loadResourceImage(Context context, int resourceId, final ImageView ivPic) {
        loadImage(context, resourceId, ivPic);
    }

    /**
     * 加载本地图片
     *
     * @param context 上下文
     * @param file    本地图片对象
     * @param ivPic   加载目标
     */
    public static void loadLocalImage(Context context, File file, final ImageView ivPic) {
        loadImage(context, file, ivPic);
    }

    /**
     * 加载Uri中图片
     *
     * @param context 上下文
     * @param uri     本地图片对象
     * @param ivPic   加载目标
     */
    public static void loadUriImage(Context context, Uri uri, final ImageView ivPic) {
        loadImage(context, uri, ivPic);
    }


}
