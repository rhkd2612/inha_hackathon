package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.USER_AUTH;
import com.mumomu.neighborwith.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@ApiModel(value="유저 정보(DTO)", description = "유저의 정보를 제공한다")
public class UserDto {
    private Long id;
    private List<Postit> postits;
    private List<Letter> sendLetters;
    private List<Letter> receiveLetters;
    private USER_AUTH auth;
    private String name;
    private String tel;
    private String buildingAddress;
    private String userAddress; // 닉네임으로 사용
    //private String deedImage;


    public UserDto(User user, List<Postit> postits, List<Letter> sendLetters, List<Letter> receiveLetters) {
        this.id = user.getId();
        this.postits = postits;
        this.sendLetters = sendLetters;
        this.receiveLetters = receiveLetters;
        this.auth = user.getAuth();
        this.name = user.getName();
        this.tel = user.getTel();
        this.buildingAddress = user.getBuildingAddress();
        this.userAddress = user.getUserAddress();
    }
}
