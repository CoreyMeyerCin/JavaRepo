//package com.example.bootcamp.invoice;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.bootcamp.invoiceline.InvoiceLine;
//import com.example.bootcamp.product.Product;
//
//@CrossOrigin
//@RestController
//@RequestMapping("api/invoices")
//
//public class InvoiceController {
//	
//	@Autowired
//	private InvoiceRepository invoiceRepo;
//	
//	
//	
//	@GetMapping
//	public ResponseEntity<Iterable<Invoice>> GetInvoices(){
//		var invoices = invoiceRepo.findAll();
//		return new ResponseEntity<Iterable<Invoice>>(invoices,HttpStatus.OK);
//	}
//
//	@GetMapping("{id}")
//	public ResponseEntity<Invoice> GetInvoicesById(@PathVariable int id){
//		var invoice = invoiceRepo.findById(id);
//		if(invoice.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Invoice>(invoice.get(), HttpStatus.OK);
//	}
//	@PostMapping
//	public ResponseEntity<Invoice> PostInvoice(@RequestBody Invoice invoice){
//		if(invoice == null || invoice.getId()!=0) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		var i1 = invoiceRepo.save(invoice);
//		return new ResponseEntity<Invoice>(i1, HttpStatus.OK);
//	}
//	
//	@PutMapping("/calculatetotal/{id}")
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private ResponseEntity<Invoice> CalculateInvoiceTotal(@PathVariable int id, @RequestBody Invoice invoice) {
//		if(invoice==null || invoice.getId() ==0) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		var inv1 = invoiceRepo.findById(id);
//		if(inv1.isEmpty()) {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//		invoice.setTotal(inv1);
//		
//		return new ResponseEntity<Invoice>(HttpStatus.OK);
//	}
//	
//	@PutMapping("{id}")
//	public ResponseEntity<Invoice> PutInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
//		if(invoice == null || invoice.getId()==0) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		var i1 = invoiceRepo.findById(id);
//		if(i1.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		invoiceRepo.save(invoice);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@DeleteMapping("{id}")
//	public ResponseEntity DeleteInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
//		var i1 = invoiceRepo.findById(id);
//		if(i1.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		invoiceRepo.delete(i1.get());
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//	}
//}