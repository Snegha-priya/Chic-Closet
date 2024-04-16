package com.chiccloset.serviceimpl;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chiccloset.ApiCaller;
import com.chiccloset.dto.CategoryDTO;
import com.chiccloset.dto.ProductDTO;
import com.chiccloset.entitymodel.CategoryModel;
import com.chiccloset.entitymodel.ProductModel;
import com.chiccloset.repository.CategoryRepository;
import com.chiccloset.repository.ProductRepository;
import com.chiccloset.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ApiCaller apiCaller;

//save
	public String save(List<CategoryDTO> categoryDTOList) {
		String username = "todo";
		String existingImageName = "";
		for (CategoryDTO categoryDTO : categoryDTOList) {
			CategoryModel categoryModel = categoryRepository.findByIdAndActive(categoryDTO.getId(), true);
			if (categoryModel != null) {
				categoryModel.setName(categoryDTO.getName());
				categoryModel.setDescription(categoryDTO.getDescription());
				categoryModel.setModifiedBy(username);
				categoryModel.setModifiedTime(ZonedDateTime.now());
				existingImageName = categoryModel.getImageName();
			}
			if (categoryDTO != null) {
				categoryModel = CategoryModel.builder().id(categoryDTO.getId()).name(categoryDTO.getName())
						.description(categoryDTO.getDescription()).createdBy(username).createdTime(ZonedDateTime.now())
						.modifiedBy(username).modifiedTime(ZonedDateTime.now()).active(true).build();
			}
			if (categoryDTO.getImage() != null && categoryDTO.getImage().length > 0) {
				if (!Arrays.equals(categoryDTO.getImage(), "something".getBytes())) {
					String filename = apiCaller.invokeSaveEndpoint(categoryDTO.getImageName(),
							categoryDTO.getImage());
					categoryModel.setImageName(filename);
					if (!existingImageName.isEmpty()) {
						apiCaller.invokeTrashEndpoint(existingImageName);
					}
				}
			}
			categoryModel = categoryRepository.save(categoryModel);

		}

		return "Success";
	}

}
