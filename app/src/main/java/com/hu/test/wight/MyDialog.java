package com.hu.test.wight;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by TT on 2017/6/29.
 */

public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static MyDialog getMyDialog(Context context) {
        return null;
    }
}
