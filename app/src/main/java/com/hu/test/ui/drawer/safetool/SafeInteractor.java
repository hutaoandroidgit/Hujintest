package com.hu.test.ui.drawer.safetool;

import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;

/**
 * Created by TT on 2017/7/7.
 */

public interface SafeInteractor {

    interface OnVerdictPassFingerprintTrue{
        void passFinger();

        void faileOnce(int fingerErrorNum);

        void faileAll();
    }

    void verdictPassFingerprintTrue(OnVerdictPassFingerprintTrue onVerdictPassFingerprintTrue);



    interface OnVerdictFingerprint{
        void useable();

        void faile();
    }

    void verdictFingerprint(FingerprintIdentify mFingerprintIdentify,OnVerdictFingerprint onVerdictFingerprint);





}
