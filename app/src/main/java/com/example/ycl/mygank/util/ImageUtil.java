package com.example.ycl.mygank.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.ycl.mygank.R;

/**
 * Created by YCL on 2016/6/14.
 */
public class ImageUtil {

    public static void load(Context context, String url, ImageView iv){
        Glide.with(context.getApplicationContext())
                .load(url)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
//                    .override(0, 0)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade(100)
                .into(new GlideDrawableImageViewTarget(iv));
    }

}
