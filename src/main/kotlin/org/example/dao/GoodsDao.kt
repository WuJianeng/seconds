package org.example.dao

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.example.vo.GoodsVo


@Mapper
interface GoodsDao {

    @Select("SELECT g.*, s.kill_price, s.stock_count, s.start_date, s.end_date FROM goods_seconds s LEFT JOIN goods g on s.goods_id = g.id")
    fun listGoodsVo(): List<GoodsVo>

    @Select("SELECT g.*, s.kill_price, s.stock_count, s.start_date, s.end_date FROM goods_seconds s LEFT JOIN goods g on s.goods_id = g.id WHERE s.goods_id=#{id}")
    fun getGoodsById(@Param("id") goodsId: Long): GoodsVo

    @Update("Update goods_seconds SET stock_count=stock_count-1 WHERE goods_id=#{id}")
    fun reduceStock(@Param("id")goodsId: Long): Unit
}