package com.example.lifem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.photo.activity.AlbumActivity;
import com.photo.utils.Bimp;
import com.photo.utils.FileUtils;
import com.photo.utils.ImageItem;
import com.photo.utils.PublicWay;
import com.photo.utils.Res;
import com.xy.lifemanage.view.TopBar;

import java.util.ArrayList;

/**
 * Created by jianfang on 2016/5/13.
 */
public class RingShareOrgView extends Activity {

    private TopBar topBar;
    private EditText ring_shareorg_et_content;
    private RelativeLayout ring_shareorg_layout_chooseOrg;
    private RelativeLayout ring_shareorg_layout_chooseRecruit;
    private GridView ring_shareorg_noScrollgridview;
    private GridAdapter adapter;
    private ProgressDialog progressDialog;
    public static Bitmap bimap;
    private View parentView;
    private PopupMenu pm_bt_xuanze = null;
    public String popMenu_selected;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static String path;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 100) {
                progressDialog.dismiss();
                Toast.makeText(RingShareOrgView.this, "上传成功", Toast.LENGTH_SHORT).show();
                Bimp.tempSelectBitmap.clear();
                finish();
            }
            if (msg.what == 1) {
                adapter.notifyDataSetChanged();
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ring_shareorg_main);

        Res.init(this);
        bimap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_addpic_unfocused);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.ring_shareorg_main, null);
        setContentView(parentView);

        findID();
        setListener();
        init();
    }

    public void init() {

        pop = new PopupWindow(RingShareOrgView.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RingShareOrgView.this, AlbumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        ring_shareorg_noScrollgridview = (GridView) findViewById(R.id.ring_shareorg_noScrollgridview);
        ring_shareorg_noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        ring_shareorg_noScrollgridview.setAdapter(adapter);
        ring_shareorg_noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Bimp.tempSelectBitmap.size()) {
                    ll_popup.startAnimation(AnimationUtils.loadAnimation(RingShareOrgView.this, R.anim.activity_translate_in));
                    pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
                } else {
                    Intent intent = new Intent(RingShareOrgView.this, AlbumActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });
    }

    private void findID() {
        ring_shareorg_et_content = (EditText) findViewById(R.id.ring_shareorg_et_content);
        ring_shareorg_layout_chooseOrg = (RelativeLayout) findViewById(R.id.ring_shareorg_layout_chooseOrg);
        ring_shareorg_layout_chooseRecruit = (RelativeLayout) findViewById(R.id.ring_shareorg_layout_chooseRecruit);
        topBar = (TopBar) findViewById(R.id.topBar_ring_shareorg);
        ring_shareorg_noScrollgridview = (GridView) findViewById(R.id.ring_shareorg_noScrollgridview);
        ring_shareorg_noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * imgPathList表示选中的几张图片的路径
     * onRestart用来接收Bimp.tempSelectBitmap(图片路径);更新数据
     */
    ArrayList<String> imgPathList;

    protected void onRestart() {
        imgPathList = new ArrayList<String>();
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            imgPathList.add(Bimp.tempSelectBitmap.get(i).getImagePath());
        }
        adapter.update();
        super.onRestart();
    }

    public ArrayList<String> getImgPathList() {
        return imgPathList;
    }

    public void setImgPathList(ArrayList<String> imgPathList) {
        this.imgPathList = imgPathList;
    }

    public void setListener() {
        topBar.setTopBarOnclickListener(new TopBar.topBarOnclickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RingShareOrgView.this).setTitle("提示").setMessage("确认发布?")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                arg0.dismiss();
                                progressDialog = new ProgressDialog(RingShareOrgView.this);
                                progressDialog.setTitle("提示");
                                progressDialog.setMessage("内容发布中...");
                                progressDialog.show();
                                new Thread() {
                                    public void run() {
                                        try {
                                            Thread.sleep(500);
                                            uploadData();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    ;
                                }.start();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }

    public void uploadData() {
        //此处需获取数据，图片获取如下

        //获取选中的几张图片
//        List<BmobFile> bmobFiles = new ArrayList<BmobFile>();
//        for (int i=0; i<imgPathList.size(); i++) {
//            BmobFile b = new BmobFile(new File(imgPathList.get(i)));
//            bmobFiles.add(b);
//        }

        //Bmob要先上传选中的几张图片imgPathList

        //获取数据成功后向handler发送消息
        handler.sendEmptyMessage(100);
    }
    public void setPath(String p) {
        path = p;
    }

    public static String getPath() {
        return path;
    }



    public class GridAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        @Override
        public int getCount() {
            if (Bimp.tempSelectBitmap.size() == 9) {
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.icon_addpic_unfocused));
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            Bimp.max += 1;
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    }
                }
            }).start();
        }
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {

                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);

                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (int i = 0; i < PublicWay.activityList.size(); i++) {
                if (null != PublicWay.activityList.get(i)) {
                    PublicWay.activityList.get(i).finish();
                }
            }
            System.exit(0);
        }
        return true;
    }
}
