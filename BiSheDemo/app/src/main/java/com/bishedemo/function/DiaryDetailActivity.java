package com.bishedemo.function;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bishedemo.R;
import com.bishedemo.note.WriteDialogListener;
import com.bishedemo.note.WritePadDialog;
import com.bishedemo.sqlite.QnoteSQLiteOpenHelper;
import com.bishedemo.utils.GetPathFromUri4kitkat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by fang on 2016/11/20.
 */

public class DiaryDetailActivity extends Activity implements View.OnClickListener{

    private ProgressDialog progressDialog;
    private ImageButton boldIB;
    private ImageButton italicIB;
    private ImageButton underlineIB;
    private ImageButton insert_handwritingIB;
    private ImageButton insert_imageIB;
    private ImageButton insert_cameraIB;
    private RichEditor mEditor;
    private EditText titleET;
    private Button backBT;
    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isUnderline = false;
    private int RESULT_LOAD_IMAGE = 20;
    private String noteText = "";
    private String noteTitle;

    public static Bitmap getZoomImage(Bitmap orgBitmap, double newWidth, double newHeight) {
        if (null == orgBitmap) {
            return null;
        }
        if (orgBitmap.isRecycled()) {
            return null;
        }
        if (newWidth <= 0 || newHeight <= 0) {
            return null;
        }

        // 获取图片的宽和高
        float width = orgBitmap.getWidth();
        float height = orgBitmap.getHeight();
        // 创建操作图片的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(orgBitmap, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_detail_layout);

        initView();
        initEditor();
        getData();
    }

    private void initView() {
        mEditor = (RichEditor) findViewById(R.id.editor);
        boldIB = (ImageButton) findViewById(R.id.action_bold);
        italicIB = (ImageButton) findViewById(R.id.action_italic);
        underlineIB = (ImageButton) findViewById(R.id.action_underline);
        insert_handwritingIB = (ImageButton) findViewById(R.id.action_handwritting);
        insert_imageIB = (ImageButton) findViewById(R.id.action_image);
        insert_cameraIB = (ImageButton) findViewById(R.id.action_camera);
        titleET = (EditText) findViewById(R.id.titleET);
        backBT = (Button) findViewById(R.id.back);

        backBT.setOnClickListener(this);
        boldIB.setOnClickListener(this);
        italicIB.setOnClickListener(this);
        underlineIB.setOnClickListener(this);
        insert_handwritingIB.setOnClickListener(this);
        insert_imageIB.setOnClickListener(this);
    }

    private void saveNote() {
        showDialog();
        SQLiteDatabase sq = new QnoteSQLiteOpenHelper(DiaryDetailActivity.this, "note.db").getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("notetime", System.currentTimeMillis());
        values.put("notetext", noteText);
        values.put("notetitle", noteTitle);
        try {
            sq.insert("notes", null, values);
        } catch (Exception e) {

        } finally {
            dismissDialog();
        }
    }

    private void initEditor() {
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                noteText = text;
            }
        });
    }

    private void getData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            titleET.setText(bundle.getString("title"));
            mEditor.setHtml(bundle.getString("text"));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("正在保存。。。");
        }
        progressDialog.show();
    }

    public void dismissDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                noteTitle = titleET.getText().toString();
                if(noteTitle.length()>0) {
                    saveNote();
                }
                finish();
                break;
            case R.id.action_bold:
                if(!isBold) {
                    boldIB.setImageResource(R.mipmap.bold_press);
                    mEditor.setBold();
                    isBold = !isBold;
                } else {
                    mEditor.setBold();
                    boldIB.setImageResource(R.mipmap.bold);
                    isBold = !isBold;
                }
                break;
            case R.id.action_italic:
                if(!isItalic) {
                    italicIB.setImageResource(R.mipmap.italic_press);
                    mEditor.setItalic();
                    isItalic = !isItalic;
                } else {
                    mEditor.setItalic();
                    italicIB.setImageResource(R.mipmap.italic);
                    isItalic = !isItalic;
                }
                break;
            case R.id.action_underline:
                if(!isUnderline) {
                    underlineIB.setImageResource(R.mipmap.underline_press);
                    mEditor.setUnderline();
                    isUnderline = !isUnderline;
                } else {
                    mEditor.setUnderline();
                    underlineIB.setImageResource(R.mipmap.underline);
                    isUnderline = !isUnderline;
                }
                break;
            case R.id.action_handwritting:
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        DiaryDetailActivity.this, new WriteDialogListener() {
                    @Override
                    public void onPaintDone(Object object) {
                        Bitmap mSignBitmap = (Bitmap) object;
                        String path = createSignFile(mSignBitmap);
                        mEditor.insertImage(path, "image");
                    }
                });
                mWritePadDialog.show();
                break;
            case R.id.action_image:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
                break;
        }
    }

    private String createSignFile(Bitmap mBitmap) {
        String fileName = System.currentTimeMillis() + "";
        return createSignFile(mBitmap, fileName);
    }

    private String createSignFile(Bitmap mBitmap, String fileName) {
        File destDir = new File(Environment.getExternalStorageDirectory() + File.separator + "Qnote");
        System.out.println("没创建");
        if (!destDir.exists()) {
            destDir.mkdirs();
            System.out.println("chuangjianle ");
        }

        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        String path = Environment.getExternalStorageDirectory() + File.separator + "Qnote" + File.separator + fileName + ".png";
        File file = null;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            if (b != null) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return path;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            String RealPath = GetPathFromUri4kitkat.getPath(DiaryDetailActivity.this,
                    uri);
            String samplePath = sampleBitmap(RealPath);
            mEditor.insertImage(samplePath, "image");
        }
    }

    private String sampleBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        // 获取屏幕尺寸
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int screenWidth = mDisplayMetrics.widthPixels;
        int be = (int) Math.ceil((options.outWidth / 320));
        if (be < 1)
            be = 1;
        else {
            be = be * 2;
        }

        options.inSampleSize = be;

        System.out.println("" + screenWidth);
        System.out.println("" + options.outWidth);
        System.out.println("" + be);
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap = BitmapFactory.decodeFile(path, options);

        System.out.println("" + options.outWidth);
        return createSignFile(bitmap, getFileName(path));
    }

    public String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }

    }
}
