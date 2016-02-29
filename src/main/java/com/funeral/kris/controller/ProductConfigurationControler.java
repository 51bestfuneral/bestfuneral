package com.funeral.kris.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;

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

import com.funeral.kris.model.Wish;
import com.funeral.kris.service.WishService;
import com.mysql.jdbc.StringUtils;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping(value = "/productConfiguration")
public class ProductConfigurationControler {

	@Autowired
	private WishService wishService;

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String id = request.getParameter("id");
		//
		String rerurnUrl="<script> alert('上傳成功！');  window.location='/funeral/pages/configuration/wishConfiguration/addProduct.html';window.close();</script>";
		

		String file = this.uploadFile(request);
		FileInputStream fin = new FileInputStream(file);

		readExcel(fin);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(rerurnUrl);
		response.getWriter().flush();
	}

	public Wish buildWish(Sheet sheet, int i) {
		Wish wish = new Wish();
		String wishName = sheet.getCell(0, i).getContents(); // 第一行，第i列
		
		wish.setWishName(wishName);
		String feature = sheet.getCell(1, i).getContents();// 短号
		wish.setFeature((feature == null ||"".equals(feature) ? 0 : Integer.parseInt(feature)));

		String generalCode = sheet.getCell(2, i).getContents();
		wish.setGeneralCode(generalCode);
		String generalCate = sheet.getCell(3, i).getContents();
		wish.setGeneralCate(generalCate);

		String subCateCode = sheet.getCell(4, i).getContents();
		wish.setSubCateCode(subCateCode);
		String subCategory = sheet.getCell(5, i).getContents();
		wish.setSubCategory(subCategory);
		String material = sheet.getCell(6, i).getContents();
		wish.setMaterial(material);
		String model = sheet.getCell(7, i).getContents();
		wish.setModel(model);
		String supplier = sheet.getCell(8, i).getContents();
		wish.setSupplier(supplier);
		String wishDesc = sheet.getCell(9, i).getContents();
		wish.setWishDesc(wishDesc);
		String size = sheet.getCell(10, i).getContents();
		wish.setSize(size);
		String url = sheet.getCell(11, i).getContents();
		wish.setUrl(url);
		String unit = sheet.getCell(12, i).getContents();
		wish.setUnit(unit);
		String procurementCost = sheet.getCell(13, i).getContents();
		wish.setProcurementCost((java.math.BigDecimal) (procurementCost == null ||"".equals(procurementCost)
				? BigDecimal.ZERO : new java.math.BigDecimal(procurementCost)));

		String sellingPrice = sheet.getCell(14, i).getContents();
		wish.setSellingPrice((java.math.BigDecimal) (sellingPrice == null ||"".equals(sellingPrice) ? BigDecimal.ZERO
				: new java.math.BigDecimal(sellingPrice)));

		String xianenPrice = sheet.getCell(15, i).getContents();
		wish.setXianenPrice((java.math.BigDecimal) (xianenPrice == null ||"".equals(xianenPrice) ? BigDecimal.ZERO
				: new java.math.BigDecimal(xianenPrice)));

		String xianenDiffPrice = sheet.getCell(16, i).getContents();
		
		System.out.println("  xianenDiffPrice= "+xianenDiffPrice);
		
	
		
		wish.setXianenDiffPrice(xianenDiffPrice);

		String xiangheTotalPrice = sheet.getCell(17, i).getContents();
		System.out.println("  xiangheTotalPrice= "+xiangheTotalPrice);

		wish.setXiangheTotalPrice((java.math.BigDecimal) (xiangheTotalPrice == null ||"".equals(xiangheTotalPrice)
				? BigDecimal.ZERO : new java.math.BigDecimal(xiangheTotalPrice)));

		String xiangheDiffPrice = sheet.getCell(18, i).getContents();
		wish.setXiangheDiffPrice(xiangheDiffPrice);

		String kaimoFee = sheet.getCell(19, i).getContents();
		wish.setKaimoFee((java.math.BigDecimal) (kaimoFee == null ||"".equals(kaimoFee) ? BigDecimal.ZERO
				: new java.math.BigDecimal(kaimoFee)));

		String kaimoTime = sheet.getCell(20, i).getContents();
		wish.setKaimoTime((kaimoTime == null ||"".equals(kaimoTime) ? 0 : Integer.parseInt(kaimoTime)));

		String otherFeeA = sheet.getCell(21, i).getContents();
		wish.setOtherFeeA((java.math.BigDecimal) (otherFeeA == null ||"".equals(otherFeeA) ? BigDecimal.ZERO
				: new java.math.BigDecimal(otherFeeA)));

		String otherFeeB = sheet.getCell(22, i).getContents();
		wish.setOtherFeeB((java.math.BigDecimal) (otherFeeB == null ||"".equals(otherFeeB) ? BigDecimal.ZERO
				: new java.math.BigDecimal(otherFeeB)));

		String otherFeeC = sheet.getCell(23, i).getContents();
		wish.setOtherFeeC((java.math.BigDecimal) (otherFeeC == null ||"".equals(otherFeeC) ? BigDecimal.ZERO
				: new java.math.BigDecimal(otherFeeC)));

		String bookPrice1 = sheet.getCell(24, i).getContents();
		wish.setBookPrice1((java.math.BigDecimal) (bookPrice1 == null ||"".equals(bookPrice1) ? BigDecimal.ZERO
				: new java.math.BigDecimal(bookPrice1)));

		String bookCount1 = sheet.getCell(25, i).getContents();
		wish.setBookCount1((bookCount1 == null ||"".equals(bookCount1) ? 0 : Integer.parseInt(bookCount1)));

		String bookPrice2 = sheet.getCell(26, i).getContents();
		wish.setBookPrice2((java.math.BigDecimal) (bookPrice2 == null ||"".equals(bookPrice2) ? BigDecimal.ZERO
				: new java.math.BigDecimal(bookPrice2)));

		String bookCount2 = sheet.getCell(27, i).getContents();
		wish.setBookCount2((bookCount2 == null ||"".equals(bookCount2) ? 0 : Integer.parseInt(bookCount2)));

		String bookPrice3 = sheet.getCell(28, i).getContents();
		wish.setBookPrice3((java.math.BigDecimal) (bookPrice3 == null ||"".equals(bookPrice3) ? BigDecimal.ZERO
				: new java.math.BigDecimal(bookPrice3)));

		String bookCount3 = sheet.getCell(29, i).getContents();
		wish.setBookCount3((bookCount3 == null ||"".equals(bookCount3) ? 0 : Integer.parseInt(bookCount3)));

		String purchaseTime=sheet.getCell(30, i).getContents();
		wish.setPurchaseTime((purchaseTime == null ||"".equals(purchaseTime) ? 0 : Integer.parseInt(purchaseTime)));

		String purchaseCapacity = sheet.getCell(31, i).getContents();
		wish.setPurchaseCapacity(
				(purchaseCapacity == null ||"".equals(purchaseCapacity) ? 0 : Integer.parseInt(purchaseCapacity)));

		String returnCount = sheet.getCell(32, i).getContents();
		wish.setReturnCount((returnCount == null ||"".equals(returnCount) ? 0 : Integer.parseInt(returnCount)));

		String salesVolume = sheet.getCell(33, i).getContents();
		wish.setSalesVolume((salesVolume == null ||"".equals(salesVolume) ? 0 : Integer.parseInt(salesVolume)));

		String salesIncome = sheet.getCell(34, i).getContents();
		wish.setSalesIncome((java.math.BigDecimal) (salesIncome == null ||"".equals(salesIncome) ? BigDecimal.ZERO
				: new java.math.BigDecimal(salesIncome)));

		String totalProcurementCost = sheet.getCell(35, i).getContents();
		wish.setTotalProcurementCost(
				(java.math.BigDecimal) (totalProcurementCost == null ||"".equals(totalProcurementCost)
						? BigDecimal.ZERO : new java.math.BigDecimal(totalProcurementCost)));

		String commissionRate = sheet.getCell(36, i).getContents();
		wish.setCommissionRate((java.math.BigDecimal) (commissionRate == null ||"".equals(commissionRate)
				? BigDecimal.ZERO : new java.math.BigDecimal(commissionRate)));

		String commission = sheet.getCell(37, i).getContents();
		wish.setCommission((java.math.BigDecimal) (commission == null ||"".equals(commission) ? BigDecimal.ZERO
				: new java.math.BigDecimal(commission)));

		String badDebt = sheet.getCell(38, i).getContents();
		
		
		wish.setBadDebt((java.math.BigDecimal) (badDebt == null ||"".equals(badDebt) ? BigDecimal.ZERO
				: new java.math.BigDecimal(badDebt)));

		String grossProfit = sheet.getCell(39, i).getContents();
		wish.setGrossProfit((java.math.BigDecimal) (grossProfit == null ||"".equals(grossProfit) ? BigDecimal.ZERO
				: new java.math.BigDecimal(grossProfit)));

		String holdingCost = sheet.getCell(40, i).getContents();
		wish.setHoldingCost((java.math.BigDecimal) (holdingCost == null ||"".equals(holdingCost) ? BigDecimal.ZERO
				: new java.math.BigDecimal(holdingCost)));

		String netProfit = sheet.getCell(41, i).getContents();
		wish.setNetProfit((java.math.BigDecimal) (netProfit == null ||"".equals(netProfit) ? BigDecimal.ZERO
				: new java.math.BigDecimal(netProfit)));

		String profitLossRate = sheet.getCell(42, i).getContents();
		wish.setProfitLossRate((java.math.BigDecimal) (profitLossRate == null ||"".equals(netProfit) ? BigDecimal.ZERO
				: new java.math.BigDecimal(netProfit)));

		String holdingCount = sheet.getCell(43, i).getContents();
		
		System.out.println("==== holdingCount="+holdingCount);
		wish.setHoldingCount((holdingCount == null ||"".equals(holdingCount) ? 0 : Integer.parseInt(holdingCount)));

		String sales_channel = sheet.getCell(44, i).getContents();
		wish.setSalesChannel((sales_channel == null ||"".equals(sales_channel) ? 0 : Integer.parseInt(sales_channel)));

		String imgUrl = sheet.getCell(45, i).getContents();
		
		wish.setImgUrl(imgUrl);
		String operatorId = sheet.getCell(46, i).getContents();
		wish.setOperator_id((operatorId == null ||"".equals(operatorId) ? 0 : Integer.parseInt(operatorId)));

		String remark = sheet.getCell(47, i).getContents();
		
		String statusId=sheet.getCell(48, i).getContents();
		wish.setStatusId((statusId == null ||"".equals(statusId) ? 0 : Integer.parseInt(statusId)));

		return wish;

	}

	public void readExcel(InputStream inputStream) throws Exception {
		Workbook workbook = Workbook.getWorkbook(inputStream); // 处理输入流
		Sheet sheet = workbook.getSheet(0);// 获取第一个sheet
		int rows = sheet.getRows(); // 获取总行号

		System.out.println("---------------------rows=" + rows);

		String[][] curArr = new String[rows][2]; // 存放正确心细
		String[][] errorArr = new String[rows * 2][4]; // 存放错误信息
		int curLines = 0;
		int errorLines = 0;
		for (int i = 1; i < rows; i++) {// 遍历行获得每行信息

			Wish wish = buildWish(sheet, i);
			System.out.println("inserting ....  i=" + i);
			wishService.addResource(wish);

		}

	}

	public String uploadFile(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");

		String actionTypes = request.getParameter("actionTypes");
		String id = request.getParameter("id");
		System.out.println("actionTypes=" + actionTypes + " id=" + id);

		// Integer wishId = Integer.parseInt(id);

		// 判断提交过来的表单是否为文件上传菜单
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		System.out.println("----isMultipart=" + isMultipart);
		String loadPath = "";
		String path = "";
		if (isMultipart) {
			// 构造一个文件上传处理对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			Iterator items;
			try {
				// 解析表单中提交的所有文件内容
				items = upload.parseRequest(request).iterator();
				while (items.hasNext()) {
					String realName = "";
					FileItem item = (FileItem) items.next();
					if (!item.isFormField()) {
						// 取出上传文件的文件名称
						String name = item.getName();
						// 取得上传文件以后的存储路径
						String fileName = name.substring(name.lastIndexOf('\\') + 1, name.length());

						realName = fileName;
						fileName = System.currentTimeMillis() + "_" + fileName;

						// 上传文件以后的存储路径
						String saveDir = "/usr/local/tomcat/webapps/funeral/pages/component/wish/images";
						if (!(new File(saveDir).isDirectory())) {
							new File(saveDir).mkdir();
						}
						path = saveDir + File.separatorChar + fileName;

						loadPath = "/funeral/pages/component/wish/images/" + fileName;

						System.out.println(
								" actionTypes= " + actionTypes + " fileName= " + fileName + " loadPath=" + loadPath);

						// 上传文件
						File uploaderFile = new File(path);
						item.write(uploaderFile);
					}

					System.out.println("realName==null=====" + realName == null);

					if (realName != null && !StringUtils.isEmptyOrWhitespaceOnly(realName)) {
						return path;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();

				return null;
			}
		}
		return path;

	}

}
