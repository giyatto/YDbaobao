package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydbaobao.exception.ExceptionForMessage;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductsService;
import com.ydbaobao.util.JSONResponseUtil;

@Controller
@RequestMapping("/api/products")
public class ApiProductsController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private BrandService brandService;
	@Resource
	private ProductsService productsService;
	
	public ResponseEntity<Object> readAsRange(@RequestParam int start) {
//		try {
			return JSONResponseUtil.getJSONResponse(productsService.readRange(start, 16), HttpStatus.OK);
//		} catch (ExceptionForMessage e) {
//			return JSONResponseUtil.getJSONResponse(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
//		}
	}
}