package com.danny.store.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.serviceInterfaces.ImageService;
import com.danny.store.java.util.ImageUtil;


@Service
public class ImageServiceImp implements ImageService{
	
	@Autowired
	ImageUtil imageUtil;

	@Override
	public byte[] getImage(
	String imageName, 
	String parentFolder
	) throws PathNotFoundException{
		return imageUtil.get(
				parentFolder + "/" + imageName
				);
	}

	@Override
	public void deleteImage(
	String imageName, 
	String parentFolder
	) throws PathNotFoundException{
		imageUtil.delete(
				parentFolder + "/" + imageName
				);
	}
	
	
}
