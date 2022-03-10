package com.example.bootcamp.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/vendors")

public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Vendor>> GetVendors(){
		var vendors = vendorRepo.findAll();
		return new ResponseEntity<Iterable<Vendor>>(vendors,HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<Vendor> GetVendorById(@PathVariable int id){
		var vendor = vendorRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Vendor> PostVendor(@RequestBody Vendor vendor){
		if(vendor == null || vendor.getId()!=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var v1 = vendorRepo.save(vendor);
		return new ResponseEntity<Vendor>(v1, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Vendor> PutVendor(@PathVariable int id, @RequestBody Vendor vendor) {
		if(vendor == null || vendor.getId()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var v1 = vendorRepo.findById(id);
		if(v1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendorRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeleteVendor(@PathVariable int id, @RequestBody Vendor vendor) {
		var v1 = vendorRepo.findById(id);
		if(v1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendorRepo.delete(v1.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	@GetMapping("code/{code}")
	public ResponseEntity<Vendor> GetByCode(@PathVariable String code){
		var v1 = vendorRepo.findByCode(code);
		if(v1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(v1.get(),HttpStatus.OK);
	}
}
