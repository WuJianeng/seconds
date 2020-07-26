package org.example.service

import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.example.dao.GoodsDao
import org.example.dao.UserDao
import org.example.domain.User
import org.example.vo.GoodsVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoodsService(private val goodsDao: GoodsDao) {

    fun listGoodsVo(): List<GoodsVo> = goodsDao.listGoodsVo()

    fun getGoodsVoByGoodsId(goodsId: Long) = goodsDao.getGoodsById(goodsId)

    fun reduceStock(goodsId: Long) = goodsDao.reduceStock(goodsId)


}