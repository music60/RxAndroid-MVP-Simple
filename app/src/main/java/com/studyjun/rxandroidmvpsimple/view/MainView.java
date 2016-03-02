package com.studyjun.rxandroidmvpsimple.view;

import com.studyjun.rxandroidmvpsimple.model.User;

/**
 * Created by studyjun on 2016/3/2.
 */
public interface MainView {

    void showLoadingDialog();

    void hideLoadingDialog();

    void updateView(User user);

    void showError(String msg);
}
