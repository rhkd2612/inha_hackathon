package com.mumomu.neighborwith.service;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Report;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.dto.LetterCreateForm;
import com.mumomu.neighborwith.entity.dto.LetterDto;
import com.mumomu.neighborwith.entity.dto.ReportCreateForm;
import com.mumomu.neighborwith.entity.dto.ReportDto;
import com.mumomu.neighborwith.repository.LetterRepository;
import com.mumomu.neighborwith.repository.ReportRepository;
import com.mumomu.neighborwith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportDto getReportInfo(Long reportId) {
        Optional<Report> optFindReport = reportRepository.findById(reportId);

        if(optFindReport.isEmpty())
            throw new IllegalArgumentException("존재하지 않는 건의입니다.");

        Report findReport = optFindReport.get();
        return new ReportDto(findReport, findReport.getSender());
    }

    public List<ReportDto> getReportList(String ownerTel) {

        List<Report> findReportList = reportRepository.findAllByOwnerTel(ownerTel);
        List<ReportDto> ret = findReportList.stream().map(r -> new ReportDto(r, r.getSender())).collect(Collectors.toList());

        Collections.sort(ret, new Comparator<ReportDto>() {
            @Override
            public int compare(ReportDto r1, ReportDto r2) {
                return r2.getCreateTime().compareTo(r1.getCreateTime());
            }
        });

        return ret;
    }

    public Long newReport(ReportCreateForm reportCreateForm) {
        User sender = userRepository.findById(reportCreateForm.getSenderId()).get();

        Report createdReport = Report.newReport(reportCreateForm);
        createdReport.setCreateTime(new Date());
        createdReport.setSender(sender);

        reportRepository.save(createdReport);

        return createdReport.getId();
    }
}
