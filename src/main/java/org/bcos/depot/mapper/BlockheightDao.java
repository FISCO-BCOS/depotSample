package org.bcos.depot.mapper;

import org.bcos.depot.entity.Blockheight;

public interface BlockheightDao {

    int deleteByPrimaryKey(Long id);

    int insert(Blockheight record);

    int insertSelective(Blockheight record);

    Blockheight selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blockheight record);

    int updateByPrimaryKey(Blockheight record);
}