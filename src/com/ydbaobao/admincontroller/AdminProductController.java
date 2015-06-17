package com.ydbaobao.admincontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.support.JSONResponseUtil;
import com.ydbaobao.model.Brand;
import com.ydbaobao.model.Category;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.BrandService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
	@Resource
	private ProductService productService;
	@Resource
	private BrandService brandService;
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 상품 이미지 업로드 페이지 호출
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registProducts(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("unregisteredProductsCountByBrand", productService.unregisteredProductsCountByBrand());
		return "admin/productRegistration";
	}
	
	/**
	 * 상품 관리 페이지 호출
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("products", productService.readProducts());
		model.addAttribute("brands", brandService.readBrands());
		model.addAttribute("categories", categoryService.read());
		return "admin/productManager";
	}
	
	/**
	 * 상품 이미지 등록
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public String imageUpload(Model model, Product product, @RequestParam("imageFile") MultipartFile... imageFile) {
		for(MultipartFile file:imageFile) {
			int productId = productService.create(product.getBrand().getBrandId());
			product.setProductId(productId);
			String imageName = productService.uploadImage(product, file);
			productService.updateProductImage(product, imageName);
		}
		model.addAttribute("brandList", brandService.readBrands());
		model.addAttribute("unregisteredProductsCountByBrand", productService.unregisteredProductsCountByBrand());
		return "admin/productRegistration";
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAll() {
		if(productService.deleteAll()) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable int productId) {
		if(productService.delete(productId)) {
			return JSONResponseUtil.getJSONResponse("success", HttpStatus.OK);
		}
		return JSONResponseUtil.getJSONResponse("fail", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{productId}/{productName}/{categoryId}/{brandId}/{productPrice}/{productSize}/{productDescription}", method = RequestMethod.PUT)
	public @ResponseBody String update(@PathVariable int productId, @PathVariable String productName, @PathVariable int categoryId, @PathVariable int brandId, @PathVariable int productPrice, @PathVariable String productSize, @PathVariable String productDescription){
		Product product = new Product(productId, productName,new Category(categoryId), new Brand(brandId), productPrice, productDescription, productSize);
		if(productService.update(product)){
			return "success";
		}
		return "fail";
	}
}
