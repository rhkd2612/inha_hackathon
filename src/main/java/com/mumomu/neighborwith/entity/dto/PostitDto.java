package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.User;
import com.mumomu.neighborwith.entity.postittype.CourierPostit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import com.mumomu.neighborwith.entity.postittype.GeneralPostit;
import com.mumomu.neighborwith.entity.postittype.SharePostit;
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
            if(this.dtype.equals("SharePostit")){
                this.place = ((SharePostit)postit).getPlace();
                this.curParticipantCount = ((SharePostit)postit).getCurParticipantCount();
                this.totalParticipantCount = ((SharePostit)postit).getTotalParticipantCount();
            } else {
                if (this.dtype.equals("CourierPostit")){
                    this.place = ((CourierPostit)postit).getPlace();
                    this.curParticipantCount = ((CourierPostit)postit).getCurParticipantCount();
                    this.totalParticipantCount = ((CourierPostit)postit).getTotalParticipantCount();

                    this.price = ((CourierPostit)postit).getPrice();
                    this.fee = ((CourierPostit)postit).getFee();
                    this.totalPrice = ((CourierPostit)postit).getTotalPrice();

                    this.link = ((CourierPostit) postit).getLink();
                } else{
                    this.place = ((DeliveryPostit)postit).getPlace();
                    this.curParticipantCount = ((DeliveryPostit)postit).getCurParticipantCount();
                    this.totalParticipantCount = ((DeliveryPostit)postit).getTotalParticipantCount();

                    this.price = ((DeliveryPostit)postit).getPrice();
                    this.fee = ((DeliveryPostit)postit).getFee();
                    this.totalPrice = ((DeliveryPostit)postit).getTotalPrice();
                }
            }
        }
    }
}
