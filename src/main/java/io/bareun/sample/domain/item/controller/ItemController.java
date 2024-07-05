package io.bareun.sample.domain.item.controller;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.sample.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 상품 Entity에 대한 컨트롤러 클래스입니다.
 * <p>
 * 이 클래스는 상품에 대한 CRUD 연산의 HTTP 요청과 응답을 처리합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    /**
     * 모든 상품을 검색합니다.
     *
     * @param parameters readAll 연산의 파라미터
     * @return 상품 리스트를 반환하는 ApiResponse
     */
    @GetMapping
    public ApiResponse<List<BaseMap>> readAll(@RequestParam Map<String, Object> parameters) {
        return ApiResponse.success(itemService.readAll(BaseMap.of(parameters)));
    }

    /**
     * id로 특정 상품을 가져옵니다.
     *
     * @param id 상품의 id
     * @return 상품을 반환하는 ApiResponse
     */
    @GetMapping("/{id}")
    public ApiResponse<BaseMap> read(@PathVariable("id") Long id) {
        return ApiResponse.success(itemService.read(id));
    }

    /**
     * 새 상품을 생성합니다.
     *
     * @param parameters 새 상품을 만드는 데 필요한 파라미터
     * @return 생성 성공을 나타내는 ApiResponse
     */
    @PostMapping
    public ApiResponse<?> create(@RequestBody BaseMap parameters) {
        itemService.create(parameters);
        return ApiResponse.success();
    }

    /**
     * 기존 상품을 업데이트합니다.
     *
     * @param parameters 상품을 업데이트하는 데 필요한 파라미터
     * @return 업데이트 성공을 나타내는 ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable("id") Long id, @RequestBody BaseMap parameters) {
        itemService.update(id, parameters);
        return ApiResponse.success();
    }
}