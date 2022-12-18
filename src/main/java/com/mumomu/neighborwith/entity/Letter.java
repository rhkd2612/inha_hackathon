package com.mumomu.neighborwith.entity;

import com.mumomu.neighborwith.common.SimpleDateFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "letter")
@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Letter {
    @Id
    @Column(name = "letter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id")
    private User receiver;

    // Null이여도 되나, 있으면 게시글 반환
    private String postitTitle;

    private String createTime;
    private String content;
    private boolean isAnonymous;

    public void setCreateTime(Date createTime) {
        this.createTime = SimpleDateFormatter.formatDateToString(createTime);
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}