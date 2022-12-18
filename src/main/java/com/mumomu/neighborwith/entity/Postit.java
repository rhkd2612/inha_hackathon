package com.mumomu.neighborwith.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "postit")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Postit {
    @Id
    @Column(name = "postit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id")
    protected User user;

    protected String title;
    protected String content;
    protected String image;

    protected Date createTime;
    protected boolean isAnonymous;
}