
[![Release](https://jitpack.io/v/Qase/power-optimization-survivor-android.svg)](https://jitpack.io/#Qase/power-optimization-survivor-android)
[![Build Status](https://travis-ci.org/Qase/power-optimization-survivor-android.svg?branch=master)](https://travis-ci.org/Qase/power-optimization-survivor-android)
[![codebeat badge](https://codebeat.co/badges/5a711533-9d78-4c53-ac9a-c27ff79593f8)](https://codebeat.co/projects/github-com-qase-power-optimization-survivor-android-master)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maintainer: balakz](https://img.shields.io/badge/Maintainer-balakz-purple.svg)](mailto:balakz@quanti.cz)
[![Qase: AndroidAppSkeleton](https://img.shields.io/badge/Qase-Power%20optimization%20survivor-ff69b4.svg)](https://github.com/Qase/power-optimization-survivor-android)

# Power optimization survivor
Android keeps pushing power optimization in order to protect user from battery draining apps. Unfortunatelly, some apps need to be running on the backround in order to fulfil their core functionality. In adition to the core power optimization, vendors are coming up with their own power optimization solutions, which are harder to find. This library was created to help app developers guide users to perform correct settings in order for the app to survive batery optimization features.

# Installation
### Gradle
**Step 1.**  Add the JitPack repository to your build file
```css
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
**Step 2.**  Add the dependency
```css
dependencies {
	implementation 'com.github.qase:power-optimization-survivor-android:0.1-alpha'
}
```
### Maven
**Step 1.**  Add the JitPack repository to your build file
```markup
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
**Step 2.**  Add the dependency
```markup
	<dependency>
	    <groupId>com.github.qase</groupId>
	    <artifactId>power-optimization-survivor-android</artifactId>
	    <version>0.1-alpha</version>
	</dependency>
```

## License
[Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
