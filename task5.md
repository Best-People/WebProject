#### XidianShop由四个解决方案文件夹所构成，分别是Libraries，Presentation，Strategies和Plugins，下图为方案文件夹的截图和介绍： ####
　　![](http://i.imgur.com/m28HcNW.png)

- Libraries：西电商城基础类库，主要提供系统核心，数据访问，业务逻辑等功能。
- Presentation：西电商城web展示，提供网站前台和后台实现。
- Strategies：包含各种策略程序集
- Plugins：包含开发授权，支付等插件。


　　XidianShop的主要功能都在Libraries和Presentation中，而Strategies和Plugins只是提供程序外围的一些服务。下面依次介绍各个项目：
　　XidianShop.Core项目：这个项目是XidianShop最核心的项目，它提供西电商城最底层，最基础的服务。首先通过图片给出各个模块的基本介绍：
	

![](http://i.imgur.com/Jw9vzce.png)

   对于异步模块，缓存模块，配置模块，数据模块，邮箱模块，随机值模块，Session模块，短信模块这8个模块来说，XidianShop.Core项目只提供加载和接口，并不包含具体实现。下述为邮箱模块代码（其它7个模块的实现原理一样）：


    	1 /// <summary>
    	2 /// XidianShop邮件管理类
    	3 /// </summary>
    	4 public class BSPEmail
    	5 {
    	6 private static IEmailStrategy _emailstrategy = null;//邮件策略
    	7
    	8 static BSPEmail()
    	9 {
    	10 Load();
    	11 }
    	12
    	13 /// <summary>
    	14 /// 邮件策略实例
    	15 /// </summary>
    	16 public static IEmailStrategy Instance
    	17 {
    	18 get { return _emailstrategy; }
    	19 }
    	20
    	21 /// <summary>
    	22 /// 加载邮件策略
    	23 /// </summary>
    	24 private static void Load()
    	25 {
    	26 try
    	27 {
    	28 string[] fileNameList = Directory.GetFiles(System.Web.HttpRuntime.BinDirectory, "XidianShop.EmailStrategy.*.dll", SearchOption.TopDirectoryOnly);
    	29 _emailstrategy = (IEmailStrategy)Activator.CreateInstance(Type.GetType(string.Format("XidianShop.EmailStrategy.{0}.EmailStrategy, XidianShop.EmailStrategy.{0}", fileNameList[0].Substring(fileNameList[0].IndexOf("EmailStrategy.") + 14).Replace(".dll", "")),
    	30 false,
    	31 true));
    	32 }
    	33 catch
    	34 {
    	35 throw new BSPException("创建\"邮件策略对象\"失败，可能存在的原因：未将\"邮件策略程序集\"添加到bin目录中；将多个\"邮件策略程序集\"添加到bin目录中；\"邮件策略程序集\"文件名不符合\"XidianShop.EmailStrategy.{策略名称}.dll\"格式");
    	36 }
    	37 }
    	38
    	39 }

　　上述实现的核心思想是“约定大于配置”和“策略模式”。
　　目前西电商城默认实现了每个策略，他们的对应关系及介绍如下：

　　
	![](http://i.imgur.com/rvlcSYj.png)


	* 当前只有企业版提供NOSQL实现，所以目前Data文件夹中的NOSQL为空。

	* XidianShop.Data项目:这个项目主要是通过调用XidianShop.Core项目中的BSPData类来和各种数据存储做交互。
- XidianShop.Services项目:西电商城的业务功能实现
- XidianShop.Web.Framework项目:该项目主要提供西电商城web层面的设计。　　
- XidianShop.Web.Admin项目:西电商城网站后台，这个项目的类型是类库，不是web项目(但mvc的各部分俱全)，所以不能直接启动。
- XidianShop.Web项目:西电商城网站前台：使用ASP.NET MVC3实现。各个文件夹或文件解释如下 ：
　　	
	
	![](http://i.imgur.com/kILKvaD.png)


