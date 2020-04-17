# 秒杀系统 by Kotlin



## 4月17日

* 搭建秒杀系统的基本框架

* 解决kotlin使用@ConfigurationProperties注解加载 application.properties 中的配置问题：
由于Kotlin的val是不可变类型，在初始化之后不可更改，而 spring 注入配置是通过set方式，因此会出现无法注入问题。

    查看 StackOverFlow 相关问题发现，可以通过添加 @ConstructorBinding 注解的方式实现，当然对 Spring Boot 的版本有一定要求。

    * 可参考链接 [Kotlin & Spring Boot @ConfigurationProperties](https://stackoverflow.com/questions/45953118/kotlin-spring-boot-configurationproperties)