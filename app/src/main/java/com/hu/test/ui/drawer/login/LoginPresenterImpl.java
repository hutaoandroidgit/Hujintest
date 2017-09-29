package com.hu.test.ui.drawer.login;

/**
 * Created by TT on 2017/7/6.
 */

public class LoginPresenterImpl implements LoginInteractor.OnLoginFinishedListener,LoginPresenter {


    private  LoginInteractor loginInteractor;
    private  LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();//多态，对外解耦
    }

    @Override
    public void onUserNameError() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.setUserNameError();
        }
    }

    @Override
    public void onPasswordError() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.setUserPasswrodError();
        }
    }

    @Override
    public void onSucess() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.loginSucess();
        }

    }

    @Override
    public void onDestory() {
        loginView = null;
    }

    @Override
    public void validateCredentials(String userName, String password) {
        //验证开始，这里就开始调用view的交互

        if(loginView!=null){
            loginView.showProgress();
        }
        loginInteractor.Login(userName,password,this);


    }
}
