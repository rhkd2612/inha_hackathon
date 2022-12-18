package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.postittype.CourierPostit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor
@ApiModel(value="쪽지 정보(DTO)", description = "작성된 쪽지의 정보를 제공한다")
public class LetterDto {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String postitTitle;
    private String createTime;
    private String content;
    private boolean isAnonymous;

    public LetterDto(Letter letter, User sender, User receiver) {
        this.id = letter.getId();
        this.senderId = sender.getId();
        this.receiverId = receiver.getId();
        this.postitTitle = letter.getPostitTitle();
        this.createTime = letter.getCreateTime();
        this.content = letter.getContent();
        this.isAnonymous = letter.isAnonymous();
    }
}
