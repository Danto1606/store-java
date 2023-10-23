package com.danny.store.java.serviceInterfaces;

import com.danny.store.java.exceptions.PathNotFoundException;

public interface ImageService {

	public byte[] getImage(
			String imageName, 
			String parentFolder
			) throws PathNotFoundException;
	
	public void deleteImage(
			String imageName, 
			String parentFolder
			) throws PathNotFoundException;
}
