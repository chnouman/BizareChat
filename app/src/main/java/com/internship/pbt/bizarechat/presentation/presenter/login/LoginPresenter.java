package com.internship.pbt.bizarechat.presentation.presenter.login;


import com.internship.pbt.bizarechat.presentation.presenter.Presenter;
import com.internship.pbt.bizarechat.presentation.view.fragment.login.LoginView;

public interface LoginPresenter extends Presenter {

    void setLoginView(LoginView view);

    void requestLogin(String email, String password);

    void goToRegistration();

    void onPasswordForgot();

    void checkFieldsAndSetButtonState(String email, String password);

    void checkIsEmailValid(String email);

    void onKeepMeSignInFalse();
}
