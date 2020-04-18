package org.example.redis

import org.example.domain.User

class UserKey private constructor(prefix: String, expireSeconds: Int): BasePrefix(prefix, expireSeconds) {

    private constructor(prefix: String): this(prefix, 0)

    companion object{
        private const val TOKEN_EXPIRE: Int = 7 * 24 * 3600

        fun getById(user: User) = UserKey(user.id.toString(), 0)

        fun getByName(user: User) = UserKey(user.nickname, 0)

        fun getDefault() = UserKey("default", 0)

        val token = UserKey("tk", TOKEN_EXPIRE)
    }
}