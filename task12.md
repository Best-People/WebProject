Spring Security2安全管理

    1.登录时需要验证用户名和密码
    2.登录时需要加上验证码

    3.登录后的所有系统的访问URL均需要授权
    4.多个用户不能使用同一个账号同时登陆系统
    
5.当用户点击注销后要实现浏览器的后退按钮后退的页面失效

支付过程的安全策略

    防火墙计技术
    漏洞扫描技术
    入侵检测技术
    反病毒技术
    加密技术

安全协议

     
1．SSL ( Secure Socket Layer) 是由Ntscape 设计的一种面向连接的开放协议，
主要在两个通信应用程序之间提供机密性和数据完整性。但是, SSL 协议只能提供交
易中客户与服务器间的双方认证， 而电子商务往往是用户、网站、银行三家协作完
成， SSL 协议运行的基点是商家对客户信息保密的承诺，在整个过程中商家可以通过
银行对客户身份进行认证，但客户没有对商家身份进行认证，因此SSL 协议有利于商
家而不利于客户。但是SSL 协议独立于应用层协议且被大部分的浏览器和Web 服务器
所内置，目前几乎所有操作平台上的Web 浏览器以及流行的Web 服务器都支持SSL 协
议，SSL 协议在保障电子商务交易安全等方面有着广泛的应用。

    2．SET(Secure Electronic Transactions)是由Visa 和MasterCard 两大信用卡
组织联合开发的电子商务安全协议。SET 允许各方之间的报文交换不是实时的。SET 详
细而准确地反映了卡交易各方之间的各种关系，主要用于消费者与商店、商店与收单
银行( 付款银行) 之间。对消费者而言, SET 保证了商家的合法性，并且用户的信用卡
号不会被窃取。对商家而言, SET 为商家提供了保护自己的手段，使商家免受欺诈的困
扰，使商家的运营成本降低。但是SET 要求在银行网络、商家服务器、顾客的PC 上安
装相应的软件，还要求必须向各方发放证书，这使得SET 要比SSL 昂贵得多。但SET 可
以以用在系统的一部分。例如，商家在与银行连接中使用SET，而与顾客连接时仍然使
用SSL。这种方案既回避了在顾客机器上安装电子钱包软件，同时又获得了SET 提供的
很多优点。
