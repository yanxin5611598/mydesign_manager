package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.UserCheckResult;
import com.yx.mydesign.bean.UserCheckResultExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserCheckResultMapper {
    int countByExample(UserCheckResultExample example);

    int deleteByExample(UserCheckResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCheckResult record);

    int insertSelective(UserCheckResult record);

    List<UserCheckResult> selectByExample(UserCheckResultExample example);

    UserCheckResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCheckResult record, @Param("example") UserCheckResultExample example);

    int updateByExample(@Param("record") UserCheckResult record, @Param("example") UserCheckResultExample example);

    int updateByPrimaryKeySelective(UserCheckResult record);

    int updateByPrimaryKey(UserCheckResult record);
    List<UserCheckResult> getAllData();
    List<UserCheckResult> selectByItemsLike(String deviceID,
			String username, String place);
    List<UserCheckResult> getDataByAddress();
    List<String> getRankAndTimeList(String location);
}