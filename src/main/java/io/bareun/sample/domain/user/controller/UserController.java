package io.bareun.sample.domain.user.controller;

import io.bareun.base.common.dto.map.BaseMap;
import io.bareun.base.common.dto.response.ApiResponse;
import io.bareun.sample.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<?> readAllUsers(@RequestParam Map<String, Object> parameters) {
        List<BaseMap> users = userService.readAllUsers(BaseMap.of(parameters));
        return ApiResponse.success(users);
    }

    @GetMapping("/{id}")
    public ApiResponse<?> readUserById(@PathVariable("id") Long id) {
        BaseMap user = userService.readUserById(id);
        return ApiResponse.success(user);
    }

    @PostMapping
    public ApiResponse<?> createUser(@RequestBody BaseMap user) {
        userService.createUser(user);
        return ApiResponse.success();
    }

    @PutMapping("/{id}")
    public ApiResponse<?> updateUser(@PathVariable("id") Long id, @RequestBody BaseMap user) {
        userService.updateUser(id, user);
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ApiResponse.success();
    }
}
