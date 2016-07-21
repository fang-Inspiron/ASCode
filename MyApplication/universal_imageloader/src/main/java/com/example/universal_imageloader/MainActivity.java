package com.example.universal_imageloader;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import static com.example.universal_imageloader.Constants.IMAGES;
import com.nostra13.universalimageloader.utils.L;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends BaseActivity {

    private static final String TEST_FILE_NAME = "center.png";
    String path = Environment.getExternalStorageDirectory().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 定义文件对象，目录：/mnt/sdcard, 文件名:TEST_FILE_NAME
        File testImageOnSdCard = new File(path+"/", TEST_FILE_NAME);
        System.out.println("aaaaaaaaaaaaa");
        if (!testImageOnSdCard.exists()) {  // 如果文件不存在
            System.out.println("bbbbbbbbbbbbb");
            // 把文件复制到SD卡
            try {
                testImageOnSdCard.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            copyTestImageToSdCard(testImageOnSdCard);
        }
    }

    // 点击进入ListView展示界面
    public void onImageListClick(View view) {
        Intent intent = new Intent(this, ImageListActivity.class);
        intent.putExtra(Constants.Extra.IMAGES, IMAGES);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        imageLoader.stop();     // 停止加载图片
        super.onBackPressed();
    }

    /**
     * 开一个线程把assert目录下的图片复制到SD卡目录下
     * @param testImageOnSdCard
     */
    private void copyTestImageToSdCard(final File testImageOnSdCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    InputStream is = getResources().openRawResource(R.raw.center);
                    FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
                    byte[] buffer = new byte[8192];
                    int read;
                    try {
                        while ((read = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, read); // 写入输出流
                        }
                    } finally {
                        fos.flush();        // 写入SD卡
                        fos.close();        // 关闭输出流
                        is.close();         // 关闭输入流
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    L.w("Can't copy test image onto SD card");
                }
            }
        }).start();     // 启动线程
    }
}
