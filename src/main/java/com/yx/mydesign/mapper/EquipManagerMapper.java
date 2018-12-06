package com.yx.mydesign.mapper;

import com.yx.mydesign.bean.EquipManager;
import com.yx.mydesign.bean.EquipManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipManagerMapper {
    int countByExample(EquipManagerExample example);

    int deleteByExample(EquipManagerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EquipManager record);

    int insertSelective(EquipManager record);

    List<EquipManager> selectByExample(EquipManagerExample example);

    EquipManager selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EquipManager record, @Param("example") EquipManagerExample example);

    int updateByExample(@Param("record") EquipManager record, @Param("example") EquipManagerExample example);

    int updateByPrimaryKeySelective(EquipManager record);

    int updateByPrimaryKey(EquipManager record);
}