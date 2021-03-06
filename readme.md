# Android Agilie Internship

Hey there, a couple of months ago (end of 2016) we’ve announced an [internship program for Android developers](https://agilie.com/ru/internship). 
During November 2016 we’ve gotten hundreds of applications and selected 12 young and smart students. 
Guys have worked hardly a few months to contribute a chat Android app. We split them into 4 teams with 3 developers on each team. 

The process included a couple of local meetings and a lot of remote consulting chats where we discussed the product requirements, an architecture, list of libraries and other technical terms. 

By the end of the internship we’ve made a local event to prove own projects, review the results and select a winner. As one of the credits we decided to publish a winning project on a Github. 

So let us intro **BizareChat**! 

As a general technical requirements we’ve agreed to develop an Android chat app. 
We provided the functional requirements and general recommendations on UI/UX.

### Project description

Bizare Android chat app has the most common features like: 
- Users and friends; 
- Auth & Facebook; 
- Chat messages and all the actions like remove and edit; 
- Push messages and local data cache.  


### Screenshots

<img src="/images/1page-1-sign-in.png" width="24%"> <img src="/images/2page-1-sign-up.png" width="24%"> <img src="/images/3page-1-chats-menu.png" width="24%"> <img src="/images/4page-1-chats-error.png" width="24%">
<img src="/images/5page-1-drawer.png" width="24%"> <img src="/images/6page-1-create-chat.png" width="24%"> <img src="/images/7page-1-invite-friends.png" width="24%"> <img src="/images/8page-1-friends-copy.png" width="24%">

## Project Specification

### Project Requirements

- OS: Android (min API 19)
- Device types: mobile only
- Orientation support: portrait + landscape
- domain: \<your company name>.internship.com
- localization: en

### Backend API (QuickBlox)

Backend is provided as [QuickBlox](https://quickblox.com) service. 

* QuickBlox Admin panel:
https://admin.quickblox.com

* Backend API documentation: 
http://quickblox.com/developers/Overview

* Error codes and rate limits API documentation:
http://quickblox.com/developers/Errors

* Authentication and Authorization API documentation:
http://quickblox.com/developers/Authentication_and_Authorization

* Users API documentation:
http://quickblox.com/developers/Users

* Chat API documentation:
http://quickblox.com/developers/Chat

* Content API documentation:
http://quickblox.com/developers/Content

* Push notifications API documentation:
http://quickblox.com/developers/Messages

### Functional requirements

If you’re interested on getting the requirements to the project, feel free to send an email to info@agilie.com

### Design requirements

Application’s UI should be implemented based on Material Design principles. Next points should be taken into consideration during app’s design implementation:
* use Material Theme
* use native Android SDK’s UI widgets
* ripple effect should be implemented for all clickable elements (rows, buttons, clickable texts etc) for Android 5.0+
selected/unselected/enabled/disabled states should be implemented for all clickable elements for Android with version lower than 5.0
* use animations for changing UI elements’ properties (i. e. show/hide, move). Switching between screens should be also animated.
* User should be notified in case of background processes (network, db operations). Use loading indicators. Do not block UI with loading elements, User should always have possibility to navigate the app 
* User should be notified in case of any kind of errors occur
* Use Crouton and Toast UI Widgets

## About project

### Architecture specs

In this project our team adhere to [Clean Architecture](https://github.com/android10/Android-CleanArchitecture) principles. It means that we've made 3 layers which perform their specific task.

Presentation:
Here we've used [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) pattern, which allows us to separate view elements from the logic and retrieving data.
In _View_ we contain only View elements, lists, specific Android parts which need to be displayed to user. Also we have reference on Presenter class.
In _Model_ we've implemented retrieving of data with specific methods which needs Context for perform.
In _Presenter_ we contain references on Model, View and UseCase classes. Here we implement all the logic that is required for concrete View.

Domain:
In this layer we have UseCase classes, every particular class implements one event that allows us to plan architecture and add every feature without any conflict.

Data:
In this layer we've implemented retrieving data from net, database, cache or another source. Also, in this layer we've implemented choosing of source. For example if we have no cached data, we fetch one from network.

### Used Technologies

* Retrofit 2
https://github.com/square/retrofit
* Okhttp
https://github.com/square/okhttp
* RxAndroid
https://github.com/ReactiveX/RxAndroid
* Smack
https://github.com/igniterealtime/Smack
* Moxy
https://github.com/Arello-Mobile/Moxy
* Google-Cloud-Messaging
https://github.com/google/gcm
* EventBus
https://github.com/greenrobot/EventBus
* Swipe layout
https://github.com/daimajia/AndroidSwipeLayout
* GreenDAO
https://github.com/greenrobot/greenDAO
* SqlCipher
https://github.com/sqlcipher/android-database-sqlcipher
* Mockito
https://github.com/mockito/mockito
* Powermock
https://github.com/powermock/powermock





### License

[Apache-2.0](https://github.com/ukevgen/BizareChat/blob/master/LICENSE.txt)
