# Skin-Loader
> 一个动态加载皮肤资源的换肤框架。  
> 支持 Material Design 风格控件一键换肤

## 示例图
<img width="320" height="500" src="https://github.com/LidongWen/Skin-Loader/blob/master/art/GIF.gif"></img> 


## 用法
```groovy
// 项目引用
dependencies {
    compile 'com.github.LidongWen:Skin-Loader:0.0.2'
}

// 根目录下引用
repositories {
    jcenter()
    maven { url "https://www.jitpack.io" }
}
```
### 1. 在Application中进行初始化
```
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(getApplicationContext());
    }
}
```
### 2.继承 public class MainActivity extends SkinActivity
```
public class MainActivity extends SkinActivity
{
    ...
}
```

### 3.设置皮肤
```
  SkinManager.getInstance().loadSkin(path);
```
### 4.恢复默认皮肤
```
 SkinManager.getInstance().restoreDefault();
```
###  5.自定义控件，回调
```
public class MainActivity extends SkinActivity{
    ...
    @Override
    public void changeSkin(SkinResource skinResource) {
        customView.setPaintColor(skinResoure.getColorStateList(this,R.id.xxxx));
        ...
    }
}

```

### 6.设置控件不替换皮肤
在布局中 设置 enable_skin
```
...
xmlns:skin="http://schemas.android.com/android/skin"
...
  <TextView
     ...
     skin:enable_skin="false" 
     ... />
```
###  7. 皮肤包是什么？如何生成？

皮肤包（后缀名为.skin）的本质是一个apk文件，该apk文件不包含代码，只包含资源文件。
项目中 skinwrapper 编译完成后的 apk 后缀为 .skin 就是一个皮肤包  
![](https://github.com/LidongWen/Skin-Loader/blob/master/art/clipboard.png)

## 支持控件、属性
#### 基础控件
> android:textColor  
> android:src  
> android:background  
#### Material Design 控件
> **CardView**  
> >cardBackgroundColor     

> **Toolbar**
  > >subtitleTextColor  
  titleTextColor  
  PopupTheme

> **FloatingActionButton**
> > backgroundTint  
rippleColor  
srcCompat


> **TabLayout**
> > tabBackground  
tabIndicatorColor  
tabSelectedTextColor  
tabTextColor

> **CollapsingToolbarLayout**
>> contentScrim

> ## **0.0.2** 
>  修复找不到dialog资源 崩溃的 bug 
> ## **0.0.1**

# Contact me
##### E-mail:wenld2014@163.com
##### github: [LidongWen](https://github.com/LidongWen)
##### 人生得意须尽欢, 桃花坞里桃花庵
##### 点个关注呗，[对，不信你点试试？](http://www.jianshu.com/users/99f514ea81b3/timeline)
