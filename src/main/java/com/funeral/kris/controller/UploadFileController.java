package com.funeral.kris.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.model.Cemetery;
import com.funeral.kris.service.CemeteryService;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping(value = "/uploadFile")
public class UploadFileController extends HttpServlet {

	@Autowired
	private CemeteryService cemeteryService;

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		
		String rerurnUrl="<script> window.location='/funeral/pages/configuration/cemeteryConfiguration/updateCemetery.html?id=$' ;window.close();</script>";
		rerurnUrl=rerurnUrl.replace("$", id);
		
		this.uploadFile(request);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(rerurnUrl);
		response.getWriter().flush();
	}

	public void uploadHeadImg(Integer cemeteryId, String imgPath) {
		
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);
		
		System.out.println("-------------  uploadHeadImg ----imgPath = "+imgPath);
		
		cemetery.setHeadImg(imgPath);
		
		cemeteryService.updateResource(cemetery);
	}
	public void uploadDescriptionImg(Integer cemeteryId, String imgPath) {

		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		System.out.println("-------------  uploadDescriptionImg ----imgPath = "+imgPath);
		
		cemetery.setDescImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);
	}

	public void uploadFeatureImg(Integer cemeteryId, String imgPath) {
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		cemetery.setFeatureImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);

	}

	public void uploadTrafficInfoImg(Integer cemeteryId, String imgPath) {
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		cemetery.setLocationImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);

	}

	public void uploadMoreImgs(Integer cemeteryId, String imgPath) {
		// TODO Auto-generated method stub

	}

	public void uploadFile(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");

		String actionTypes = request.getParameter("actionTypes");
		String id = request.getParameter("id");
		System.out.println("actionTypes=" + actionTypes + " id=" + id);

		Integer cemeteryId = Integer.parseInt(id);

		// 判断提交过来的表单是否为文件上传菜单
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		System.out.println("----isMultipart=" + isMultipart);


		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			Iterator items;
			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					String realName="";
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());

						 realName=fileName;
						fileName = System.currentTimeMillis() + "_" + fileName;

						// 上传文件以后的存储路径
						String saveDir = "/usr/local/tomcat/webapps/funeral/pages/component/cemetery/images";
						if (!(new File(saveDir).isDirectory())) {
							new File(saveDir).mkdir();
						}
						String path = saveDir + File.separatorChar + fileName;
						
						String loadPath="/funeral/pages/component/cemetery/images/"+fileName;

						System.out.println(" actionTypes= " + actionTypes + " fileName= " + fileName+" loadPath="+loadPath);
						if ("uploadDescriptionImg".equals(actionTypes)) {

							this.uploadDescriptionImg(cemeteryId, loadPath);
						} else if ("uploadFeatureImg".equals(actionTypes)) {

							this.uploadFeatureImg(cemeteryId, loadPath);

						} else if ("uploadTrafficInfoImg".equals(actionTypes)) {
							this.uploadTrafficInfoImg(cemeteryId, loadPath);

						} else if ("uploadMoreImgs".equals(actionTypes)) {
							this.uploadMoreImgs(cemeteryId, loadPath);

						}else{
							
							this.uploadHeadImg(cemeteryId, loadPath);
						}
						// 上传文件
						File uploaderFile = new File(path);
						item.write(uploaderFile);
					}
					
					System.out.println("realName==null====="+realName==null);
					
					if(realName!=null&&!StringUtils.isEmptyOrWhitespaceOnly(realName)){
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

				return;
			}
		}

	}

}
