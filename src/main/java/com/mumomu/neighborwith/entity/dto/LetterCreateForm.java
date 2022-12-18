package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="쪽지 생성 정보", description = "쪽지 생성 정보를 제공한다")
public class LetterCreateForm {
    private Long senderId;
    private Long receiverId;
    private String postitTitle; // 게시글 제목, NULL 가능
    private String content;
    private boolean isAnonymous;
}
