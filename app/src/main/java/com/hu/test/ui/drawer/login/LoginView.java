package com.hu.test.ui.drawer.login;

/**
 * Created by TT on 2017/7/6.
 */

public interface LoginView  {
    void showProgress();

    void hideProgress();

    void setUserNameError();

    void setUserPasswrodError();

    void loginSucess();
}
