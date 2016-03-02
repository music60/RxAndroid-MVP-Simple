package com.studyjun.rxandroidmvpsimple.presenter;

import com.studyjun.rxandroidmvpsimple.model.LoginModel;
import com.studyjun.rxandroidmvpsimple.model.User;
import com.studyjun.rxandroidmvpsimple.view.MainView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

/**
 * Created by studyjun on 2016/3/2.
 */
public class LoginPresenter {

    private MainView mMainView;
    private LoginModel mLoginModel;

    public LoginPresenter(MainView mainView) {
        this.mMainView = mainView;
        mLoginModel = new LoginModel();
    }

    /**
     * 登录
     * 没有使用RxAndroid下的MVP
     *
     * @param username
     * @param password
     */
    public void login(String username,String password){
        mMainView.showLoadingDialog();
        mLoginModel.login(username,password,this);
    }


    /**
     * 登录
     * 使用RxAndroid下的mvp
     *
     * @param username
     * @param password
     *
     */
    public void login2(String username,String password){
        mMainView.showLoadingDialog();
        mLoginModel.login2(username,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<User>(){

            @Override
            public void onCompleted() {
                mMainView.hideLoadingDialog();
            }

            @Override
            public void onError(Throwable e) {
                LoginPresenter.this.onError(e.getMessage());
            }

            @Override
            public void onNext(User user) {
                mMainView.updateView(user);
            }
        });
    }


    /**
     * 登录出错
     * @param msg
     */
    public void onError(String msg){
        mMainView.hideLoadingDialog();
        mMainView.showError(msg);
    }


    /**
     * 登录成功
     * @param user
     */
    public void onSuccess(User user){
        mMainView.hideLoadingDialog();
        mMainView.updateView(user);
    }
}
