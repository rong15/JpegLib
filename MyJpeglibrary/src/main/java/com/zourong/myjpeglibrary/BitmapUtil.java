package com.zourong.myjpeglibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
    static {
        System.loadLibrary("native-lib");
    }
    public native String stringFromJNI();

    public static native int compressBitmap(Bitmap bitmap, int quality,String fileName);

    public static Bitmap decodeFile(String path) {
        int finalWidth = 800;

        // 先获取宽度
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 不加载图片到内存只拿宽高
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        int bitmapWidth = options.outWidth;

        int inSampleSize = 1;

        if(bitmapWidth>finalWidth){
            inSampleSize = bitmapWidth/finalWidth;
        }

        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path,options);
    }
}
