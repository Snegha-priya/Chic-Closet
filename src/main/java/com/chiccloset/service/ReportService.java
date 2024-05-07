package com.chiccloset.service;

import java.time.LocalDateTime;
import java.util.List;

import com.chiccloset.dto.ContactUsDTO;
import com.chiccloset.dto.ReportDTO;

public interface ReportService {

	String remove(Long id);

	List<ReportDTO> list(LocalDateTime startDate, LocalDateTime enddate);

	String getReport();
	
 }