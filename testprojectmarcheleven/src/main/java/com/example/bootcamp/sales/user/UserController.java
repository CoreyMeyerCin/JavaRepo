package com.example.bootcamp.sales.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> GetUser(){
		var users =userRepo.findAll();
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> GetUserById(@PathVariable int id){
	var user = userRepo.findById(id);
	if(user.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<User>(user.get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> PostUser(@RequestBody User user){
		if (user == null || user.getId() != 0){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var u1 = userRepo.save(user);
		return new ResponseEntity<User>(u1,HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> PutUser(@RequestBody User user, @PathVariable int id){
		if(user == null || user.getId()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var u1 = userRepo.findById(id);
		if(u1.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepo.save(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<User> DeleteUser(@PathVariable int id){
		var user = userRepo.findById(id);
	if(user.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	userRepo.delete(user.get());
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	
	}
	
}
