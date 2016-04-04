package com.funeral.kris.upload.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConfigureCemeteryService {
	
	public void uploadDescriptionImg(Integer cemeteryId,String imgPath);
	public void uploadFeatureImg(Integer cemeteryId,String imgPath);
	public void uploadTrafficInfoImg(Integer cemeteryId,String imgPath);
	public void uploadMoreImgs(Integer cemeteryId,String imgPath);
	public void uploadFile(HttpServletRequest request, HttpServletResponse response)throws Exception ;
}
