package com.hu.test.ui.drawer.login;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by TT on 2017/7/6.
 * login实现层，逻辑验证
 */

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void Login(final String name, final String password, final OnLoginFinishedListener loginFinishedListener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error =false;//是否错误
                if(TextUtils.isEmpty(name)){
                    loginFinishedListener.onUserNameError();
                    error=true;
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    loginFinishedListener.onPasswordError();
                    error=true;
                    return;
                }

                if(!error){
                    //无错误就执行onsucess
                    loginFinishedListener.onSucess();

                }
            }
        },2000);
    }
}
