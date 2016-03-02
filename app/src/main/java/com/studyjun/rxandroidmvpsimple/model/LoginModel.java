package com.studyjun.rxandroidmvpsimple.model;


import android.os.Handler;

import com.studyjun.rxandroidmvpsimple.presenter.LoginPresenter;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by studyjun on 2016/3/2.
 */
public class LoginModel {


    /**
     * 登录
     * 没有使用RxAndroid下的mvp
     *
     * @param username
     * @param password
     * @param loginPresenter
     */
    public void login(final String username, final String password, final LoginPresenter loginPresenter) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (username == null || "".equals(username)) {
                    loginPresenter.onError("用户名为空");
                } else if (password == null || "".equals(password)) {
                    loginPresenter.onError("请输入密码");
                } else {
                    loginPresenter.onSuccess(new User(username, password));
                }
            }
        }, 2000);
    }


    /**
     * 使用RxAndroid 下的mvp
     * @param username
     * @param password
     * @return
     */
    public Observable<User> login2(final String username, final String password) {
        Observable<User> user = Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (username == null || "".equals(username)) {
                    subscriber.onError(new Throwable("用户名为空"));
                } else if (password == null || "".equals(password)) {
                    subscriber.onError(new Throwable("请输入密码"));
                } else {
                    User u = new User(username, password);
                    subscriber.onNext(u);
                    subscriber.onCompleted();
                }
            }
        });

        return user;
    }
}
