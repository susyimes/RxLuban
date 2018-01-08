package com.susyimes.funbox.rxluban;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.susyimes.funbox.rx2luban.luban.luban.Luban;
import com.susyimes.funbox.rx2luban.luban.luban.OnCompressListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {
    private ImageView img1,img2;
    private ByteArrayOutputStream mByteArrayOutputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);


        Resources res = MainActivity.this.getResources();
        Bitmap bmp= BitmapFactory.decodeResource(res, R.mipmap.photo);
        try {
            saveImage(Environment.getExternalStorageDirectory()
                    + "/"+"123.jpg",bmp,1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Luban.compress(new File(Environment.getExternalStorageDirectory()
                + "/"+"123.jpg"),new File(Environment.getExternalStorageDirectory()
                + "/"+"234.jpg")).launch(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                Log.d("pengying",file.getPath());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        Glide.with(this).load(R.mipmap.ic_launcher).into(img2);
    }

    private File saveImage(String filePath, Bitmap bitmap, long size) throws IOException {


        File result = new File(filePath.substring(0, filePath.lastIndexOf("/")));

        if (!result.exists() && !result.mkdirs()) {
            return null;
        }

        if (mByteArrayOutputStream == null) {
            mByteArrayOutputStream = new ByteArrayOutputStream(
                    bitmap.getWidth() * bitmap.getHeight());
        } else {
            mByteArrayOutputStream.reset();
        }

        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, mByteArrayOutputStream);

//        while (mByteArrayOutputStream.size() / 1024 > size && options > 6) {
//            mByteArrayOutputStream.reset();
//            options -= 6;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, options, mByteArrayOutputStream);
//        }
        bitmap.recycle();

        FileOutputStream fos = new FileOutputStream(filePath);
        mByteArrayOutputStream.writeTo(fos);
        fos.close();

        return new File(filePath);
    }
}
