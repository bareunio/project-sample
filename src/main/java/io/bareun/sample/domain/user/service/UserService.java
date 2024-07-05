package io.bareun.sample.domain.user.service;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.sample.domain.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public List<BaseMap> readAllUsers(BaseMap parameters) {
        return userDao.selectAllUsers(parameters);
    }

    public BaseMap readUserById(Long id) {
        return userDao.selectUserById(id);
    }

    public void createUser(BaseMap user) {
        userDao.insertUser(user);
    }

    public void updateUser(Long id, BaseMap user) {
        userDao.updateUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
