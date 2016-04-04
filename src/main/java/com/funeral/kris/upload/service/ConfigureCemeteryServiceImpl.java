package com.funeral.kris.upload.service;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funeral.kris.model.Cemetery;
import com.funeral.kris.service.CemeteryService;
import com.funeral.kris.service.CemeteryServiceImpl;
@Service
@Transactional
public class ConfigureCemeteryServiceImpl extends HttpServlet implements ConfigureCemeteryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static CemeteryService cemeteryService=new CemeteryServiceImpl(); ;

	@Override
	public void uploadDescriptionImg(Integer cemeteryId, String imgPath) {

		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		cemetery.setDescImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);
	}

	@Override
	public void uploadFeatureImg(Integer cemeteryId, String imgPath) {
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		cemetery.setFeatureImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);

	}

	@Override
	public void uploadTrafficInfoImg(Integer cemeteryId, String imgPath) {
		Cemetery cemetery = cemeteryService.getResource(cemeteryId);

		cemetery.setLocationImgUrl(imgPath);

		cemeteryService.updateResource(cemetery);

	}

	@Override
	public void uploadMoreImgs(Integer cemeteryId, String imgPath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String actionTypes = request.getParameter("actionTypes");
		String id = request.getParameter("id");
		System.out.println("actionTypes=" + actionTypes + " id=" + id);

		Integer cemeteryId = Integer.parseInt(id);

		// 鍒ゆ柇鎻愪氦杩囨潵鐨勮〃鍗曟槸鍚︿负鏂囦欢涓婁紶鑿滃崟
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			// 鏋勯�涓�釜鏂囦欢涓婁紶澶勭悊瀵硅薄
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			Iterator items;
			try {
				// 瑙ｆ瀽琛ㄥ崟涓彁浜ょ殑鎵�湁鏂囦欢鍐呭
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 鍙栧嚭涓婁紶鏂囦欢鐨勬枃浠跺悕绉�
						String name = item.getName();
						// 鍙栧緱涓婁紶鏂囦欢浠ュ悗鐨勫瓨鍌ㄨ矾寰�
						String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());

						fileName = System.currentTimeMillis() + "_" + fileName;

						// 涓婁紶鏂囦欢浠ュ悗鐨勫瓨鍌ㄨ矾寰�
						String saveDir = "/funeral/pages/component/cemetery/images";
						if (!(new File(saveDir).isDirectory())) {
							new File(saveDir).mkdir();
						}
						String path = saveDir + File.separatorChar + fileName;

						System.out.println(" path= " + path + " fileName= " + fileName);
						if ("uploadDescriptionImg".equals(actionTypes)) {

							this.uploadDescriptionImg(cemeteryId, path);
						} else if ("uploadFeatureImg".equals(actionTypes)) {

							this.uploadFeatureImg(cemeteryId, path);

						} else if ("uploadTrafficInfoImg".equals(actionTypes)) {
							this.uploadTrafficInfoImg(cemeteryId, path);

						} else if ("uploadMoreImgs".equals(actionTypes)) {
							this.uploadMoreImgs(cemeteryId, path);

						}
						// 涓婁紶鏂囦欢
						File uploaderFile = new File(path);
						item.write(uploaderFile);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().append("");
		}

	}

}
