package com.mumomu.neighborwith.repository;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.Postit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostitRepository extends JpaRepository<Postit, Long> {
}
