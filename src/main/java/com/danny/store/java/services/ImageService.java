package com.danny.store.java.services;

import com.danny.store.java.exceptions.PathNotFoundException;

public interface ImageService {

	public byte[] getImage(
			String imageName, 
			String parentFolder
			) throws PathNotFoundException;
	
	public String deleteImage(
			String imageName, 
			String parentFolder
			) throws PathNotFoundException;
}
