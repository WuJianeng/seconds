package org.example.exception

import org.apache.ibatis.javassist.SerialVersionUID
import org.example.result.CodeMsg

data class GlobalException(val codeMsg: CodeMsg): RuntimeException(codeMsg.toString()) {

    companion object{
        private const val serialVersionUID = 1L
    }
}