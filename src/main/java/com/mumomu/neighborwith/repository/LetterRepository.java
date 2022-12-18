package com.mumomu.neighborwith.repository;

import com.mumomu.neighborwith.entity.Letter;
import com.mumomu.neighborwith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
