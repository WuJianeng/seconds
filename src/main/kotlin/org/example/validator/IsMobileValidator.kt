package org.example.validator

import org.example.util.isMobile
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class IsMobileValidator: ConstraintValidator<IsMobile, String> {

    var required: Boolean = false

    override fun initialize(constraintAnnotation: IsMobile) {
        super.initialize(constraintAnnotation)
        required = constraintAnnotation.required
    }

    override fun isValid(p0: String?, p1: ConstraintValidatorContext?): Boolean {
        return if (required) {
            if (p0 == null) {
                false
            } else {
                isMobile(p0)
            }
        } else if (p0 == null) {
            true
        } else {
            isMobile(p0)
        }
    }

}