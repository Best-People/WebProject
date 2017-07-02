网上商城设计



目录

1 系统分析与设计
1.1系统总体的功能需求
1.2用户接口模块
1.3 管理员接口模块
2 系统UML建模
2.1 系统用例图
2.2 系统的时序图和活动图

 
1 系统分析与设计
1.1系统总体的功能需求
网上商城是个电子商务系统，它提供接口供用户登陆并选的商品，还提供系统的管理接口供管理员工作者处理客户订单。
总体功能框架

![1](https://github.com/GongYanran/WebProject/blob/master/1.png)

（1）用户接口模块。服务入口，用户都浏览器登陆，进行查询等操作。用户接口模块包括用户信息维护、商品查询、订购商品和订单维护4个部分。登陆后，用户的ID被保存在服务器的缓存中，用户的操作都将被存到数据库中，供商家进行分析。
（2）管理员接口模块。接口。管理员接口模块包括商品信息维护、内部员工信息维护、订单处理、销售情况查询和报表维护5部分。工作人员只有订单处理的权限。
（3）数据服务模块。是系统运行的基础，它包括查询订单的保存，工作人员的订单处理；销售情况的分析。



1.2用户接口模块
用户接口模块包括

![2](https://github.com/GongYanran/WebProject/blob/master/2.png)

（1）用户信息维护。用户必须先注册，用户名是标识。后台程序记录每个用户操作,根据用户的记录判断该用户的喜好。
（2）商品查询。商品查询模块可以根据商品的名称、种类等条件进行组合查询。
（3）订购商品。客户可以选择自己需要的商品放进购物车。用户记录都保存在系统数据库中供后台管理员分析。
（4）订单维护。客户订购商品后可查询状态。客户的订单维护信息也被保存在系统数据库中供管理员分析。

![3](https://github.com/GongYanran/WebProject/blob/master/3.png)

1.3 管理员接口模块
管理员接口包括

![4](https://github.com/GongYanran/WebProject/blob/master/4.png)


（1）商品信息维护，系统管理员登陆后可以对系统所有商品维护，包括商品的价格调整，新商品的加入和过期商品的删除等。

![5](https://github.com/GongYanran/WebProject/blob/master/5.png)


前提条件：管理员已经登陆。

（2）内部员工信息维护。系统管理员登陆后可以对信息进行维护，包括员工个人信息的更新等。


![6](https://github.com/GongYanran/WebProject/blob/master/6.png)


（3）订单处理。
订单处理一般是普通员工的工作，可以获得未处理的订单，并根据库存情况发货或者延迟发货。

![7](https://github.com/GongYanran/WebProject/blob/master/7.png)


前提条件：管理员或者普通员工已经登陆。

（4）销售情况查询。销售情况查询为企业管理者提供参数。管理员登陆后，可以得到详细的销售情况列表。

![8](https://github.com/GongYanran/WebProject/blob/master/8.png)


（5）报表维护。
报表维护提供查询结果的打印输出功能，如图11所示。

![9](https://github.com/GongYanran/WebProject/blob/master/9.png)


2 系统UML建模
2.1 系统用例图
  

![10](https://github.com/GongYanran/WebProject/blob/master/10.png)


![11](https://github.com/GongYanran/WebProject/blob/master/11.png)


2.2 系统的时序图和活动图

![12](https://github.com/GongYanran/WebProject/blob/master/12.png)

![13](https://github.com/GongYanran/WebProject/blob/master/13.png)

![14](https://github.com/GongYanran/WebProject/blob/master/14.png)
