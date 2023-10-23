package com.danny.store.java.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;


@Component
public class ImageUtil {
	
	private final String IMAGE_FOLDER = "storage/image/";
	
 
	
	public String save(
			MultipartFile image,
			String parentFolder
			) throws UserBadRequestException, IOException{
		
		if(image.isEmpty() 
				|| (!image.getContentType().equalsIgnoreCase("image/png")
				&& !image.getContentType().equalsIgnoreCase("image/jpeg"))) {
			throw new UserBadRequestException("invalid image: " + image.getOriginalFilename());
		}
		
		Path folderPath = Paths.get(IMAGE_FOLDER + parentFolder);
		
		Path imagePath =  folderPath.resolve(
				Paths.get(image.getOriginalFilename())
			).normalize()
			.toAbsolutePath();
		
		Files.createDirectories(folderPath);
		
		Files.copy(
				image.getInputStream(), 
				imagePath, 
				StandardCopyOption.REPLACE_EXISTING
			);
		

				
		return  "images/" + parentFolder + "/"+ image.getOriginalFilename();
	}
	
	public List<String> saveAll(
			List<MultipartFile> images,
			String parentFolder
			) throws IOException, UserBadRequestException{
		
		List<String> paths = new ArrayList<String>(images.size());
		
		for(MultipartFile image : images) {
			paths.add(
				save(image, parentFolder)
			);
		}
		
		return paths;
	}
	
	
	public byte[] get(String path) throws PathNotFoundException{
		
		Path imagePath = Paths.get(IMAGE_FOLDER + path);
		
		
		try {
			return Files.readAllBytes(imagePath);
		} catch (IOException e) {
			throw new PathNotFoundException("resouce not found");
		}
	}
	
	public void delete(String path) throws PathNotFoundException{
		
		Path imagePath = Paths.get(IMAGE_FOLDER + path);
		
		try {
			Files.delete(imagePath);
		} catch (IOException e) {
			throw new PathNotFoundException("resouce not found");
		}
	}
	
	
	

	
	
}
