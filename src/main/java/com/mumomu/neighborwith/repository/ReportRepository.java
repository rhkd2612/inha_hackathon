package com.mumomu.neighborwith.repository;

import com.mumomu.neighborwith.entity.Postit;
import com.mumomu.neighborwith.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByOwnerTel(String ownerTel);
}
