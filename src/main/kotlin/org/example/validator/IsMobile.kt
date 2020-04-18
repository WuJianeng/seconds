package org.example.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [IsMobileValidator::class])
annotation class IsMobile(
        val required: Boolean = true,
        val message: String = "手机号码格式错误",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<in Payload>> = []
)