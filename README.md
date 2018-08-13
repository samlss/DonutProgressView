# DonutProgressView
### A progress view that you can use it when you are loading picture（一个简洁的进度view）.



[![Api reqeust](https://img.shields.io/badge/api-1+-green.svg)](https://github.com/samlss/DonutProgressView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/DonutProgressView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)


<br>

![DonutProgressView](https://github.com/samlss/DonutProgressView/blob/master/screenshots/screenshot1.gif)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>


## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:DonutProgressView:1.0'
}
```

布局中使用：
```
<com.iigo.library.DonutProgressView
        android:id="@+id/dpv_loading"
        app:progress="0"
        app:progressColor="@android:color/white"
        android:layout_centerInParent="true"
        android:layout_width="50dp"
        android:layout_height="50dp" />
```

<br>

代码中使用：
```
    donutProgressView.setProgress(progress); //设置进度
    donutProgressView.setColor(Color.RED); //设置进度颜色
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| progressColor      | 进度颜色 |
| progress | 进度大小，0-100 |
<br>


## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:DonutProgressView:1.0'
}
```


in layout.xml：
```
<com.iigo.library.DonutProgressView
        android:id="@+id/dpv_loading"
        app:progress="0"
        app:progressColor="@android:color/white"
        android:layout_centerInParent="true"
        android:layout_width="50dp"
        android:layout_height="50dp" />
```

<br>

in java code：
```
 donutProgressView.setProgress(progress); //set progress
 donutProgressView.setColor(Color.RED); //set the progress color
```

<br>

Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| progressColor      | the progress color |
| progress | the progress, [0 -100] |

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/DonutProgressView/blob/master/LICENSE)
