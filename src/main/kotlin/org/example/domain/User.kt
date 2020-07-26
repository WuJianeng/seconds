package org.example.domain

import java.util.Date


data class User(val id: Long,
                val nickname: String,
                val password: String,
                val salt: String,
                val head: String?,
                val registerDate: Date,
                val lastLoginDate: Date,
                val loginCount: Int
) {
    constructor(): this(0, "test", "214esdv", "smfi23rsdr23r", "sv", Date(), Date(), 1)
}