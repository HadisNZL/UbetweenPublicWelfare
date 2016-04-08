package com.ubetween.ubetweenpublicwelfare.base;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.ubetween.ubetweenpublicwelfare.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 全局初始化app
 *
 * @author hadis on 16.4.5.
 */
public class BaseApplication extends Application {
    public static BaseApplication app;
    public static ImageOptions imageOptions;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        x.Ext.init(this);//初始化xutils3.x
        x.Ext.setDebug(true);// 是否输出debug日志
        initImageOptions();
    }


    public static BaseApplication getInstance() {
        return app;
    }

    public static ImageOptions getImageOptions() {
        return imageOptions;
    }

    private void initImageOptions() {
        imageOptions = new ImageOptions.Builder()
                // 图片大小
//                .setSize(DensityUtil.dip2px(240), DensityUtil.dip2px(200))
//                .setSize(DensityUtil.getScreenWidth(), ((DensityUtil.getScreenWidth() * 9) / 16))
                // ImageView圆角半径
                .setRadius(DensityUtil.dip2px(0))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.ic_launcher)// 加载中默认显示图片
                .setFailureDrawableId(R.drawable.ic_launcher)// 加载失败后默认显示图片
                .build();
    }

    /**
     * 此方法描述的是： 复制内容到剪贴板
     */
    public static void copyContent(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
    }

}
