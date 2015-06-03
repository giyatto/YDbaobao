package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.BrandDao;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;

@Service
public class BrandService {

	@Resource
	private BrandDao brandDao;

	public List<Brand> readBrands() {
		return brandDao.readBrands();
	}

	public int createBrand(String brandName) {
		if(brandDao.readBrandByBrandName(brandName) != null) {
			// TODO 브랜드명 중복 예외처리
		}
		return brandDao.createBrand(brandName);
	}

	public void updateBrand(String brandId, String brandName) {
		if(brandDao.readBrandByBrandName(brandName) != null) {
			// TODO 브랜드명 중복 예외처리
		}
		brandDao.updateBrand(brandId, brandName);
	}

	public void deleteBrand(String brandId) {
		brandDao.deleteBrand(brandId);
	}

	public List<Brand> search(String firstLetter) {
		return brandDao.search(firstLetter);
	}
}
