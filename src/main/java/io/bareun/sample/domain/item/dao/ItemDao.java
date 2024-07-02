package io.bareun.sample.domain.item.dao;

import io.bareun.base.common.dto.map.BaseMap;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface ItemDao {

    List<BaseMap> findAll(BaseMap parameters);

    BaseMap findById(Long id);

    void save(BaseMap parameters);

    void update(BaseMap parameters);
}
