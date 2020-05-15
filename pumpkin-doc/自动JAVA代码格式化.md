# java代码格式化

## 一, 下载阿里代码规范插件

| 文件                               | 说明         |
| ---------------------------------- | ------------ |
| p3c-formatter                      | 模板文件     |
| EclipseFormatter.zip               | idea插件     |
| 阿里巴巴Java开发手册（详尽版）.pdf | 代码规范手册 |

官方文件：

- 格式化模板文件下载地址：https://github.com/alibaba/p3c/tree/master/p3c-formatter

| 文件名                   | 作用                 |
| ------------------------ | -------------------- |
| eclipse-codestyle.xml    | 代码格式化时用的模板 |
| eclipse-codetemplate.xml | 注释模板             |



![img](https://upload-images.jianshu.io/upload_images/13732217-537ac14ae7f678af.png?imageMogr2/auto-orient/strip|imageView2/2/w/998/format/webp)

两个模板文件

## 二、eclipse 格式化设置

### 格式化模板导入

- 依次点击：`Window`->`Preferences`->`Java`->`Code Style`->`Formatter`->`Import`

- 选择`eclipse-codestyle.xml`文件确定

- 默认在`Active profile`中选择新导入的`P3C-CodeStyle`，如未选择，请手动选择

- 点击

  ```
  Apply
  ```

  完成配置

  

  ![img](https://upload-images.jianshu.io/upload_images/13732217-7b7c29f141f5d7ff.png?imageMogr2/auto-orient/strip|imageView2/2/w/811/format/webp)

  eclipse模板文件导入

### 注释模板导入

- 操作流程同上，`Window`->`Preferences`->`Java`->`Code Style`->`Code Templates`->`Import`

- 勾选`Automatically add comments for new methods and types`

- 点击 Apply 完成配置

  

  ![img](https://upload-images.jianshu.io/upload_images/13732217-7bb520bea2af6702.png?imageMogr2/auto-orient/strip|imageView2/2/w/870/format/webp)

  image.png

### properties文件编码UTF-8

- `Window`->`Preferences`->`General`->`Content Types`->`Text`->`Java Properties File`

- 将Java Properties File及下属的所有项均设定为Default encoding: UTF-8

  ![img](https://upload-images.jianshu.io/upload_images/13732217-35385e39e9930369.png?imageMogr2/auto-orient/strip|imageView2/2/w/810/format/webp)

  ## eclipse 自动格式化

  1. 首先打开eclipse，在导航栏上找到如图所示位置的**Window，**选中最后的**Preferences。**

     [![eclipse怎么设置在保存代码时自动格式化](https://imgsa.baidu.com/exp/w=500/sign=75be4896f7f2b211e42e854efa816511/e61190ef76c6a7efab9d86c3f1faaf51f2de669f.jpg)](http://jingyan.baidu.com/album/48b37f8dca54411a65648851.html?picindex=1)

  2.  点击后在打开的页面左边栏找到**Java，**然后找到其下面的**Editor，**如图**。**

     [![eclipse怎么设置在保存代码时自动格式化](https://imgsa.baidu.com/exp/w=500/sign=0fd0f908eb1190ef01fb92dffe199df7/32fa828ba61ea8d377fa5de19b0a304e241f587e.jpg)](http://jingyan.baidu.com/album/48b37f8dca54411a65648851.html?picindex=2)

  3. 选中**Editor**下面的**Save Action**，在右边内容栏修改即可。

     [![eclipse怎么设置在保存代码时自动格式化](https://imgsa.baidu.com/exp/w=500/sign=fdde8a08ad44ad342ebf8787e0a00c08/b58f8c5494eef01f56d43020ecfe9925bd317d16.jpg)](http://jingyan.baidu.com/album/48b37f8dca54411a65648851.html?picindex=3)

  4. 右边各个选项如图所示，可以根据自己需要选择。

     [![eclipse怎么设置在保存代码时自动格式化](https://imgsa.baidu.com/exp/w=500/sign=486f8417711ed21b79c92ee59d6cddae/aec379310a55b319243f97904fa98226cefc1714.jpg)](http://jingyan.baidu.com/album/48b37f8dca54411a65648851.html?picindex=4)

  5. 一般选择勾选‘格式化修改的行’和‘导入包’的那个点击确定就行，如图。确定之后就可以在每次Ctrl+S 保存代码时自动格式化了。当然直接使用Ctrl+shift+f格式化代码后再保存也是可以的，可以根据自己的习惯选择。推荐使用自动格式化。

     [![eclipse怎么设置在保存代码时自动格式化](https://imgsa.baidu.com/exp/w=500/sign=10b8ebc2733e6709be0045ff0bc69fb8/34fae6cd7b899e51b88d2df14ea7d933c9950dd7.jpg)](http://jingyan.baidu.com/album/48b37f8dca54411a65648851.html?picindex=5)

## 三、idea 格式化配置

### 安装EclipseCodeFormatter插件

- 因为阿里配置文件是针对eclipse的，所有要导入配置文件需要额外安装EclipseFormatter插件

- 在线安装，需翻墙。依次点击进入插件界面：

  ![img](https://upload-images.jianshu.io/upload_images/13732217-4a86d63b11a07a39.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

  在线安装EclipseCodeFormatter

- 离线安装：下载插件文件，在`Plugins`界面中选择`Install plugin from disk`，选择已下载好的 `EclipseFormatter.zip`文件，确定并重启idea。

- 下载地址：

  https://github.com/krasa/EclipseCodeFormatter/releases

  

  ![img](https://upload-images.jianshu.io/upload_images/13732217-fcaab43d52cdcfa4.png?imageMogr2/auto-orient/strip|imageView2/2/w/692/format/webp)

  image.png

###  导入`eclipse-codestyle.xml`

 

![img](https://upload-images.jianshu.io/upload_images/13732217-b064bf1d15301a32.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

导入eclipse-codestyle.xml



### 设置类的注释格式

进入Settings界面 Editor->File and Code Templates->File Header，右侧输入注释模板

![img](https://upload-images.jianshu.io/upload_images/13732217-d5325bc8d104215a.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

设置类的注释格式

- 代码片段

```bash
/**
* @author  ${USER}
* @since ${DATE} ${TIME} 
*/
```



###  IDEA 自动格式化设置

**安装Save Actions插件：**
![img](http://javashizhan.com/img/A018_1.png)

**3.Save Actions默认没有激活，需要激活：**
![img](http://javashizhan.com/img/A018_0.png)

 