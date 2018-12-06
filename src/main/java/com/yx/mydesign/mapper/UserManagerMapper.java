package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.UserManager;
import com.yx.mydesign.bean.UserManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserManagerMapper {
    int countByExample(UserManagerExample example);

    int deleteByExample(UserManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserManager record);

    int insertSelective(UserManager record);

    List<UserManager> selectByExample(UserManagerExample example);

    UserManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserManager record, @Param("example") UserManagerExample example);

    int updateByExample(@Param("record") UserManager record, @Param("example") UserManagerExample example);

    int updateByPrimaryKeySelective(UserManager record);

    int updateByPrimaryKey(UserManager record);
}