package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor
@ApiModel(value="작성할 건의 정보", description = "작성할 건의의 정보를 제공한다")
public class ReportCreateForm {
    private Long senderId;
    private String content;
    private String ownerTel;
    private boolean isAnonymous;
}
