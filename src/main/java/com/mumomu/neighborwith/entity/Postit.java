package com.mumomu.neighborwith.entity;

import com.mumomu.neighborwith.common.SimpleDateFormatter;
import com.mumomu.neighborwith.entity.dto.PostitCreateForm;
import com.mumomu.neighborwith.entity.postittype.CourierPostit;
import com.mumomu.neighborwith.entity.postittype.DeliveryPostit;
import com.mumomu.neighborwith.entity.postittype.GeneralPostit;
import com.mumomu.neighborwith.entity.postittype.SharePostit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "postit")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder @Getter @Setter
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

    @Column(insertable = false, updatable = false)
    protected String dtype;

    protected String title;
    protected String content;
    protected String image;
    protected String buildingAddress;

    protected String createTime;
    protected boolean isAnonymous;

    public void setCreateTime(Date createTime) {
        this.createTime = SimpleDateFormatter.formatDateToString(createTime);
    }

    public static Postit newPost(PostitCreateForm postitCreateForm){
        String curDtype = postitCreateForm.getDtype();

        switch (curDtype) {
            case "SharePostit":
                return SharePostit.builder()
                        .place(postitCreateForm.getPlace())
                        .curParticipantCount(0)
                        .totalParticipantCount(postitCreateForm.getTotalParticipantCount())
                        .build();
            case "DeliveryPostit":
                return DeliveryPostit.builder()
                        .place(postitCreateForm.getPlace())
                        .curParticipantCount(0)
                        .totalParticipantCount(postitCreateForm.getTotalParticipantCount())
                        .price(postitCreateForm.getPrice())
                        .fee(postitCreateForm.getFee())
                        .totalPrice(postitCreateForm.getTotalPrice())
                        .build();
            case "CourierPostit":
                return CourierPostit.builder()
                        .place(postitCreateForm.getPlace())
                        .curParticipantCount(0)
                        .totalParticipantCount(postitCreateForm.getTotalParticipantCount())
                        .link(postitCreateForm.getLink())
                        .price(postitCreateForm.getPrice())
                        .fee(postitCreateForm.getFee())
                        .totalPrice(postitCreateForm.getTotalPrice())
                        .build();
        }

        // default
        return GeneralPostit.builder()
                .title(postitCreateForm.getTitle())
                .content(postitCreateForm.getContent())
                .image(postitCreateForm.getImage())
                .isAnonymous(postitCreateForm.isAnonymous()).build();
    }
}