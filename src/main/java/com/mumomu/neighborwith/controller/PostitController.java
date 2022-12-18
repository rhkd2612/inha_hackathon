package com.mumomu.neighborwith.controller;

import com.mumomu.neighborwith.entity.dto.PostitCreateForm;
import com.mumomu.neighborwith.entity.dto.PostitDto;
import com.mumomu.neighborwith.entity.dto.PostitListForm;
import com.mumomu.neighborwith.service.PostitService;
import io.swagger.annotations.Api;
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
@Api(tags = {"게시글과 관련된 컨트롤러"})
@RequestMapping("/api/postit")
public class PostitController {
    private final PostitService postitService;

    @GetMapping("/list")
    @Operation(summary = "게시글 리스트 반환", description = "게시글 리스트를 반환합니다")
    @ApiResponse(responseCode = "200", description = "게시글 리스트 반환 성공")
    public ResponseEntity<?> getPostitList(@RequestBody PostitListForm postitListForm){
        List<PostitDto> postitDtoList = postitService.getPostitList(postitListForm);
        return new ResponseEntity<>(postitDtoList, HttpStatus.OK);
    }

    @PostMapping("/new")
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다")
    @ApiResponse(responseCode = "201", description = "게시글 작성 성공")
    @ApiResponse(responseCode = "400", description = "게시글 작성 실패")
    public ResponseEntity<?> newPostit(@RequestBody PostitCreateForm postitCreateForm){
        try {
            postitService.newPostit(postitCreateForm);
        } catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
