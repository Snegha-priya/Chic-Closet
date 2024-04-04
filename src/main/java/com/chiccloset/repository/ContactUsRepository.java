package com.chiccloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.dto.ContactUsDTO;
import com.chiccloset.entitymodel.ContactUsModel;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUsModel, Long> {

	ContactUsModel findByIdAndActive(Long id, Boolean active);

	void save(List<ContactUsDTO> contactUsDTOList);

	List<ContactUsModel> findByActive(boolean b);

	void deleteByIdAndActive(Long id, boolean b);

}