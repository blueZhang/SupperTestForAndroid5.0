package com.bluezhang.supperapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

public class PaletteActivity extends AppCompatActivity implements Palette.PaletteAsyncListener {
    private ImageView imageView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        imageView = (ImageView) findViewById(R.id.image_view);
        textView = (TextView) findViewById(R.id.text_view);
        textView.setText("娶你呀");
        imageView.setImageResource(R.mipmap.ic_luncher);

        //通常使用异步任务加载
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_luncher);
        //使用.from()准备处理bitamp； .generate()同步方法，计算色系风格 .generate()加上listener就是异步方法
        //同步方法会阻塞线程
        Palette.from(bitmap).generate(this);
    }

    /**
     * Called when the {@link Palette} has been generated.
     * 调色板处理完成的回掉
     * @param palette
     */
    @Override
    public void onGenerated(Palette palette) {
        //获取用于覆盖在图片上的主题颜色，黑色的柔和的
        int darkMutedColor = palette.getVibrantColor(Color.WHITE);
        int red = Color.red(darkMutedColor);
        int g = Color.green(darkMutedColor);
        int b = Color.blue(darkMutedColor);
        darkMutedColor = Color.argb(0x99,red,g,b);
        textView.setBackgroundColor(darkMutedColor);
        Palette.Swatch swatch = palette.getDarkMutedSwatch();
        if (swatch != null) {
            int titleTextColor = swatch.getTitleTextColor();
            textView.setTextColor(titleTextColor);

        }

    }
}
