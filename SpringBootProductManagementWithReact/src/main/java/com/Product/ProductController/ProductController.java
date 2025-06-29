package com.Product.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Product.CustomResponse.ApiResponse;
import com.Product.Model.Product;
import com.Product.ProductRepository.ProductRepository;
import com.Product.ProductService.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3001") // React frontend allowed
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	ProductServiceImpl productservice;

	@GetMapping("/displayall")
	public ApiResponse getAllProducts() {
		System.out.println("Display Getall  method executed....");
		return productservice.DisplayAllProduct();
	}

	@PostMapping("/insert")
	public ApiResponse createProduct(@RequestBody Product product) {
		System.out.println("Insert method Executed...... by  React(node js)");
		return productservice.insert(product);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return productRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}



	@PutMapping("/update/{id}")
	public ApiResponse updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
	    System.out.println("Edit Called... with ID: " + id);
	    return productservice.EditById(id, updatedProduct);
	}


	
	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteProduct(@PathVariable Long id) {
	    System.out.println("Delete Called... with ID in controller: " + id);
	    return productservice.deleteById(id);
	}

}
