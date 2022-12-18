package com.mumomu.neighborwith.controller;

import com.mumomu.neighborwith.entity.dto.*;
import com.mumomu.neighborwith.service.PostitService;
import com.mumomu.neighborwith.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Api(tags = {"유저와 관련된 컨트롤러"})
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @ApiImplicitParam(name = "userId", value = "유저 아이디", required = true, dataType = "Long", paramType = "path")
    @Operation(summary = "유저 정보 반환", description = "유저 정보를 반환합니다")
    @ApiResponse(responseCode = "200", description = "유저 정보 반환 성공")
    public ResponseEntity<?> getPostitList(@PathVariable Long userId){
        UserDto userInfo;

        try {
            userInfo = userService.getUserInfo(userId);
        } catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @PostMapping("/new")
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다")
    @ApiResponse(responseCode = "201", description = "게시글 작성 성공")
    @ApiResponse(responseCode = "400", description = "게시글 작성 실패")
    public ResponseEntity<?> newUser(@RequestBody UserCreateForm userCreateForm){
        Long userId;
        try {
            userId = userService.newUser(userCreateForm);
        } catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userId, HttpStatus.CREATED);
    }
}
