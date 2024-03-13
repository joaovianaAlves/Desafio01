package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import com.example.demo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.objects.Livro;

@RestController
public class RestDemoController {

	@Autowired
	FirebaseService fireBaseService;

	@GetMapping("/getBookDetails")
	public Livro getExample(@RequestHeader String titulo) throws InterruptedException, ExecutionException {
		return fireBaseService.getBookDetails(titulo);
	}

	@PostMapping("/createUser")
	public String postExample(@RequestBody Livro livro) throws InterruptedException, ExecutionException {
		return fireBaseService.saveBookDetails(livro);
	}

	@PutMapping("/updateUser")
	public String putExample(@RequestBody Livro livro) throws InterruptedException, ExecutionException {
		return fireBaseService.updateBookDetails(livro);
	}

	@DeleteMapping("/deleteUser")
	public String deleteExample(@RequestHeader String titulo) {
		return fireBaseService.deleteBook(titulo);
	}
}
