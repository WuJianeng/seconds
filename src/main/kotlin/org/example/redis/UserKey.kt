package org.example.redis

import org.example.domain.User

class UserKey private constructor(prefix: String, expireSeconds: Int): BasePrefix(prefix, expireSeconds) {

    private constructor(prefix: String): this(prefix, 0)

    companion object{
        fun getById(user: User) = UserKey(user.id.toString(), 0)

        fun getByName(user: User) = UserKey(user.name, 0)

        fun getDefault() = UserKey("default", 0)
    }
}