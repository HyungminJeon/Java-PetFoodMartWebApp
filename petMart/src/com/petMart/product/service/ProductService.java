package com.petMart.product.service;

import java.util.List;

import com.petMart.product.vo.ProductVO;


public interface ProductService {
	List<ProductVO> productSelectList();
	ProductVO productSelect(ProductVO vo);
	int insertProduct(ProductVO vo);
	int updateProduct(ProductVO vo);
	int deleteProduct(ProductVO vo);
}
