package org.example.vo

import org.example.validator.IsMobile
import org.hibernate.validator.constraints.Length


data class LoginVo(@IsMobile val mobile: String, @Length(min = 32) val password: String) {
}