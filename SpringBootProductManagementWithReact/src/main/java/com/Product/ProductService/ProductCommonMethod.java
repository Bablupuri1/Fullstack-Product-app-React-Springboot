package com.Product.ProductService;

import com.Product.CustomResponse.ApiResponse;
import com.Product.Model.Product;

public interface ProductCommonMethod {


	public ApiResponse insert(Product product);
	public ApiResponse DisplayAllProduct();
	public ApiResponse DisplaybyId(Long id);
	public ApiResponse EditById(Long id, Product updatedProduct);
	public ApiResponse deleteById(Long id);


}
