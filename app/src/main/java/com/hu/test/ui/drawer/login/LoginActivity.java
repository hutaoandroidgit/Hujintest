package com.hu.test.ui.drawer.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hu.test.MainActivity;
import com.hu.test.R;
import com.hu.test.ui.BaseActivity;

/**
 * Created by TT on 2017/7/6.
 * 实现了需要View层次交互的接口
 * 在实现的方法中，填写交互
 */

public class LoginActivity extends BaseActivity implements LoginView ,View.OnClickListener{

    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;
    private LoginPresenter loginPresenter;
    private Button btnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        pbLogin = (ProgressBar) findViewById(R.id.progress);
        btnReset = (Button) findViewById(R.id.btn_reset);


        loginPresenter = new LoginPresenterImpl(this);

        btnReset.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameError() {
        etName.setError("用户名错误");

    }

    @Override
    public void setUserPasswrodError() {
        etPassword.setError("密码错误");
    }

    @Override
    public void loginSucess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                loginPresenter.validateCredentials(etName.getText().toString(),etPassword.getText().toString());
                break;
            case R.id.btn_reset:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }
}
