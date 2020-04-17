package org.example.dao

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.example.domain.User

@Mapper
interface UserDao {

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    fun getById(@Param("id") id: Int): User

    @Insert("INSERT INTO `user`(`id`, `name`) VALUES(#{id}, #{name})")
    fun insert(user: User): Int
}