package com.hu.test.ui.drawer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hu.test.R;
import com.hu.test.utils.BitmapUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by TT on 2017/9/12.
 */
public class CloudActivity extends Activity {

    private int PHOTO_GRAPH;
    private ImageView ivCloud;
    private String mipmap64;
    private File picture;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);
        ivCloud = (ImageView) findViewById(R.id.iv_cloud);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(),"temp.jpg")));
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<String>post("http://cloud.exocr.com/recognize")
                        .params("username", "test")
                        .params("password", "test")
                        .params("recotype", "IdCard")
                        .params("image", mipmap64)
                        .params("encoding", "utf-8")
                        .params("head_portrait", 0)
                        .params("crop_image", 1)
                        .params("b64", 1)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String body = response.body();
                                Log.e("body", body.toString());
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//完成照相后回调用此方法

        if (requestCode == 1) {
            // 设置文件保存路径
            picture = new File(Environment.getExternalStorageDirectory()
                    + "/temp.jpg");
            //startPhotoZoom(Uri.fromFile(picture));
            try {
                bmp = BitmapUtil.getBmp(picture);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                mipmap64 = Bitmap2StrByBase64(bmp);
                Log.e("base64==",mipmap64);
                ivCloud.setImageBitmap(bmp); //把图片显示在ImageView控件上
            } catch (FileNotFoundException e) {

            }
        }

        // 处理结果
        if (requestCode == 3) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                mipmap64 = Bitmap2StrByBase64(photo);
                Log.e("base64==",mipmap64);
                ivCloud.setImageBitmap(photo); //把图片显示在ImageView控件上

            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    private static final String IMAGE_UNSPECIFIED = "image/*";
    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX",480);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
