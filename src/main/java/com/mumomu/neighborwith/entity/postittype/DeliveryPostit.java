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
@DiscriminatorValue("DeliveryPostit")
public class DeliveryPostit extends Postit {
    private String place;
    private int curParticipantCount;
    private int totalParticipantCount;

    private int price;
    private int fee;
    private int totalPrice;

    public boolean participant(){
        if(curParticipantCount + 1 <= totalParticipantCount){
            curParticipantCount += 1;
            return true;
        }

        return false;
    }
}
