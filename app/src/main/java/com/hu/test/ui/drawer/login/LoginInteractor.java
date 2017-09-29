package com.hu.test.ui.drawer.login;

/**
 * Created by TT on 2017/7/6.
 */

public interface LoginInteractor {

    interface OnLoginFinishedListener{
        void  onUserNameError();

        void onPasswordError();

        void onSucess();
    }

    void Login(String name ,String password ,OnLoginFinishedListener loginFinishedListener);

}
