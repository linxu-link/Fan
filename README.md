### 前言
至今为止从事Android开发两年多了，17年开始实习时，恰逢APP刚刚立项不久，当时新项目沿用了旧项目古老的MVC架构。从那之后一直都是根据飘忽不定的需求，没有规则的垒代码。直到18年中，其他项目组的APP功能也被集成到了这一个APP中，APP代码、功能爆炸性增多，项目开始变得极难维护，调试旧代码经常找不到位置，需要全局搜索连蒙带猜，也就是从那时起，我开始意识到一个好架构的重要性！

### 目录
* 项目简介
* APP概览
* 源代码使用方法
* 后续展望
* 总结
* 参考资料
### 正文
#### 一、项目简介
本次实践的内容是基于Android JetPack组件实现MVVM架构，并结合当下较为流行的组件化开发方式，编写的一个菜谱类型的小型应用。组件化的过程中结合了我把公司的APP组件化后，得到一些经验和教训，做了一些优化和调整。

实际的项目已经上架小米应用商店，欢迎搜索《饭fan》下载体验。

**需要强调的是**，实际使用了Data Binding后，不仅调试bug时想砸电脑，后来在读过nanchen大佬的文章[《不一样的角度谈 DataBinding的坑》](https://dwz.cn/s5KjNQQJ)后决定，在本次实践不采用Data Binding。

本项目中当前的MVVM架构设计的可能并不理想，而且界面优化还存在一定的问题，不过没关系，后续会以该项目为案例，系统性的实践Android中常见的启动优化、内存优化以及布局优化等等，实践过程也会完整的整理成文章记录下来，方便日后学习、讨论，如果感兴趣话记得关注哦。

**数据来源**：[聚合数据-菜谱大全](https://www.juhe.cn/docs/api/id/46)

**后台服务器**：[Bmob](https://www.bmob.cn/)

**开发语言**：Kotlin（部分使用了Java）

**主要使用的Jetpack组件**：Navigation、ViewModel、Room、LiveData、Lifecycle

**使用到的第三方框架**：App中使用了很多第三方框架，这里只列举几种，Tinker、ARouter、Glide、Rxjava、Retrofit、BaseRecyclerViewAdapterHelper等等。

**开源地址**：https://github.com/linux-link/Fan

#### 二、APP概览

![首页](https://upload-images.jianshu.io/upload_images/3146091-d3395313a99f0102?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![分类](https://upload-images.jianshu.io/upload_images/3146091-091e88293cbf55d6?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![发现](https://upload-images.jianshu.io/upload_images/3146091-2442b0fe3d0b008d?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![我的](https://upload-images.jianshu.io/upload_images/3146091-947b48ff75d2b0a8?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 三、源码结构
本项目概念上的结构如图所示

![image](https://upload-images.jianshu.io/upload_images/3146091-c25e27033a5510ec?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实际结构如图所示

![image](https://upload-images.jianshu.io/upload_images/3146091-2bbe6636d76ad995?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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

### 后续展望
当前的APP依然十分简单，或者说简陋，当前正在学习如何部署、开发一个简单的后台，后面会逐渐放弃bmob，同时增加APP的复杂度，引入更多的开发技术，比如我最感兴趣的插件化、NDK、React Native或者Flutter等等。

最终目的还是希望能在一个APP实践当下的主流开发技术，然后从中选择一个方向深入研究，不过这都是后话了。毕竟理想很丰满，现实很骨干=_=。

### 四、总结
以上就是本项目的大致概览，总得来说，你既可以用它来学习组件化开发，也可以用它来学习Android Jetpack组件的实际运用，还可以用来学习如何优化一个APP，不过这等我后续的更新了，布局优化中工具的使用，已经先行发布了，请移步[「Android布局优化 1」布局优化工具的使用](https://www.jianshu.com/p/aa2bf172a7ae)。

如有任何问题、建议请给我留言或者在github中提交issue。感谢您的阅读，欢迎下载体验。
![下载二维码.png](https://upload-images.jianshu.io/upload_images/3146091-e51928b12b0583f7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 五、参考资料
[《如何构建Android MVVM 应用框架》](https://tech.meituan.com/2016/11/11/android-mvvm.html)

[《Android 组件化最佳实践》](https://dwz.cn/lOeaiPi8)

[《来自一位阿里朋友的组件化架构实践》](https://dwz.cn/rPM80kL7)


