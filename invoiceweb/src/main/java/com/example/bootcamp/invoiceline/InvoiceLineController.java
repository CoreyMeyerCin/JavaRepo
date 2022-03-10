package com.example.bootcamp.invoiceline;

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
@RequestMapping("api/invoiceLines")

public class InvoiceLineController {
	
	@Autowired
	private InvoiceLineRepository invoiceLineRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<InvoiceLine>> GetInvoiceLines(){
		var invoiceLines = invoiceLineRepo.findAll();
		return new ResponseEntity<Iterable<InvoiceLine>>(invoiceLines,HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<InvoiceLine> GetInvoiceLinesById(@PathVariable int id){
		var invoiceLines = invoiceLineRepo.findById(id);
		if(invoiceLines.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InvoiceLine>(invoiceLines.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<InvoiceLine> PostInvoice(@RequestBody InvoiceLine invoiceLines){
		if(invoiceLines == null || invoiceLines.getId()!=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var i1 = invoiceLineRepo.save(invoiceLines);
		return new ResponseEntity<InvoiceLine>(i1, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<InvoiceLine> PutInvoice(@PathVariable int id, @RequestBody InvoiceLine invoiceLines) {
		if(invoiceLines == null || invoiceLines.getId()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var i1 = invoiceLineRepo.findById(id);
		if(i1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invoiceLineRepo.save(invoiceLines);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity DeleteInvoice(@PathVariable int id, @RequestBody InvoiceLine invoiceLines) {
		var i1 = invoiceLineRepo.findById(id);
		if(i1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invoiceLineRepo.delete(i1.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}