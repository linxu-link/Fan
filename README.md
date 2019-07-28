### 前言
至今为止从事Android开发两年多了，17年开始实习时，恰逢项目刚刚立项，当时新项目沿用了15年旧项目的MVC架构，从那之后一直都是根据飘忽不定的需求敲代码。直到18年中，随着功能的增多，项目变得极难维护，调试旧代码经常找不到位置，也就是从那时起，我开始意识到一个好架构的重要性！

本篇文章主要介绍，我在把公司的项目组件化后得到的一些经验，并结合Android Jetpack实现组件化MVVM架构的一次尝试，其中MVVM架构设计得或许并不恰当，不过没关系，后续会逐步优化。

需要说明的是，实际使用了databinding后，不仅调试bug时想砸电脑，后来在读过nanchen大佬的文章[《不一样的角度谈 DataBinding的坑》](https://dwz.cn/s5KjNQQJ)后决定，在本次MVVM的组件化尝试不会采用Data Binding。

同时项目本身的代码也没有刻意的优化，因为在后续的文章中会以该项目为主，系统性的讲解在Android开发中常用启动优化、内存优化以及布局优化。如果感兴趣的话请关注我哦。

开源地址：https://github.com/linux-link/Fan

下载体验：小米应用商店搜索《饭fan》

### 目录
* 项目简介
* APP概览
* 源代码使用方法

### 正文
#### 一、项目简介
本次实践的项目是一个菜谱类型的APP。

数据来源：[聚合数据-菜谱大全](https://www.juhe.cn/docs/api/id/46)

后台服务器：[Bmob](https://www.bmob.cn/)

开发语言：Kotlin（部分使用了Java）

主要使用的Jetpack组件：Navigation、ViewModel、Room、LiveData、Lifecycle

第三方框架：App中使用了很多第三方框架，全部放在LibraryBase中用于集中管理，这里只列举几种-Tinker、ARouter、Glide、Rxjava、Retrofit、BaseRecyclerViewAdapterHelper等等。


#### 二、APP概览

![首页](https://user-gold-cdn.xitu.io/2019/7/28/16c37c87bf86bf75?w=300&h=533&f=png&s=203386)

![分类](https://user-gold-cdn.xitu.io/2019/7/28/16c37c8f577c2e59?w=300&h=533&f=png&s=36833)

![发现](https://user-gold-cdn.xitu.io/2019/7/28/16c37c94d9581f8e?w=300&h=533&f=png&s=243837)

![我的](https://user-gold-cdn.xitu.io/2019/7/28/16c37c98edc992fa?w=300&h=533&f=png&s=48005)

#### 三、源码结构
本项目概念上的结构如图所示


![](https://user-gold-cdn.xitu.io/2019/7/28/16c37be12644b795?w=1184&h=1214&f=png&s=104514)

实际结构如图所示

![](https://user-gold-cdn.xitu.io/2019/7/28/16c37caa1744353e?w=400&h=768&f=png&s=126463)

下面分别解释各个module的作用：

* Library-Base

    整个项目的基类，所有的第三方框架和自定义View框架都在这里添加依赖，以方便统一管理。
* View_Xxx
    
    通用的自定义View框架。一些APP特有的view效果可以抽出独立成一个Module。例如：本项目使用了一个仿红板报的3D翻页效果库，就可以把它独立成一个module。

* General_Xxx

    通用的自定义工具框架。一些所有项目可以公用的框架，为了方便日后在其他项目中使用，可以抽出独立成一个module，不能与项目的业务逻辑有关联。例如：本项目中网络框架就被单独封装成一个module。
* Library-Architecture
    
    通用框架库。一般包含BaseApplication、常用的工具类以及架构层的封装例如BaseMvvmActivity等等。
    
    这个module在封装时需要考虑一定的通用性，最理想的情况的是，在重新开一个新的项目时，可以直接拷贝使用。
* Library-Component

    服务于组件化的module。与业务逻辑相关，主要包含一些组件化的封装，一些子组件需要抽出的公共类也会放在这里。需要注意的是，Library-Component是其他所有子组件必需依赖的库。
* Component_Xxx

    子组件。APP中各个业务逻辑的具体实现，既可以编译为library，用作其他组件的类库，也可以编译为application，可以独立运行调试。
    
    在项目根目录的build.gradle可以通过xxx_isSingleCompile的值来控制各个module是否需要独立运行。
    

* app

    整个项目的入口，又被成为APP壳，在这里面将所有的子组件全部打包进apk中，，为了加快编译速度，主要使用runtimeOnly。
    
需要单独说明的是，每一个子组件中还包含了一个gradle.properties文件，里面设定了该组件被编译成AAR库时一些属性，以及私有maven仓库的地址和用户名与密码。

后面可以将子组件编译一个aar库，然后通过maven_push.gradle上传到私有maven仓库里，不过本项目只完成了一部分，关键的私有maven仓库还没有搭建好，后续会陆续处理。

