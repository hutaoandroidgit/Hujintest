package com.hu.test.ui.drawer.safetool;

/**
 * Created by TT on 2017/7/6.
 */

public interface SafeView {
    void showTip();

    void hideTip();

    void setGestureCheck();

    void setGestureNocheck();

    void setFingerprintCheck();

    void setFingerprintNocheck();

    void showFingerprintDialog();

    void setDialogContent(int num,String tip);

    void showFingerDialog();

    void hideFingerDialog();


}
