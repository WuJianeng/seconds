package org.example.result

data class CodeMsg private constructor(val code: Int, val msg: String) {

    /**
     * 返回带参数的错误码
     * @param args
     * @return
     */
    fun fillArgs(vararg args: Any): CodeMsg {
        val message = String.format(msg, *args)
        return CodeMsg(code, message)
    }

    override fun toString(): String {
        return "CodeMsg [code=$code, msg=$msg]"
    }

    companion object {
        //通用的错误码
        val SUCCESS = CodeMsg(0, "success")
        val SERVER_ERROR = CodeMsg(500100, "服务端异常")
        val BIND_ERROR = CodeMsg(500101, "参数校验异常：%s")
        val ACCESS_LIMIT_REACHED = CodeMsg(500104, "访问高峰期，请稍等！")

        //登录模块 5002XX
        val SESSION_ERROR = CodeMsg(500210, "Session不存在或者已经失效")
        val PASSWORD_EMPTY = CodeMsg(500211, "登录密码不能为空")
        val MOBILE_EMPTY = CodeMsg(500212, "手机号不能为空")
        val MOBILE_ERROR = CodeMsg(500213, "手机号格式错误")
        val MOBILE_NOT_EXIST = CodeMsg(500214, "手机号不存在")
        val PASSWORD_ERROR = CodeMsg(500215, "密码错误")
        val PRIMARY_ERROR = CodeMsg(500216, "主键冲突")

        //商品模块 5003XX
        //订单模块 5004XX
        val ORDER_NOT_EXIST = CodeMsg(500400, "订单不存在")

        //秒杀模块 5005XX
        val SECKILL_OVER = CodeMsg(500500, "商品已经秒杀完毕")
        val REPEATE_SECKILL = CodeMsg(500501, "不能重复秒杀")
    }
}