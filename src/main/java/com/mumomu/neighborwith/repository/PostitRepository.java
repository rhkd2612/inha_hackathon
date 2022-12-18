package com.mumomu.neighborwith.repository;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Postit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostitRepository extends JpaRepository<Postit, Long> {
    List<Postit> findAllByBuildingAddress(String buildingAddress);
}
