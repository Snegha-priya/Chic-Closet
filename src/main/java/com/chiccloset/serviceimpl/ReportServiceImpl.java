package com.chiccloset.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.chiccloset.dto.ReportDTO;
import com.chiccloset.entitymodel.ReportModel;
import com.chiccloset.repository.ReportRepository;

public class ReportServiceImpl {
	@Autowired
	ReportRepository reportRepository;

	public ReportDTO get(long id) {
		ReportModel reportModel = reportRepository.findByIdAndActive(id, true);
		ReportDTO reportDTO = ReportDTO.builder().id(reportModel.getId()).customerid(reportModel.getCustomerid())
				.ordertime(LocalDateTime.now()).endDate(LocalDateTime.now()).orderitem(reportModel.getOrderitem())
				.price(reportModel.getPrice()).build();
		return reportDTO;
	}
}
