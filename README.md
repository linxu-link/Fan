### 前言
至今为止从事Android开发两年多了，17年开始实习时，恰逢APP刚刚立项不久，当时新项目沿用了旧项目古老的MVC架构。从那之后一直都是根据飘忽不定的需求，没有规则的垒代码。

直到18年中，其他项目组开发的APP要求集成到了我们项目组的APP中，从这时开始项目的代码、APP的功能，爆炸性增多，代码本身也开始变得极难维护，调试旧代码经常找不到位置，需要全局搜索有时还要连蒙带猜，也就是从那时起，我开始意识到一个好架构以及规范的重要性！

### 目录
* 项目简介
* APP概览
* 项目结构
* 组件复用
* 后续展望
* 总结
* 参考资料
### 正文
#### 一、项目简介
本次实践的内容是基于Android JetPack组件实现MVVM架构，并结合当下较为流行的组件化开发方式，编写的一个菜谱类型的小型应用。组件化的过程中结合了我把公司的APP组件化后，得到一些经验和教训，做了一些优化和调整。

**需要强调的是**，实际使用了Data Binding后，不仅调试bug时想砸电脑，后来在读过nanchen大佬的文章[《不一样的角度谈 DataBinding的坑》](https://dwz.cn/s5KjNQQJ)后决定，在本次实践不采用Data Binding。

**项目当前的MVVM架构设计的可能并不理想，而且界面优化还存在一定的问题**，不过没关系，后续会以该项目为案例，系统性的实践Android中常见的启动优化、内存优化以及布局优化等等，实践过程也会完整的整理成文章记录下来，方便日后学习、讨论，如果感兴趣话记得关注哦。

**数据来源**：[聚合数据-菜谱大全](https://www.juhe.cn/docs/api/id/46)、[Easy-Mock](https://www.easy-mock.com/)

**后台服务器**：[Bmob](https://www.bmob.cn/)（使用了bmob用户管理的部分api，其他数据主要使用easy-mock产生模拟数据）

**开发语言**：Kotlin（部分使用了Java）

**主要使用的Jetpack组件**：
LiveData-[Android Architecture Components探索（1）-LiveData](https://www.jianshu.com/p/4e8424a51735)
ViewModel-[Android Architecture Components探索（2）-ViewModel](https://www.jianshu.com/p/879036c1876b)
Navigation-[Android Architecture Components探索（3）-Navigation](https://www.jianshu.com/p/aced993cb990)
Room、Lifecycle

**使用到的第三方框架**：App中使用了很多第三方框架，这里只列举几种，Tinker（热修复）、ARouter、Glide、RxJava、Retrofit、BaseRecyclerViewAdapterHelper、X5WebView等等。

**开源地址**：https://github.com/linux-link/Fan

#### 二、APP概览

![首页](https://upload-images.jianshu.io/upload_images/3146091-b33d807d59b9f60a?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![分类](https://upload-images.jianshu.io/upload_images/3146091-980b2327d3c7e202?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![发现](https://upload-images.jianshu.io/upload_images/3146091-a907536a1f474d71?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![我的](https://upload-images.jianshu.io/upload_images/3146091-1498baf1c33349a5?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 三、项目结构
本项目概念上的结构如图所示

![结构图](https://upload-images.jianshu.io/upload_images/3146091-1531d755f90d807b?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

实际结构如图所示

![结构图](https://upload-images.jianshu.io/upload_images/3146091-8dc5746b4562f0be?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

下面分别解释各个module的作用：

- **Library-Base**

    整个项目的基类，所有的第三方框架和自定义View框架都在这里添加依赖，以方便统一管理。
- **View_Xxx**
    
    通用的自定义View框架。一些APP特有的view效果可以抽出独立成一个Module。例如：本项目使用了一个仿红板报的3D翻页效果库，就可以把它独立成一个module。

- **General_Xxx**

    通用的自定义工具框架。一些所有项目可以公用的框架，为了方便日后在其他项目中使用，可以抽出独立成一个module，不能与项目的业务逻辑有关联。例如：本项目中网络框架就被单独封装成一个module。
    
- **Library-Architecture**
    
    通用框架库。一般包含BaseApplication、常用的工具类以及架构层的封装例如BaseMvvmActivity等等。
    
    这个module在封装时需要考虑一定的通用性，最理想的情况的是，在重新开一个新的项目时，可以直接拷贝使用。
- **Library-Component**

    服务于组件化的module。与业务逻辑相关，主要包含一些组件化的封装，一些子组件需要抽出的公共类也会放在这里。需要注意的是，Library-Component是其他所有子组件必需依赖的库。
    
- **Component_Xxx**

    子组件。APP中各个业务逻辑的具体实现，既可以编译为library，用作其他组件的类库，也可以编译为application，可以独立运行调试。
    
    在项目根目录的build.gradle可以通过xxx_isSingleCompile的值来控制各个module是否需要独立运行。
    

- **app**

    整个项目的入口，又被成为APP壳，在这里面将所有的子组件全部打包进apk中，为了加快编译速度，主要使用runtimeOnly。
    
需要单独说明的是，每一个子组件中还包含了一个gradle.properties文件，里面设定了该组件被编译成AAR库时一些属性，以及私有maven仓库的地址和用户名与密码。
### 四、组件复用（2019-08-22 更新）
组件化的一个重要目的就是能够在另一个项目中复用当前APP的一些组件，就像我们在项目中使用implementation引入一些第三方库一样，这些第三方库也可以当作一个组件。不过为了隐私的需要，我们多数时候是在公司的内网使用nexus搭建一个私有的maven仓库。

关于如何搭建maven仓库就不再介绍了，请自行百度，这里我在华为云服务器上搭建了一个公用的maven仓库（自行搭建maven仓库时，尽量使用内存大于2GB的主机），公网地址：[http://119.3.215.243:9882](http://119.3.215.243:9882/)，用户名：public，密码：123456@qq.com。

有了maven仓库，接下来我们就可以尝试将组件打包并上传到maven仓库中，给别项目使用。

#### 1.编译aar类库并上传maven仓库
- **选择一个module，执行build命令，将这个module编译成一个aar类库。编译好的aar文件，一般在对应module的build->outputs->aar下**
![执行脚本](https://upload-images.jianshu.io/upload_images/3146091-8fdb49e6c512e302?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- **在gradle.properties中配置maven仓库地址，以及组件的其他属性**
![配置属性](https://upload-images.jianshu.io/upload_images/3146091-94d159fd8f9ea476?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- **执行upload脚本，将aar类库上传到maven仓库中**
![上传到maven仓库](https://upload-images.jianshu.io/upload_images/3146091-ebd3d5c6e93c93f3?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


控制台出现successful，表示上传成功
![上传成功](https://upload-images.jianshu.io/upload_images/3146091-3b4cb928c8e58846?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![maven仓库](https://upload-images.jianshu.io/upload_images/3146091-3354b90067032b68?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

接下来就是如何在当前项目或其他项目中使用这个aar的类库

#### 2.使用aar类库
- **在根部目录的version.gradle中添加私有maven仓库的地址**
![引入私有maven仓库的地址](https://upload-images.jianshu.io/upload_images/3146091-c60a6cb6a4bce24c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- **使用在gradle.properties配置的唯一项目标识，引入aar类库**
![统一配置aar类库的地址](https://upload-images.jianshu.io/upload_images/3146091-08e93c5d5647cc1c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![修改引入类库的地址](https://upload-images.jianshu.io/upload_images/3146091-efe6faf810d8018c?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- **在setting.gradle中，把一些已经编译成aar类库module注释掉**
![注释掉源码形式的module](https://upload-images.jianshu.io/upload_images/3146091-5d25495a9b69df15?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

通过以上这些步骤，我们实现了组件的本地复用以及不同项目间的复用，而且因为大量的组件被编译成了aar类库，项目全编译时，这部分组件就不需要再进行编译，大幅提高了项目的编译效率。

### 五、后续展望
当前的APP依然十分简单，或者说简陋，当前正在学习如何部署、开发一个简单的后台，后面会逐渐放弃bmob，同时增加APP的复杂度，引入更多的开发技术，比如我最感兴趣的插件化、NDK、React Native或者Flutter等等。
最终目的还是希望能在一个APP中实践当下的主流开发技术，然后从中选择一个方向深入研究，不过这都是后话了。毕竟理想很丰满，现实很骨干=_=。

**2019-08-22更新**
- 1.0.3版开始，在APP中集成了一个独立进程的简易商城，商城使用H5开发，支付功能使用Native开发，商城本质上是一个多进程Hybird架构的组件。

H5部分是根据慕课网的一个vue.js课程中使用的源码改造的，所以源代码无法公开。不过我同样架设了一个可以在公网访问的地址：[http://118.24.197.176](http://118.24.197.176)，因为数据需要使用APP的内置网络服务获取，所以在浏览器中访问这个地址是缺失部分数据的。

**2019-08-23更新**
- 1.0.4版集成了美团walle多渠道打包，多渠道打包最重要的意义在于后台统计，打出不同的渠道包，网络请求时带上渠道标识，这可以便于后台统计

在需要渠道等信息时可以通过下面代码进行获取
String channel = WalleChannelReader.getChannel(this.getApplicationContext());

如何生成渠道包
生成渠道包的方式是和assemble${variantName}Channels指令结合，渠道包的生成目录默认存放在 build/outputs/apk/，也可以通过walle闭包中的apkOutputFolder参数来指定输出目录

生成渠道包 ./gradlew clean assembleReleaseChannels

### 六、总结
以上就是本项目的大致概览，总得来说，你既可以用它来学习组件化开发，也可以用它来学习Android Jetpack组件的实际运用，还可以用来学习如何优化一个APP，不过这等我后续的更新了，当前已经更新的有
- [「全面理解Android内存优化 1」-Android的内存机制与管理建议](https://www.jianshu.com/p/527bef3c517e)
- [「深入理解Android布局优化 1」-布局的加载流程与绘制原理](https://www.jianshu.com/p/aa2bf172a7ae)

如有任何问题、建议请给我留言或者在github中提交issue。感谢您的阅读，欢迎下载体验。
![下载二维码.png](https://upload-images.jianshu.io/upload_images/3146091-6d427831355b8b79?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### 参考资料
[《如何构建Android MVVM 应用框架》](https://tech.meituan.com/2016/11/11/android-mvvm.html)

[《Android 组件化最佳实践》](https://dwz.cn/lOeaiPi8)

[《来自一位阿里朋友的组件化架构实践》](https://dwz.cn/rPM80kL7)


