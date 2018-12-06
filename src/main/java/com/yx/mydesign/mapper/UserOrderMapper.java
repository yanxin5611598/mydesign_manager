package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.UserOrder;
import com.yx.mydesign.bean.UserOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserOrderMapper {
    int countByExample(UserOrderExample example);

    int deleteByExample(UserOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    List<UserOrder> selectByExample(UserOrderExample example);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

    int updateByExample(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

	List<UserOrder> selectByPage(UserOrder order);

	Integer getPriceSumsByMoth(int month);

	Integer getPriceRepointSumsByMonth(int month);

	Integer getPriceVIPSumsByMonth(int month);
}