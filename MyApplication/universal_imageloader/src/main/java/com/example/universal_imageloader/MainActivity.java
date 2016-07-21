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

        // �����ļ�����Ŀ¼��/mnt/sdcard, �ļ���:TEST_FILE_NAME
        File testImageOnSdCard = new File(path+"/", TEST_FILE_NAME);
        System.out.println("aaaaaaaaaaaaa");
        if (!testImageOnSdCard.exists()) {  // ����ļ�������
            System.out.println("bbbbbbbbbbbbb");
            // ���ļ����Ƶ�SD��
            try {
                testImageOnSdCard.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            copyTestImageToSdCard(testImageOnSdCard);
        }
    }

    // �������ListViewչʾ����
    public void onImageListClick(View view) {
        Intent intent = new Intent(this, ImageListActivity.class);
        intent.putExtra(Constants.Extra.IMAGES, IMAGES);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        imageLoader.stop();     // ֹͣ����ͼƬ
        super.onBackPressed();
    }

    /**
     * ��һ���̰߳�assertĿ¼�µ�ͼƬ���Ƶ�SD��Ŀ¼��
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
                            fos.write(buffer, 0, read); // д�������
                        }
                    } finally {
                        fos.flush();        // д��SD��
                        fos.close();        // �ر������
                        is.close();         // �ر�������
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    L.w("Can't copy test image onto SD card");
                }
            }
        }).start();     // �����߳�
    }
}
