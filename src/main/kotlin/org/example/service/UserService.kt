package org.example.service

import org.example.dao.UserDao
import org.example.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(val userDao: UserDao) {



    fun getById(id: Int): User {
        return userDao.getById(id)
    }

    /**
     * 测试事务，
     */
    @Transactional
    fun tx(): Boolean {
        val user = User(3, "Jack")
        val user1 = User(1, "Mack")
        userDao.insert(user)
        userDao.insert(user1)

        return true
    }

}