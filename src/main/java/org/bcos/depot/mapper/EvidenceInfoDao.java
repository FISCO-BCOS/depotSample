package org.bcos.depot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bcos.depot.entity.EvidenceInfo;
import org.bcos.depot.entity.EvidenceInfoExample;

public interface EvidenceInfoDao {
    long countByExample(EvidenceInfoExample example);

    int deleteByExample(EvidenceInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EvidenceInfo record);

    int insertSelective(EvidenceInfo record);

    List<EvidenceInfo> selectByExample(EvidenceInfoExample example);

    EvidenceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EvidenceInfo record, @Param("example") EvidenceInfoExample example);

    int updateByExample(@Param("record") EvidenceInfo record, @Param("example") EvidenceInfoExample example);

    int updateByPrimaryKeySelective(EvidenceInfo record);

    int updateByPrimaryKey(EvidenceInfo record);
}