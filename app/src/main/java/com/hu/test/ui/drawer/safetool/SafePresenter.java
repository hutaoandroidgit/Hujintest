package com.hu.test.ui.drawer.safetool;

import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

/**
 * Created by TT on 2017/7/7.
 */

public interface SafePresenter {
    void onDestory();

    //这里可以接数据的
    void verdictUsableFingerprint(FingerprintIdentify mFingerprintIdentify);

    //验证指纹是否通过
    void verdictPassFingerprint();
}
