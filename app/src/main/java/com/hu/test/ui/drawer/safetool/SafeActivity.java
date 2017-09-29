package com.hu.test.ui.drawer.safetool;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.hu.test.R;
import com.hu.test.ui.BaseActivity;
import com.hu.test.utils.UtilCom;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/**
 * Created by TT on 2017/7/6.
 */

public class SafeActivity extends BaseActivity implements SafeView {

    private LinearLayout llGesture;
    private LinearLayout llFingerprint;
    private Switch stGesture;
    private Switch stFingerprint;
    private TextView tvTip;
    private SafePresenter safePresenter;
    private FingerprintIdentify mFingerprintIdentify;
    private Dialog fingerprintdialog;
    private TextView tv_dialog_content;
    private int fingerErrorNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        setTitle("安全工具");
        //这里模拟网络请求
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //已在主线程中，可以更新UI
                showContentView();

            }
        }, 1000);
        safePresenter = new SafePresenterImpl(this);
        init();


    }

    private void init() {
        llGesture = (LinearLayout) findViewById(R.id.ll_gesture);
        stGesture = (Switch) findViewById(R.id.st_gesture);
        llFingerprint = (LinearLayout) findViewById(R.id.ll_fingerprint);
        stFingerprint = (Switch) findViewById(R.id.st_fingerprint);

        tvTip = (TextView) findViewById(R.id.tv_tip);

        mFingerprintIdentify = new FingerprintIdentify(this, new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {
                exception.printStackTrace();
            }
        });

        //初始化dialog
        showDialog();


        //设置监听
        stGesture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Log.e("tag", "开");


                } else {
                    Log.e("tag", "关");
                }
            }
        });


        stFingerprint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Log.e("tag", "开");
                    //这里验证指纹模块能不能用

                    safePresenter.verdictUsableFingerprint(mFingerprintIdentify);

                 //   fingerprintdialog.show();



                    safePresenter.verdictPassFingerprint();
                } else {
                    Log.e("tag", "关");
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        safePresenter.onDestory();
        mFingerprintIdentify = null;
        super.onDestroy();
    }

    @Override
    public void showTip() {
        tvTip.setVisibility(View.VISIBLE);
        stFingerprint.setEnabled(false);//显示提示不让点
        stFingerprint.setChecked(false);
    }

    @Override
    public void hideTip() {
        stFingerprint.setEnabled(true);//显示不提示让点
        tvTip.setVisibility(View.GONE);
    }

    @Override
    public void setGestureCheck() {
        stGesture.setChecked(true);
    }

    @Override
    public void setGestureNocheck() {
        stGesture.setChecked(false);
    }

    @Override
    public void setFingerprintCheck() {
        stFingerprint.setChecked(true);
    }

    @Override
    public void setFingerprintNocheck() {
        stFingerprint.setChecked(false);
    }

    @Override
    public void showFingerprintDialog() {
        showDialog();
    }

    @Override
    public void setDialogContent(int num, String tip) {
        if(tv_dialog_content != null){
            tv_dialog_content.setText(tip);
        }
    }

    @Override
    public void showFingerDialog() {
        if(fingerprintdialog != null){
            fingerprintdialog.show();
        }
    }

    @Override
    public void hideFingerDialog() {
        if(fingerprintdialog != null){
            if(fingerprintdialog.isShowing()){
                fingerprintdialog.dismiss();
            }
        }
    }

    public void showDialog() {
        fingerprintdialog = new Dialog(SafeActivity.this, R.style.dialog);
        View view = LayoutInflater.from(SafeActivity.this).inflate(R.layout
                .fingerprint_dialog, null);
        LinearLayout lay_dialog = (LinearLayout) view.findViewById(R.id.lay_dialog);

        TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_title);
        tv_dialog_content = (TextView) view.findViewById(R.id.tv_dialog_content);
        tv_dialog_content.setText("请验证系统录入指纹开启指纹解锁");
        Button btn_dialog_next = (Button) view.findViewById(R.id.btn_dialog_next);
        btn_dialog_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mNeedToRestartFingerprint=false;
                fingerprintdialog.dismiss();
                mFingerprintIdentify.cancelIdentify();
                stFingerprint.setChecked(false);
            }
        });
        lay_dialog.getBackground().setAlpha(243);
        fingerprintdialog.setContentView(view);// 得到加载view
        Window dialogWindow = fingerprintdialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = UtilCom.dip2px(SafeActivity.this, 245); // 宽度
        lp.alpha = 0.95f; // 透明度
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        //dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        fingerprintdialog.setCanceledOnTouchOutside(true);//点击对话框以外不关闭
        fingerprintdialog.setCancelable(false);//是否可以用返回键取消
    }


}
