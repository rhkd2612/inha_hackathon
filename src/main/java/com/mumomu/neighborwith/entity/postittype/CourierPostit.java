package com.mumomu.neighborwith.entity.postittype;

import com.mumomu.neighborwith.entity.Postit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuperBuilder @Getter
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("CourierPostit")
public class CourierPostit extends Postit {
    private String place;
    private int curParticipantCount;
    private int totalParticipantCount;

    private String link;
    private int price;
    private int fee;
    private int total_price;
}
