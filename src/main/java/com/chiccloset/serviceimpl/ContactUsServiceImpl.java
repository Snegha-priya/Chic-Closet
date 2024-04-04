package com.chiccloset.serviceimpl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiccloset.ApiCaller;
import com.chiccloset.dto.ContactUsDTO;
import com.chiccloset.entitymodel.ContactUsModel;
import com.chiccloset.exception.VaruvaaiException;
import com.chiccloset.repository.ContactUsRepository;
import com.chiccloset.service.ContactUsService;

import jakarta.transaction.Transactional;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsRepository contactUsRepository;
	private final Logger logger = LoggerFactory.getLogger(ContactUsServiceImpl.class);

	@Autowired
	ApiCaller apiCaller;

	// list save
	public String save(List<ContactUsDTO> contactUsDTOList) {
		String username = "todo";

		for (ContactUsDTO contactUsDTO : contactUsDTOList) {
			ContactUsModel contactUsModel = contactUsRepository.findByIdAndActive(contactUsDTO.getId(), true);
			String existingImageName = "";
			if (contactUsModel != null) {
				contactUsModel.setTitle(contactUsDTO.getTitle());
				contactUsModel.setDescription(contactUsDTO.getDescription());
				contactUsModel.setModifiedBy(username);
				contactUsModel.setModifiedTime(ZonedDateTime.now());
				existingImageName = contactUsModel.getImageName();
			} else {
				contactUsModel = ContactUsModel.builder().id(contactUsDTO.getId()).title(contactUsDTO.getTitle())
						.description(contactUsDTO.getDescription()).createdBy("null").createdTime(ZonedDateTime.now())
						.active(true).build();
			}

			if (contactUsDTO.getImage() != null && contactUsDTO.getImage().length > 0) {
				if (!Arrays.equals(contactUsDTO.getImage(), "something".getBytes())) {
					String filename = apiCaller.invokeSaveEndpoint(contactUsDTO.getImageName(), contactUsDTO.getImage(),
							"public");
					contactUsModel.setImageName(filename);
					if (!existingImageName.isEmpty()) {
						apiCaller.invokeTrashEndpoint(existingImageName);
					}
				}
			}
			contactUsRepository.save(contactUsModel);
		}
		return "Success";
	}

	// delete
	/*
	 * public String remove(Long id) {
	 * 
	 * ContactUsModel contactUsModel = contactUsRepository.findByIdAndActive(id,
	 * true); if (contactUsModel != null) { String fileName =
	 * contactUsModel.getImageName();
	 * apiCaller.invokeRemoveEndpoint(fileName,"public");
	 * contactUsRepository.deleteByIdAndActive(id, true); return "Success"; } else {
	 * throw new VaruvaaiException("1010"); } }
	 */
@Transactional
	public String remove(Long id) {
		ContactUsModel contactUsModel = contactUsRepository.findByIdAndActive(id, true);
		if (contactUsModel != null) {
			String fileName = contactUsModel.getImageName();
			String response = apiCaller.invokeRemoveEndpoint(fileName,"public");
			// Check response or handle accordingly
			contactUsRepository.deleteByIdAndActive(id, true);
			return "Success";
		} else {
			throw new VaruvaaiException("1010");
		}
	}
	// list

	public List<ContactUsDTO> list() {
		List<ContactUsDTO> contactUsDTOList = new ArrayList<>();

		logger.debug("contactUsRepository.findByActive");

		List<ContactUsModel> contactUsModelList = contactUsRepository.findByActive(true);

		if (contactUsModelList != null) {
			for (ContactUsModel contactUsModel : contactUsModelList) {
				// image logic
				String imageUrl = "";
				if (contactUsModel.getImageName() != null && !contactUsModel.getImageName().isEmpty()) {
					imageUrl = apiCaller.invokeGetEndpoint(1, contactUsModel.getImageName(), "public");
				}
				ContactUsDTO contactUsDTO = ContactUsDTO.builder().id(contactUsModel.getId())
						.title(contactUsModel.getTitle()).description(contactUsModel.getDescription())
						.imageUrl(imageUrl).imageName(contactUsModel.getImageName()).build();
				contactUsDTOList.add(contactUsDTO);
			}
		}
		return contactUsDTOList;
	}

	

}