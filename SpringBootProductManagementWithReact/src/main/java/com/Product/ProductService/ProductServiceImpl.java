package com.Product.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Product.CustomResponse.ApiResponse;
import com.Product.Model.Product;
import com.Product.ProductRepository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductCommonMethod {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public ApiResponse insert(Product product) {
		try {
			if (product == null) {
				return new ApiResponse(false, "Product Object cannot be null.", HttpStatus.BAD_REQUEST.value(), null);
			}

			if (product.getCategory() == null || product.getCategory().isBlank()) {
				return new ApiResponse(false, "Category cannot be null or empty.", HttpStatus.BAD_REQUEST.value(),
						product);
			}

			if (product.getName() == null || product.getName().isBlank()) {
				return new ApiResponse(false, "Product name cannot be null or empty.", HttpStatus.BAD_REQUEST.value(),
						product);
			}

			if (product.getPrice() == 0.0 || product.getPrice() <= 0) {
				return new ApiResponse(false, "Product price must be greater than 0.", HttpStatus.BAD_REQUEST.value(),
						product);
			}

			Product savedProduct = productRepo.save(product);
			return new ApiResponse(true, "Product Added Successfully.", HttpStatus.OK.value(), savedProduct);

		} catch (Exception e) {
			return new ApiResponse(false, "Server error", HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
	}

	@Override
	public ApiResponse DisplayAllProduct() {
		try {
			List<Product> productList = productRepo.findAll();
			if (productList.isEmpty()) {
				return new ApiResponse(false, "No products found.", HttpStatus.NOT_FOUND.value(), productList);
			} else {
				System.out.println("display product: " + productList);
				return new ApiResponse(true, "Product list fetched successfully.", HttpStatus.OK.value(), productList);
			}
		} catch (Exception e) {
			return new ApiResponse(false, "Server error while fetching products.",
					HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
	}

	@Override
	public ApiResponse DisplaybyId(Long id) {
		try {
			Optional<Product> optionalProduct = productRepo.findById(id);
			if (optionalProduct.isPresent()) {
				return new ApiResponse(true, "Product found.", HttpStatus.OK.value(), optionalProduct.get());
			} else {
				return new ApiResponse(false, "Product not found.", HttpStatus.NOT_FOUND.value(), null);
			}
		} catch (Exception e) {
			return new ApiResponse(false, "Server error while fetching product.",
					HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
	}

	@Override
	public ApiResponse EditById(Long id, Product updatedProduct) {
		try {
			if (updatedProduct == null) {
				return new ApiResponse(false, "Product object cannot be null.", HttpStatus.BAD_REQUEST.value(), null);
			}

			if (updatedProduct.getCategory() == null || updatedProduct.getCategory().isBlank()) {
				return new ApiResponse(false, "Category cannot be null or empty.", HttpStatus.BAD_REQUEST.value(),
						updatedProduct);
			}

			if (updatedProduct.getName() == null || updatedProduct.getName().isBlank()) {
				return new ApiResponse(false, "Product name cannot be null or empty.", HttpStatus.BAD_REQUEST.value(),
						updatedProduct);
			}

			if (updatedProduct.getPrice() == 0.0 || updatedProduct.getPrice() <= 0) {
				return new ApiResponse(false, "Product price must be greater than 0.", HttpStatus.BAD_REQUEST.value(),
						updatedProduct);
			}

			Optional<Product> optionalProduct = productRepo.findById(id);

			if (optionalProduct.isPresent()) {
				Product existingProduct = optionalProduct.get();

				existingProduct.setName(updatedProduct.getName());
				existingProduct.setPrice(updatedProduct.getPrice());
				existingProduct.setDescription(updatedProduct.getDescription());
				existingProduct.setCategory(updatedProduct.getCategory());

				Product savedProduct = productRepo.save(existingProduct);
				return new ApiResponse(true, "Product updated successfully.", HttpStatus.OK.value(), savedProduct);
			} else {
				return new ApiResponse(false, "Product not found for update.", HttpStatus.NOT_FOUND.value(), null);
			}

		} catch (Exception e) {
			return new ApiResponse(false, "Error occurred while updating.", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getMessage());
		}
	}

	@Override
	public ApiResponse deleteById(Long id) {
		System.out.println("provided by react deleted id  in service class is:"+id);
		try {
			
			System.out.println("delete try start---------------------");
			if (productRepo.existsById(id))
			{
				productRepo.deleteById(id);
				return new ApiResponse(true, "Product deleted successfully.", HttpStatus.OK.value(), null);
			} else 
			{
				return new ApiResponse(false, "Product not found for deletion.", HttpStatus.NOT_FOUND.value(), null);
			}
			
		
			

		} 
		catch (Exception e) 
		{
			System.out.println("Product Deleting time controller goes in catch block so we are facing this error");
			return new ApiResponse(false, "Error occurred while deleting.", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getMessage());
		}
	}

}
