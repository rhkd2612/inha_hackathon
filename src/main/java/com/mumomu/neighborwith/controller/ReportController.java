package com.mumomu.neighborwith.controller;

import com.mumomu.neighborwith.entity.dto.LetterCreateForm;
import com.mumomu.neighborwith.entity.dto.LetterDto;
import com.mumomu.neighborwith.entity.dto.ReportCreateForm;
import com.mumomu.neighborwith.entity.dto.ReportDto;
import com.mumomu.neighborwith.service.LetterService;
import com.mumomu.neighborwith.service.ReportService;
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
@Api(tags = {"건의와 관련된 컨트롤러"})
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/list/{ownerTel}")
    @Operation(summary = "집주인 번호와 관련된 건의 반환", description = "집주인 번호와 관련된 건의 반환합니다.")
    @ApiResponse(responseCode = "200", description = "건의 리스트 반환 성공")
    public ResponseEntity<?> getReportList(@PathVariable String ownerTel){
        List<ReportDto> reportDtos = reportService.getReportList(ownerTel);
        return new ResponseEntity<>(reportDtos, HttpStatus.OK);
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "건의 정보 반환", description = "특정 건의 정보를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "건의 반환 성공")
    @ApiResponse(responseCode = "400", description = "건의 반환 실패")
    public ResponseEntity<?> getReport(@PathVariable Long reportId){
        ReportDto reportDto;
        try{
            reportDto = reportService.getReportInfo(reportId);
        } catch(IllegalArgumentException e){
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    @PostMapping("/new")
    @Operation(summary = "건의 작성", description = "건의를 작성합니다")
    @ApiResponse(responseCode = "201", description = "건의 작성 성공")
    @ApiResponse(responseCode = "400", description = "건의 작성 실패")
    public ResponseEntity<?> newReport(@RequestBody ReportCreateForm reportCreateForm){
        Long reportId;
        try {
            reportId = reportService.newReport(reportCreateForm);
        } catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(reportId, HttpStatus.CREATED);
    }
}
