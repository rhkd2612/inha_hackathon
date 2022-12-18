package com.mumomu.neighborwith.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
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

    public void setPostits(List<Postit> postits) {
        this.postits = postits;
    }

    // 받은 모든 쪽지 반환
    public List<Letter> getTotalLetters(){
        List<Letter> result = new ArrayList<>();
        result = sendLetters + receiveLetters;

        return result;
    }
}
