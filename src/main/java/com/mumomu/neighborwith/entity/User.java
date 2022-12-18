package com.mumomu.neighborwith.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mumomu.neighborwith.entity.dto.UserCreateForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Postit> postits = new ArrayList<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Letter> sendLetters = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Letter> receiveLetters = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private USER_AUTH auth;

    private String name;
    private String tel;
    private String buildingAddress;
    private String userAddress; // 닉네임으로 사용
    private String deedImage;

    public void setSendLetters(List<Letter> sendLetters) {
        this.sendLetters = sendLetters;
    }

    public void setPostits(List<Postit> postits) {
        this.postits = postits;
    }

    // 받은 모든 쪽지 반환
    // 가공은 서비스단에서
    public List<Letter> getTotalLetters(){
        List<Letter> result = new ArrayList<>();

        result.addAll(sendLetters);
        result.addAll(receiveLetters);

        return result;
    }

    public void addPostit(Postit postit){
        postits.add(postit);
    }
    public void addSendLetter(Letter letter){
        sendLetters.add(letter);
    }

    public void addReceiveLetter(Letter letter){
        receiveLetters.add(letter);
    }
    public static User newUser(UserCreateForm userCreateForm){
        return User.builder()
                .auth(USER_AUTH.valueOf(userCreateForm.getAuth()))
                .name(userCreateForm.getName())
                .tel(userCreateForm.getTel())
                .buildingAddress(userCreateForm.getBuildingAddress())
                .userAddress(userCreateForm.getUserAddress())
                //.deedImage(userCreateForm.getDeedImage())
                .build();
    }
}
