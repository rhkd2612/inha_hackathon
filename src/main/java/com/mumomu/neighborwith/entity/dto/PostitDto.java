package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.postittype.CourierPostit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data @Builder
@AllArgsConstructor
@ApiModel(value="게시글 정보(DTO)", description = "작성된 게시글의 정보를 제공한다")
public class PostitDto {
    private Long id;
    private String username;
    private String dtype;
    private String title;
    private String content;
    private String image;
    private String createTime;
    private boolean isAnonymous;
    private String buildingAddress;

    private String place = "";
    private int curParticipantCount = -1;
    private int totalParticipantCount = -1;
    private String link = "";
    private int price = -1;
    private int fee = -1;
    private int totalPrice = -1;

    public PostitDto(Postit postit){
        this.id = postit.getId();
        this.username = postit.getUser().getUserAddress();
        this.dtype = postit.getDtype();
        this.title = postit.getTitle();
        this.content = postit.getContent();
        this.image = postit.getImage();
        this.createTime = postit.getCreateTime();
        this.isAnonymous = postit.isAnonymous();

        if(!this.dtype.equals("GeneralPostit")){
            // 나눔 게시글, 배달 게시글, 택배 게시글
            this.place = ((DeliveryPostit)postit).getPlace();
            this.curParticipantCount = ((DeliveryPostit)postit).getCurParticipantCount();
            this.totalParticipantCount = ((DeliveryPostit)postit).getTotalParticipantCount();

            // 배달 게시글, 택배 게시글
            if(!this.dtype.equals("SharePostit")){
                this.place = ((DeliveryPostit)postit).getPlace();
                this.curParticipantCount = ((DeliveryPostit)postit).getCurParticipantCount();
                this.totalParticipantCount = ((DeliveryPostit)postit).getTotalParticipantCount();
            }

            // 택배 게시글
            if(this.dtype.equals("CourierPostit"))
                this.link = ((CourierPostit)postit).getLink();
        }
    }
}
