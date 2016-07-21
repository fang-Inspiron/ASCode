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

    DisplayImageOptions options;        // DisplayImageOptions是用于设置图片显示的类
    String path = Environment.getExternalStorageDirectory().toString();
    String[] imageUrls;                 // 图片路径
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

        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.ic_launcher)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .build();                                   // 创建配置过得DisplayImageOption对象

        listView = (ListView) findViewById(android.R.id.list);
        ((ListView) listView).setAdapter(new ItemAdapter());
    }

    @Override
    public void onBackPressed() {
        AnimateFirstDisplayListener.displayedImages.clear();
        super.onBackPressed();
    }



    /**
     * 自定义列表项适配器
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
                view.setTag(holder);        // 给View添加一个格外的数据
            } else {
                holder = (ViewHolder) view.getTag(); // 把数据取出来
            }

            holder.text.setText("Item " + (position + 1));  // TextView设置文本

            /**
             * 显示图片
             * 参数1：图片url
             * 参数2：显示图片的控件
             * 参数3：显示图片的设置
             * 参数4：监听器
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

    // 创建文件夹
    public void createSDCardDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            newPath = sdcardDir.getPath() + "/ImageLoaderPic/";// newPath在程序中要声明
            File path1 = new File(newPath);
            if (!path1.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
                System.out.println("paht ok,path:" + newPath);
            }
        } else {
            System.out.println("false");
        }
    }

    /**
     * 图片加载第一次显示监听器
     *
     * @author Administrator
     */
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
