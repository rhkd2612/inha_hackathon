package com.mumomu.neighborwith.controller;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.dto.*;
import com.mumomu.neighborwith.service.LetterService;
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
@Api(tags = {"쪽지와 관련된 컨트롤러"})
@RequestMapping("/api/letter")
public class LetterController {
    private final LetterService letterService;

    @GetMapping("/list/{userId}")
    @Operation(summary = "사용자의 쪽지 리스트 반환", description = "사용자의 쪽지 리스트를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "쪽지 리스트 반환 성공")
    public ResponseEntity<?> getLetterList(@PathVariable Long userId){
        List<LetterDto> letterDtos = letterService.getLetterList(userId);
        return new ResponseEntity<>(letterDtos, HttpStatus.OK);
    }

    @GetMapping("/{letterId}")
    @Operation(summary = "쪽지 정보 반환", description = "특정 쪽지 정보를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "쪽지 반환 성공")
    @ApiResponse(responseCode = "400", description = "쪽지 반환 실패")
    public ResponseEntity<?> getLetter(@PathVariable Long letterId){
        LetterDto letterDto;
        try{
            letterDto = letterService.getLetterInfo(letterId);
        } catch(IllegalArgumentException e){
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    }

    @PostMapping("/new")
    @Operation(summary = "쪽지 작성", description = "쪽지를 작성합니다")
    @ApiResponse(responseCode = "201", description = "쪽지 작성 성공")
    @ApiResponse(responseCode = "400", description = "쪽지 작성 실패")
    public ResponseEntity<?> newLetter(@RequestBody LetterCreateForm letterCreateForm){
        Long letterId;
        try {
            letterId = letterService.newLetter(letterCreateForm);
        } catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(letterId, HttpStatus.CREATED);
    }
}
