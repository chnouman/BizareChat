package com.internship.pbt.bizarechat.presentation.presenter.login;

import android.util.Log;

import com.internship.pbt.bizarechat.data.net.requests.UserRequestModel;
import com.internship.pbt.bizarechat.data.repository.SessionDataRepository;
import com.internship.pbt.bizarechat.data.repository.UserToken;
import com.internship.pbt.bizarechat.domain.interactor.GetTokenUseCase;
import com.internship.pbt.bizarechat.domain.interactor.LoginUserUseCase;
import com.internship.pbt.bizarechat.domain.interactor.ResetPasswordUseCase;
import com.internship.pbt.bizarechat.domain.interactor.UseCase;
import com.internship.pbt.bizarechat.domain.model.Session;
import com.internship.pbt.bizarechat.domain.model.UserLoginResponse;
import com.internship.pbt.bizarechat.presentation.exception.ErrorMessageFactory;
import com.internship.pbt.bizarechat.presentation.model.CurrentUser;
import com.internship.pbt.bizarechat.presentation.util.Validator;
import com.internship.pbt.bizarechat.presentation.view.fragment.login.LoginView;

import retrofit2.Response;
import rx.Subscriber;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private ResetPasswordUseCase resetPasswordUseCase;
    private Validator validator = new Validator();
    private UseCase loginUseCase;
    private UseCase sessionRequestUseCase;

    public LoginPresenterImpl(ResetPasswordUseCase resetPasswordUseCase) {
        this.resetPasswordUseCase = resetPasswordUseCase;
    }

    @Override
    public void requestLogin(String email, String password) {
        this.sessionRequestUseCase = new GetTokenUseCase(new SessionDataRepository());
        sessionRequestUseCase.execute(new Subscriber<Session>() {
            @Override
            public void onCompleted() {
                loginUseCase(email, password);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Session session) {
                UserToken.getInstance().saveToken(session.getToken());
            }
        });
    }

    @Override
    public void checkIsEmailValid(String email) {
        if (validator.isValidEmail(email)) {
            resetPasswordUseCase.setEmail(email);

            resetPasswordUseCase.execute(new Subscriber<Response<Void>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    String message = ErrorMessageFactory.
                            createMessageOnLogin(loginView.getContextActivity(), e);
                    loginView.showError(message);
                }

                @Override
                public void onNext(Response<Void> o) {
                    loginView.showSuccessOnPasswordRecovery();
                }
            });
        } else {
            loginView.showErrorOnPasswordRecovery();
        }
    }

    @Override
    public void showViewLoading() {
        loginView.showLoading();
    }

    @Override
    public void hideViewLoading() {
        loginView.hideLoading();
    }

    @Override
    public void setLoginView(LoginView view) {
        loginView = view;
    }

    @Override
    public void goToRegistration() {
        loginView.navigateToRegistration();
    }

    @Override
    public void onPasswordForgot() {
        loginView.showForgotPassword();
    }

    @Override
    public void checkFieldsAndSetButtonState(String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            loginView.setButtonSignInEnabled(false);
        else
            loginView.setButtonSignInEnabled(true);
    }

    private void loginUseCase(String email, String password) {
        this.loginUseCase = new LoginUserUseCase(new SessionDataRepository(),
                new UserRequestModel(email, password));

        Log.d("321", "request Login. TOKEN = " + UserToken.getInstance().getToken());

        this.loginUseCase.execute(new Subscriber<UserLoginResponse>() {
            @Override
            public void onCompleted() {
                Log.d("321", "request Login OnCompleted()");
                CurrentUser.getInstance().setAuthorized(true);
                CurrentUser.getInstance().setCurrentEmail(email);
                CurrentUser.getInstance().setCurrentPasswrod(password);
                onLoginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                String message = ErrorMessageFactory.
                        createMessageOnLogin(loginView.getContextActivity(), e);
                loginView.showError(message);
                e.printStackTrace();
                Log.d("321", "request Login OnError() + " + e.toString());

            }

            @Override
            public void onNext(UserLoginResponse userLoginResponse) {
            }
        });
    }

    @Override
    public void onKeepMeSignInFalse() {
        loginView.showCheckBoxModalDialog();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        loginView = null;
        if (loginUseCase != null)
            loginUseCase.unsubscribe();
        if (sessionRequestUseCase != null)
            sessionRequestUseCase.unsubscribe();
        if (resetPasswordUseCase != null)
            sessionRequestUseCase.unsubscribe();
    }

    @Override
    public void onLoginSuccess() {
        loginView.onLoginSuccess();
    }
}
