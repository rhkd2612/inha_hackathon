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
@ApiModel(value="작성할 게시글 정보", description = "작성할 게시글의 정보를 제공한다")
public class PostitCreateForm {
    private Long id;
    private Long userId;
    private String dtype;
    private String title;
    private String content;
    private String image;
    private boolean isAnonymous;

    private String place = "";
    private int curParticipantCount = -1;
    private int totalParticipantCount = -1;
    private String link = "";
    private int price = -1;
    private int fee = -1;
    private int totalPrice = -1;
}
