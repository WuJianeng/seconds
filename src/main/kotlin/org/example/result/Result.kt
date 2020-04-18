package org.example.result

data class Result<T> private constructor(
        var code: Int,
        val msg: String,
        val data: T? = null
) {
    private constructor(codeMsg: CodeMsg) : this(codeMsg.code, codeMsg.msg, null)
    private constructor(data: T): this(0, "", data)
    private constructor(code: Int, msg: String): this(code, msg, null)

    companion object{
        fun<T> error(codeMsg: CodeMsg): Result<T> {
            return Result<T>(codeMsg)
        }

        fun <T> success(data: T): Result<T> {
            return Result(data)
        }
    }
}