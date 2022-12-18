package com.mumomu.neighborwith.entity.postittype;

import com.mumomu.neighborwith.entity.Postit;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuperBuilder @Getter
@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("SharePostit")
public class SharePostit extends Postit {
    private String place;
    private int curParticipantCount;
    private int totalParticipantCount;
}
