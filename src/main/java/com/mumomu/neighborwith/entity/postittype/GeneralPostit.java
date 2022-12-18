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
@AllArgsConstructor
@DiscriminatorValue("GeneralPostit")
public class GeneralPostit extends Postit {
}
