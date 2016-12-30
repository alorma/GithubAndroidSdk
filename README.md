# DEPRECATED



[![Build Status](https://travis-ci.org/gitskarios/GithubAndroidSdk.svg)](https://travis-ci.org/gitskarios/GithubAndroidSdk)

[ ![Download](https://api.bintray.com/packages/alorma/maven/github-sdk/images/download.svg) ](https://bintray.com/alorma/maven/github-sdk/_latestVersion)

#GithubAndroidSdk

[![Join the chat at https://gitter.im/gitskarios/GithubAndroidSdk](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/gitskarios/GithubAndroidSdk?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## USAGE

You should be using `jCenter()` as a repository for your libraries, if not, add this to your main `build.gradle`:

``` groovy

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
    classpath 'com.android.tools.build:gradle:X.X.X'
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

```

Or add repository url in your top-level `build.gradle`

``` groovy
maven {
    url  "http://dl.bintray.com/alorma/maven"
}
```

Add dependency in your project `build.gradle`

``` groovy
    compile 'com.github.alorma:github-sdk:3.2.5'
```

Or grab it via maven
``` xml
<dependency>
    <groupId>com.github.alorma</groupId>
    <artifactId>github-sdk</artifactId>
    <version>3.2.2</version>
    <type>apklib</type>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>com.github.alorma</groupId>
    <artifactId>github-sdk</artifactId>
    <version>3.2.2</version>
    <type>jar</type>
    <scope>provided</scope>
</dependency>
```

## INIT

```
    TokenProvider.setTokenProviderInstance(new TokenProviderInterface() {
      @Override
      public String getToken() {
        ...
      }
    });

    UrlProvider.setUrlProviderInstance(new UrlProviderInterface() {
      @Override
      public String getUrl() {
        ...
      }
    });

    UsernameProvider.setUsernameProviderInterface(new UsernameProviderInterface() {
      @Override
      public String getUsername() {
        ...
      }
    });

    LogProvider.setTokenProviderInstance(new LogProviderInterface() {
      @Override
      public void log(String message) {
        ...
      }
    });
```

## EXAMPLE

Get repositories list:
```java
GithubReposClient client = new UserReposClient(getActivity(), username);
client.observable()
.observeOn(AndroidSchedulers.mainThread()
.subscribeOn(Schedulers.io())
.subscribe( ... );
```

Paginated:
```java
UserReposClient client = new UserReposClient(getActivity(), username, page);
client.observable()
.observeOn(AndroidSchedulers.mainThread()
.subscribeOn(Schedulers.io())
.subscribe( ... );
```

##LICENSE

The MIT License (MIT)

Copyright (c) 2016 Bernat Borrás Paronella

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
