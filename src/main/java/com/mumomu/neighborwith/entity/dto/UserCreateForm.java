package com.mumomu.neighborwith.entity.dto;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.USER_AUTH;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="유저 생성 정보", description = "유저 생성 정보를 제공한다")
public class UserCreateForm {
    private String auth;
    private String name;
    private String tel;
    private String buildingAddress;
    private String userAddress; // 닉네임으로 사용
    // private String deedImage; // 집문서 사진
}
