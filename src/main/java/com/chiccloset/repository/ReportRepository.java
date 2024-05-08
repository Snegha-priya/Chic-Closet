package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.ReportModel;

@Repository
public interface ReportRepository extends JpaRepository<Long, ReportModel> {

	ReportModel findByIdAndActive(long id, boolean active);

}
