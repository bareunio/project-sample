package io.bareun.sample.domain.user.service;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.exception.BusinessException;
import io.bareun.base.exception.code.BaseErrorCode;
import io.bareun.sample.domain.user.code.UserErrorCode;
import io.bareun.sample.domain.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService extends EgovAbstractServiceImpl {

    private final UserDao userDao;

    @Transactional(readOnly = true)
    public List<BaseMap> readAllUsers(BaseMap parameters) {
        return userDao.selectAllUsers(parameters);
    }

    @Transactional(readOnly = true)
    public BaseMap readUserById(Long id) {
        BaseMap user = userDao.selectUserById(id);

        if (user == null) {
            throw new BusinessException(UserErrorCode.USER_NOT_FOUND);
        }

        log.info("UserService.readUserById [사용자 정보 조회] : 이름={}, 이메일={}",
                user.getString("name"), user.getString("email"));

        return user;
    }

    public void createUser(BaseMap user) {
        if (!hasText(user.getString("email"))) {
            throw new BusinessException(UserErrorCode.USER_REQUIRED, "이메일");
        }

        userDao.insertUser(user);
    }

    public void updateUser(Long id, BaseMap user) {
        userDao.updateUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
