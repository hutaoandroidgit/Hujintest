package com.hu.test.ui.drawer.safetool;

import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

/**
 * Created by TT on 2017/7/7.
 */

public class SafeInteractorImpl implements SafeInteractor {


    private int fingerErrorNum = 0;
    private FingerprintIdentify fingerprintIdentify;



    @Override
    public void verdictPassFingerprintTrue( final OnVerdictPassFingerprintTrue onVerdictPassFingerprintTrue) {
        fingerprintIdentify.startIdentify(5, new BaseFingerprint.FingerprintIdentifyListener() {
            @Override
            public void onSucceed() {
                //  tag("验证成功");
                onVerdictPassFingerprintTrue.passFinger();
            }

            @Override
            public void onNotMatch(int availableTimes) {
                //tag("指纹不匹配，可用次数剩余：" + availableTimes);
                fingerErrorNum=availableTimes;
                //  tv_dialog_content.setText("指纹不匹配，剩余次数："+availableTimes);
                onVerdictPassFingerprintTrue.faileOnce(fingerErrorNum);
            }

            @Override
            public void onFailed() {
                // tag("验证遇到错误！！！");
                if (fingerErrorNum==1) {
                    //        tv_dialog_content.setText("指纹已错5次，请稍后再试");
                    onVerdictPassFingerprintTrue.faileAll();
                }else {
                    //        tv_dialog_content.setText("验证遇到错误，请稍后再试");
                }
                //   stFingerprint.setChecked(false);
            }
        });
    }

    /**
     * 指纹模块是否可用
     */
    @Override
    public void verdictFingerprint(FingerprintIdentify mFingerprintIdentify,OnVerdictFingerprint onVerdictFingerprint) {

        fingerprintIdentify = mFingerprintIdentify;

        //这里实现指纹模块是否可用
        if(mFingerprintIdentify.isFingerprintEnable()){
            //可用的话
            onVerdictFingerprint.useable();



        }else{
            onVerdictFingerprint.faile();
        }
    }



}
