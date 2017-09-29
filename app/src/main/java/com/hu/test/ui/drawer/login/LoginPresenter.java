package com.hu.test.ui.drawer.login;

/**
 * Created by TT on 2017/7/6.
 */

public interface LoginPresenter {
    void onDestory();

    void validateCredentials(String userName , String password);
}
