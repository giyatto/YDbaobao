package com.ydbaobao.controller;

import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.support.CommonUtil;
import com.ydbaobao.model.Product;
import com.ydbaobao.service.AdminConfigService;
import com.ydbaobao.service.CategoryService;
import com.ydbaobao.service.ProductService;
import com.ydbaobao.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private SearchService searchService;
	@Resource
	private ProductService productService;
	@Resource
	private AdminConfigService adminConfigService;

	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	public String searchBrand(Model model, @RequestParam String param, @RequestParam int page) {
		String paramForQuery = searchService.changeParam(param);
		
		if (paramForQuery.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = searchService.countBySearchBrandName(paramForQuery);
		List<Product> products= searchService.readByBrandName(paramForQuery, page, adminConfigService.read().getAdminDisplayProducts());

		int totalPage = CommonUtil.countTotalPage(count, CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("url", "/search/brands?param=" + param + "&page=");
		model.addAttribute("categories", categoryService.read());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", param);
		model.addAttribute("query", paramForQuery);
		return "search";
	}

	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String search(Model model, @RequestParam String param, @RequestParam int page) {
		String paramForQuery = searchService.changeParam(param);
		
		if (paramForQuery.length() < 1) {
			model.addAttribute("count", 0);
			return "search";
		}
		int count = searchService.countBySearchProductName(paramForQuery);
		List<Product> products= searchService.readByProductName(paramForQuery, page, adminConfigService.read().getAdminDisplayProducts());

		int totalPage = CommonUtil.countTotalPage(count, CommonUtil.productsPerPage);

		model.addAttribute("prev", CommonUtil.prevBlock(page));
		model.addAttribute("next", CommonUtil.nextBlock(page, totalPage));
		model.addAttribute("selectedIndex", page);
		model.addAttribute("range", IntStream.range(CommonUtil.startPage(page), CommonUtil.endPage(page, totalPage)).toArray());
		model.addAttribute("url", "/search/products?param=" + param + "&page=");
		model.addAttribute("categories", categoryService.readWithoutUnclassifiedCategory());
		model.addAttribute("products", products);
		model.addAttribute("count", count);
		model.addAttribute("terms", param);
		model.addAttribute("query", paramForQuery);
		return "search";
	}
}
