package com.studyjun.rxandroidmvpsimple.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.studyjun.rxandroidmvpsimple.R;
import com.studyjun.rxandroidmvpsimple.model.User;
import com.studyjun.rxandroidmvpsimple.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements MainView{

    private EditText mUsername;
    private EditText mPassword;
    private ProgressDialog mProgressDialog;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = (EditText) findViewById(R.id.editText);
        mPassword = (EditText) findViewById(R.id.editText2);

        mLoginPresenter = new LoginPresenter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingDialog() {
        if (mProgressDialog==null){
            mProgressDialog = ProgressDialog.show(this,"","加载中",true,false);
        } else {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (mProgressDialog!=null&&mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }

    }

    @Override
    public void updateView(User user) {
        Toast.makeText(getApplicationContext(),user.getUsername()+"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideLoadingDialog();
        mProgressDialog=null;
    }

    /**
     * 登录
     * @param view
     */
    public void login(View view){
        mLoginPresenter.login2(mUsername.getText().toString(),mPassword.getText().toString());
    }
}
