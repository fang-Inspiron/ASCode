package com.example.universal_imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jianfang on 2016/4/24.
 */
public class ImageListActivity extends AbsListViewBaseActivity{

    DisplayImageOptions options;        // DisplayImageOptions����������ͼƬ��ʾ����
    String path = Environment.getExternalStorageDirectory().toString();
    String[] imageUrls;                 // ͼƬ·��
    String newPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_image_list);
        imageLoader = ImageLoader.getInstance();

        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        Bundle bundle = getIntent().getExtras();
        imageUrls = bundle.getStringArray(Constants.Extra.IMAGES);

        createSDCardDir();

        // ʹ��DisplayImageOptions.Builder()����DisplayImageOptions
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)          // ����ͼƬ�����ڼ���ʾ��ͼƬ
                .showImageForEmptyUri(R.mipmap.ic_launcher)  // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
                .showImageOnFail(R.mipmap.ic_launcher)       // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
                .cacheInMemory(true)                        // �������ص�ͼƬ�Ƿ񻺴����ڴ���
                .cacheOnDisc(true)                          // �������ص�ͼƬ�Ƿ񻺴���SD����
                .displayer(new RoundedBitmapDisplayer(20))  // ���ó�Բ��ͼƬ
                .build();                                   // �������ù���DisplayImageOption����

        listView = (ListView) findViewById(android.R.id.list);
        ((ListView) listView).setAdapter(new ItemAdapter());
    }

    @Override
    public void onBackPressed() {
        AnimateFirstDisplayListener.displayedImages.clear();
        super.onBackPressed();
    }



    /**
     * �Զ����б���������
     */
    class ItemAdapter extends BaseAdapter {

        private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

        private class ViewHolder {
            public TextView text;
            public ImageView image;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.item_list_image, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView) view.findViewById(R.id.text);
                holder.image = (ImageView) view.findViewById(R.id.image);
                view.setTag(holder);        // ��View���һ�����������
            } else {
                holder = (ViewHolder) view.getTag(); // ������ȡ����
            }

            holder.text.setText("Item " + (position + 1));  // TextView�����ı�

            /**
             * ��ʾͼƬ
             * ����1��ͼƬurl
             * ����2����ʾͼƬ�Ŀؼ�
             * ����3����ʾͼƬ������
             * ����4��������
             */
            imageLoader.displayImage(imageUrls[position], holder.image, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {


                    try {
                        System.out.println(bitmap==null);
                        FileOutputStream fos = null;
                        File f = new File(newPath + position + ".jpeg");
                        f.createNewFile();
                        fos = new FileOutputStream(f);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fos);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });



            return view;
        }
    }

    // �����ļ���
    public void createSDCardDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // ����һ���ļ��ж��󣬸�ֵΪ�ⲿ�洢����Ŀ¼
            File sdcardDir = Environment.getExternalStorageDirectory();
            // �õ�һ��·����������sdcard���ļ���·��������
            newPath = sdcardDir.getPath() + "/ImageLoaderPic/";// newPath�ڳ�����Ҫ����
            File path1 = new File(newPath);
            if (!path1.exists()) {
                // �������ڣ�����Ŀ¼��������Ӧ��������ʱ�򴴽�
                path1.mkdirs();
                System.out.println("paht ok,path:" + newPath);
            }
        } else {
            System.out.println("false");
        }
    }

    /**
     * ͼƬ���ص�һ����ʾ������
     *
     * @author Administrator
     */
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // �Ƿ��һ����ʾ
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // ͼƬ����Ч��
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
