# Relief Supports Project (Android)

This is a community-driven Open Source project to support relief support activities conducted by volunteers.
This project was originally created to support flood relief activities in Sri Lanka in May 2017.

We welcome your innovative ideas and suggestions to make a better solution for the community.

http://reliefsupports.org

## Contribution Guide

### Setup the development environment

* Fork and  clone the project repository.
* Follow the following guide to setup `react-native`: https://facebook.github.io/react-native/docs/getting-started.html
* `cd` into the project folder and run the following command to install the dependencies:

```
yarn install
```
**Always use `yarn` not `npm`**

* Next, run the following command to start the `react-native` packager:

```
react-native start
```

* Finally, compile and run the android app (Make sure you have the android emulator running or a device attached to the machine):

```
react-native run-android
```

### Pull requests

Send all the PRs to `dev` branch. We keep `master` and `prod` branches only for final releases and all the development works on the `dev`.
