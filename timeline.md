# 秒杀系统 by Kotlin



## 4月17日

* 搭建秒杀系统的基本框架

* 解决kotlin使用@ConfigurationProperties注解加载 application.properties 中的配置问题：
由于Kotlin的val是不可变类型，在初始化之后不可更改，而 spring 注入配置是通过set方式，因此会出现无法注入问题。

    查看 StackOverFlow 相关问题发现，可以通过添加 @ConstructorBinding 注解的方式实现，当然对 Spring Boot 的版本有一定要求。

    > 可参考链接 [Kotlin & Spring Boot @ConfigurationProperties](https://stackoverflow.com/questions/45953118/kotlin-spring-boot-configurationproperties)

* 拷贝 **seckill** 中前端的 static 代码到项目中

## 4月18日

* 完成用户登录和分布式session功能
* 使用了异常拦截和参数解析功能
    
    在参数解析时，遇到异常拦截器拦截抛出的异常 
    
    java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to org.example.domain.User

```
    private fun <T> resolveString(string: String, clazz: Class<T>): T {
            return when (clazz) {
                Int::class -> string.toInt()
                Long::class -> string.toLong()
                Double::class -> string.toDouble()
                Float::class -> string.toFloat()
                String::class -> this
                else -> JSON.parseObject(string, clazz)
            } as T
        }
```

最开始 fastjson 解析 JSON 字符串使用的是 `Object parse(String text)` 方法。
因此导致解析错误，无法进行类型转换。

目前已完成用户登录和分布式 session 功能