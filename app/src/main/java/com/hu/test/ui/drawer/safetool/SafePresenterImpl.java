package com.hu.test.ui.drawer.safetool;

import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

/**
 * Created by TT on 2017/7/7.
 */

public class SafePresenterImpl implements  SafeInteractor.OnVerdictFingerprint, SafeInteractor.OnVerdictPassFingerprintTrue,SafePresenter {

    private  SafeView safeView;
    private  SafeInteractor safeInteractor;

    public SafePresenterImpl(SafeView safeView) {
        this.safeView = safeView;
        this.safeInteractor = new SafeInteractorImpl();
    }

    @Override
    public void useable() {
        //调用View层，隐藏提醒
        if(safeView != null){
            safeView.hideTip();

            safeView.showFingerDialog();
        }



    }

    @Override
    public void faile() {
        //调用View层，显示提醒
        if(safeView != null){
            safeView.showTip();
        }
    }

    @Override
    public void onDestory() {
        //置空，放掉引用
        safeView = null ;
    }

    @Override
    public void verdictUsableFingerprint(FingerprintIdentify mFingerprintIdentify) {

        //验证指纹模块是否可用
        safeInteractor.verdictFingerprint(mFingerprintIdentify,this);
    }

    /**
     * 验证指纹是否通过
     */
    @Override
    public void verdictPassFingerprint() {
        safeInteractor.verdictPassFingerprintTrue(this);
    }


    @Override
    public void passFinger() {
        if(safeView != null){
            safeView.hideFingerDialog();
        }
    }

    @Override
    public void faileOnce(int fingerErrorNum) {
        if(safeView != null){
            safeView.setDialogContent(fingerErrorNum,"验证失败=="+fingerErrorNum);
        }
    }

    @Override
    public void faileAll() {
        if(safeView != null){
            safeView.setDialogContent(0,"全部失败");
        }
    }
}
