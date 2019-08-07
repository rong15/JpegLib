package com.zourong.jpegcompressdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zourong.myjpeglibrary.BitmapUtil;

public class MainActivity extends AppCompatActivity {

    ImageView image1,image2;
    TextView textView1,textView2;
    Button btn1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        textView1 =  findViewById(R.id.text1);
        textView2 =  findViewById(R.id.text2);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        // image1.setImageResource(R.mipmap.ic_launcher);
        /*Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.get*/

        bitmap = BitmapUtil.decodeFile("storage/emulated/0/DCIM/Camera" + "/20190807_140833.jpg");
        Log.i("mylog","bitmap::::" + bitmap);
        // bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/1548840834365.jpg");
        image1.setImageBitmap(bitmap);
        textView1.setText(bitmap.getByteCount() + "");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = BitmapUtil.compressBitmap(bitmap,50, Environment.getExternalStorageDirectory().getAbsolutePath() + "/jpegTest.png");


            }
        });
        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap2 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jpegTest.png");

                //Log.i("mylog","i:::::" + bitmap2.getByteCount());
                image2.setImageBitmap(bitmap2);
                textView2.setText(bitmap2.getByteCount() + "");

            }
        });
        //Log.i("mylog",stringFromJNI2(bitmap,20,"hello"));
        //int i = compressBitmap(bitmap,20,"jpegTest.png");
        /* Log.i("mylog","i:::::" + i);*/

        // Example of a call to a native method
       /* TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());*/

    }

    private void getPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);
                return;
            }else{
                //上面已经写好的拨号方法
                //callDirectly(mobile);
            }
        }
    }

}
