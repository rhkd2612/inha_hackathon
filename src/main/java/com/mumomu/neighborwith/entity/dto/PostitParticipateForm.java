package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data @Builder
@AllArgsConstructor
@ApiModel(value="참여할 게시글 정보", description = "참여할 게시글의 정보를 제공한다")
public class PostitParticipateForm {
    private Long id;
    private Long userId;
}
