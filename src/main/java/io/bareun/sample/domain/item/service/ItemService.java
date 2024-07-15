package io.bareun.sample.domain.item.service;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.sample.domain.item.dao.ItemDao;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상품에 대한 서비스 클래스입니다.
 *
 * <p>
 * 이 클래스는 상품에 대한 데이터베이스 연산을 수행합니다.
 */
@Service
@RequiredArgsConstructor
public class ItemService extends EgovAbstractServiceImpl {

    private final ItemDao itemDao;

    /**
     * 모든 상품을 데이터베이스에서 가져옵니다.
     *
     * @param parameters readAll 연산의 파라미터
     * @return 상품 리스트
     */
    public List<BaseMap> readAll(BaseMap parameters) {
        // TODO : 비지니스 로직 추가
        return itemDao.findAll(parameters);
    }

    /**
     * id로 특정 상품을 데이터베이스에서 가져옵니다.
     *
     * @param id 상품의 id
     * @return 특정 상품
     */
    public BaseMap read(Long id) {
        // TODO : 비지니스 로직 추가
        return itemDao.findById(id);
    }

    /**
     * 새로운 상품을 데이터베이스에 생성합니다.
     *
     * @param parameters 새 상품 생성에 필요한 파라미터
     */
    public void create(BaseMap parameters) {
        // TODO : 비지니스 로직 추가
        itemDao.save(parameters);
    }

    /**
     * 데이터베이스의 기존 상품을 업데이트합니다.
     *
     * @param id
     * @param parameters 상품 업데이트에 필요한 파라미터
     */
    public void update(Long id, BaseMap parameters) {
        // TODO : 비지니스 로직 추가
        itemDao.update(parameters);
    }
}