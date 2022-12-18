package com.mumomu.neighborwith.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="게시글 조회 정보", description = "조회할 게시물 정보를 작성합니다")
public class PostitListForm {
    @ApiModelProperty(example = "SharePostit")
    private String dtype;
    @ApiModelProperty(example = "유저 아이디")
    private Long userId;
}
