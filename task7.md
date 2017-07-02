## 系统架构图::
![Image text](WebProject/image/7_FUNC_01_01.png)


#构建技术:

## redis:  来构建分布式的缓存服务器.

    1.对首页的缓存,对用户经常访问的页面的缓存.
    2.用于实现单点登录服务器集群的session共享.
	即通过配置tomcat服务器的一个插件tomcat-redis-sesison-manager
	,使得tomcat服务器在封装session的时候不是在本地查找,
	而是去redis中去取,由于redis本身也是高可用的,这样我们就实现了session共享.
    3.用于秒杀系统,购物车系统的实现.

## solr: 实现商品搜索
	1.使用IKAnalyzer作为中文分词器
	2.从数据库中抽取商品摘要信息导入solr进行分析索引
	3.整合tomcat来发布solr服务
  

## spring ioc:
	1.管理Controller组件,http请求经过springMVC分发到达Controller
	2.Dao组件,操作数据库.
	3.Service组件,调用dao组件实现业务逻辑.
	4.过滤器组件,拦截器组件等.

## spring aop:
	1.用于实现声明式的事务管理
	2.用于测试缓存命中率
	3.用于测试服务组件的性能指标

## mybatis:
	ORM框架,为我们开发DAO层代码提供了更为灵活的方式.
	使用官网提供的generatorSqlmapCustom可以逆向生成DAO层的代码.
	但是这个只可以生成单表操作的代码,多表操作的代码还是需要我们组员进行coding.
	不过这已经极大的解放了生产力,因为我们的数据库设计使得系统大部分功能的实现
	只需要进行单表操作.

## SpringMVC:
	管理一个url和后端controller组件之间的映射关系.
	对客户端传过来的数据进行过滤拦截,封装进相应的POJO.
	

## httpClent:
	我们对httpClient进行了更高一层的封装.
	使得后端分布式服务之间的RPC调用更为方便.
