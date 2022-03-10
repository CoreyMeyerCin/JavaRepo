package com.example.bootcamp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@CrossOrigin
@RestController
@RequestMapping("api/products")

public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> GetProducts(){
		var products = productRepo.findAll();
		return new ResponseEntity<Iterable<Product>>(products,HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> GetProductById(@PathVariable int id){
		var product = productRepo.findById(id);
		if(product.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Product> PostProduct(@RequestBody Product product){
		if(product == null || product.getId()!=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var p1 = productRepo.save(product);
		return new ResponseEntity<Product>(p1, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> PutProduct(@PathVariable int id, @RequestBody Product product) {
		if(product == null || product.getId()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var p1 = productRepo.findById(id);
		if(p1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		productRepo.save(product);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeleteProduct(@PathVariable int id, @RequestBody Product product) {
		var p1 = productRepo.findById(id);
		if(p1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		productRepo.delete(p1.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	@GetMapping("partNbr/{partNbr}")
	public ResponseEntity<Product> GetByPartNbr(@PathVariable String partNbr){
		var p1 = productRepo.findByPartNbr(partNbr);
		if(p1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(p1.get(),HttpStatus.OK);
	}
}
