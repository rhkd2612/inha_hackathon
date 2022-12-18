package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Report;
import com.mumomu.neighborwith.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor
@ApiModel(value="건의 정보(DTO)", description = "작성된 건의의 정보를 제공한다")
public class ReportDto {
    private Long id;
    private String username;
    private String userTel;
    private String buildingAddress;
    private String userAddress; // 닉네임으로 사용
    private String createTime;
    private String content;
    private String ownerTel;
    private boolean isAnonymous;

    public ReportDto(Report report, User sender) {
        this.id = report.getId();
        this.username = sender.getName();
        this.userTel = sender.getTel();
        this.buildingAddress = sender.getBuildingAddress();
        this.userAddress = sender.getUserAddress();
        this.createTime = report.getCreateTime();
        this.content = report.getContent();
        this.ownerTel = report.getOwnerTel();
        this.isAnonymous = report.isAnonymous();
    }
}
