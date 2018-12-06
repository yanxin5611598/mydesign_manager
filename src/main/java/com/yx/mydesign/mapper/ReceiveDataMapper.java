package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.ReceiveData;
import com.yx.mydesign.bean.ReceiveDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiveDataMapper {
    int countByExample(ReceiveDataExample example);

    int deleteByExample(ReceiveDataExample example);

    int insert(ReceiveData record);

    int insertSelective(ReceiveData record);

    List<ReceiveData> selectByExample(ReceiveDataExample example);

    int updateByExampleSelective(@Param("record") ReceiveData record, @Param("example") ReceiveDataExample example);

    int updateByExample(@Param("record") ReceiveData record, @Param("example") ReceiveDataExample example);
}