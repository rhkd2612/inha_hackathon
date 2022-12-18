package com.mumomu.neighborwith.repository;

import com.mumomu.neighborwith.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
