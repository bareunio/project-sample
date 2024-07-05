package io.bareun.sample.domain.user.dao;

import io.bareun.base.common.dto.map.BaseMap;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    List<BaseMap> selectAllUsers(BaseMap parameters);

    BaseMap selectUserById(Long id);

    void insertUser(BaseMap user);

    void updateUser(BaseMap user);

    void deleteUser(Long id);
}
