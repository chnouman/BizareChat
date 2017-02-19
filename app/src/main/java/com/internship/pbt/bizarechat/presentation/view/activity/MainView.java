package com.internship.pbt.bizarechat.presentation.view.activity;

import com.arellomobile.mvp.MvpView;


public interface MainView extends MvpView {

    void hideEmptyScreen();

    void showEmptyScreen();

    void showLackOfFriends();

    void startNewChatView();

    void showInviteFriendsScreen();

    void showNavigationElements();

    void hideNavigationElements();

    void navigateToLoginScreen();

    void startUsersView();

    void confirmLogOut();

    void startBackPressed();

    void showDialogs();

    void showPublicDialogs();

    void showPrivateDialogs();


}
