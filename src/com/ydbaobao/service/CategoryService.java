package com.ydbaobao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydbaobao.dao.CategoryDao;
import com.ydbaobao.model.Category;

@Service
public class CategoryService {
	@Resource
	private CategoryDao categoryDao;

	public List<Category> read() {
		return categoryDao.read();
	}

	public boolean update(long categoryId, String categoryName) {
		if(categoryDao.update(categoryId, categoryName) == 1) {
			return true;
		}
		return false;
	}

	public boolean delete(long categoryId) {
		if(categoryDao.delete(categoryId) == 1) {
			return true;
		}
		return false;
	}

	public boolean create(String categoryName) {
		if(categoryDao.create(categoryName) == 1) {
			return true;
		}
		return false;
	}
	
	public Category readByCategoryId(int categoryId) {
		return categoryDao.readByCategoryId(categoryId);
	}
}