package com.danny.store.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.serviceInterfaces.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@GetMapping("{parent}/{image}")
	public ResponseEntity<byte[]> getImage(
			@PathVariable("image") String imageName,
			@PathVariable("parent") String parentFolder
			) throws PathNotFoundException{
		
		byte[] image = imageService.getImage(imageName, parentFolder);
		
		return ResponseEntity
		.status(HttpStatus.OK)
		.contentType(MediaType.valueOf("image/png"))
		.body(image);
	}
}
