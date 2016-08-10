<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title>念念--产品及服务</title>
<link href="/js/images/niannian.ico" rel="shortcut icon" />
<meta name="description" content="念念网致力于打造智能葬礼产品和服务定制工具。念念主张“不要让你爱的人走的太匆忙”，鼓励充分尊重逝者意愿的提前定制，已实现“殡葬服务价格透明化、互联网化、个性化”为己任 。念念，也是中国最大的日系骨灰盒骨灰坛销售公司，最有特色的祭扫祭祀用品服务商。" /> 
<meta name="keywords" content="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="content-style-type" content="text/css" /> 
<meta http-equiv="content-script-type" content="text/javascript" />
<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/jquery-ui.js"></script>
<link rel="stylesheet" href="/js/Font-Awesome-4.4.0/css/font-awesome.min.css">
<link href="/js/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet">
<script src="/js/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<link href="/css/common.css" rel="stylesheet">
</head>
<style>
#mainImage {
	background-image: url(https://a2.muscache.com/im/pictures/52f94028-af4c-437a-b548-a3ed3dfd7c6d.jpg?aki_policy=xx_large);
	width: 100%;
	height: 400px;
	background-repeat: no-repeat;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	background-position: 50% 50%;
	background-color: #f5f5f5;
}
#middleContent {
	background-color: #ffffff;
	margin-bottom: 0px;
}
.divContain {
	width: 1080px;
	padding-left: 24px;
	padding-right: 24px;
	margin: 0px auto;
}
.king {
	border-radius: 50%;
	border: 2px solid #fff;
	overflow: hidden;
	display: inline-block;
}
.king img {
	width: 90px;
	width: 90px;
}
.container {
	width: 100%;
	padding: 0px;
}
#reTopbar {
	background-color: rgba(60,63,64,0.9);
	border: 0;
	color: #fff;
	height: 40px;
	padding: 6px 20px;
	text-transform: capitalize;
	z-index: 1001;
	overflow-y: hidden;
}
#reserve {
	width: 315px;
	margin-top: -40px;
}
#reserve input {
	font-size: 13px;
	padding: 9px 6px;
	height: 37px;
}
#infoForm {
	border: 1px solid #dce0e0;
	background-color: #ffffff;
}
.reserveBtn {
	display: inline-block;
	margin-bottom: 0;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
	border: 1px solid;
	text-align: center;
	vertical-align: middle;
	font-weight: bold;
	line-height: 1.43;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	white-space: nowrap;
	cursor: pointer;
	border-radius: 4px;
}
.naviPic {
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	background-position: 50% 50%;
	display: block;
	height: 100%;
}
.mainPic {
	height: 282px;
	margin-bottom: 3px;
}
.subPic {
	 height: 141px;
}
.subPic div {
	height: 100%;
}
.picGrid .row {
	margin-left: -3px;
	margin-right: -3px;
}
.picGrid .col-md-6 {
	padding-right: 1.5px;
	padding-left: 1.5px;
}
.picGrid .col-12 {
	padding-right: 1.5px;
	padding-left: 1.5px;
}
#referenceContent {
	width: 1080px;
	margin: 0px auto;
	overflow: auto;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	margin-bottom: 30px;
	padding-top: 30px;
	padding-left: 24px;
	padding-right: 24px;
}
#referenceContainer {
	width: 100%;
}
.singleReference {
	width: 25%;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	float: left;
	cursor: pointer;
	padding-left: 12.5px;
	padding-right: 12.5px;
}
.imageDiv {
	height: 210px;
	overflow: hidden;
}
.imageDiv img {
	width: 100%;
	height: 100%;
}
.priceFloat {
	padding: 7px 10px;
	background-color: rgba(60,63,64,0.9);
	color: #fff;
	position: absolute;
	margin-left: 20px;
	font-size: 24px;
	margin-top: -80px;
}
.refDesc {
	padding-top: 10px;
	font-size: 16px;
	padding-left: 6px;
	padding-bottom: 10px;
}
#otherType {
	width: 1080px;
	margin: 0px auto;
	padding-left: 24px;
	padding-right: 24px;
}
#otherNavi {
	width: 100%;
	padding-bottom: 40px;
}
#otherNavi a{
	font-size: 14px;
	text-decoration: none;
	color: #82888a;
	cursor: pointer;
}
#otherNavi a:hover{
	text-decoration: underline;
}
</style>
<body>
    <IFRAME NAME="content_frame" width=100% SRC="/component/topBar.html?cat=cemetery" frameborder = 0 height = "67px"></IFRAME>
    <div class="container">
	    <div id="mainImage">
		</div>
		<div class="panel" id="middleContent">
			<div class="divContain" id="midContain">
				<div class="row">
					<div id="midLeft" class="col-lg-8 space-4-top space-4">
						<div>
							<div class="row">
								<div class="col-md-3 text-center">
									<div class="king">
										<img src="https://a0.muscache.com/im/users/9722571/profile_pic/1383158449/original.jpg?aki_policy=profile_x_medium"></img>
									</div>
								</div>
								<div id="mainInfo" class="col-md-9">
									<div class="space-1">
										<h3>1 Bedroom Apt on First Hill</h3>
									</div>
									<div class="space-3 text-muted">
										<span>西雅图, 华盛顿, 美国 · </span>
									</div>
									<div class="row row-condensed text-muted text-center">
                                        <div class="col-sm-3 icon-size-2">
											<i class="fa fa-car"></i>
										</div>
										<div class="col-sm-3 icon-size-2">
											<i class="fa fa-car"></i>
										</div>
										<div class="col-sm-3 icon-size-2">
											<i class="fa fa-car"></i>
										</div>
										<div class="col-sm-3 icon-size-2">
											<i class="fa fa-car"></i>
										</div>
									</div>
								</div>
							</div>
							<div class="row text-center text-muted">
								<div class="col-md-3">
                                    <span>Will</span>
								</div>
								<div class="col-md-9">
									<div class="row row-condensed">
                                        <div class="col-sm-3">
											<span>整套房子/公寓</span>
										</div>
										<div class="col-sm-3">
											<span>2位房客</span>
										</div>
										<div class="col-sm-3">
											<span>0间卧室</span>
										</div>
										<div class="col-sm-3">
											<span>1张床</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class ="col-lg-4" style="height: 171px;">
						<div id="reserve">
							<div id="reTopbar">
                                <span class="h3">$ 652</span>
								<span style="float: right;">每晚</span>
							</div>
							<div class="panel panel-body" id="infoForm">
								<div class="row row-condensed">
									<div class="col-md-9">
                                        <div class="row row-condensed space-3">
                                            <div class="col-md-6">
                                                <label>入住</label>
												<input type="text" placeholder="年-月-日"/>
											</div>
											<div class="col-md-6">
												<label>退房</label>
												<input type="text" placeholder="年-月-日"/>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<label>退房</label>
										<input type="text" placeholder="人"/>
									</div>
								</div>
								<div class="space-2">
									<button class="reserveBtn btn-primary btn-block btn-lg"><span>申请预定</span></button>
								</div>
								<div class="text-center">
									<span class="text-muted">您的信用卡不会被收费</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="mainContent">
			<div class="divContain">
                <div class="row">
					<div class="col-lg-8 space-8 space-8-top">
						<div>
							<h4><span style="font-weight: bold;">关于此房源</span></h4>
							<p class="space-2-top"><span>Very clean, non pet, non smoking apt in the heart of it all. Minutes away from Downtown or Capital Hill. Lyft, Uber and the Light Rail easy access.</span></p>
						</div>
						<hr/>
						<div class="row">
                            <div class="col-md-3">
                                <span>房源</span>
							</div>
							<div class="col-md-9">
                                <div class="row">
									<div class="col-md-6">
                                        <div>
											<span>可住： <span style="font-weight: bold;">2</span></span>
										</div>
										<div>
											<span>卫生间： <span style="font-weight: bold;">2</span></span>
										</div>
										<div>
											<span>床型 <span style="font-weight: bold;">实体床</span></span>
										</div>
										<div>
											<span>卧室： <span style="font-weight: bold;">0</span></span>
										</div>
										<div>
											<span>床位： <span style="font-weight: bold;">1</span></span>
										</div>
									</div>
									<div class="col-md-6">
										<div>
											<span>入住时间： <span style="font-weight: bold;">14:00 后</span></span>
										</div>
										<div>
											<span>退房时间： <span style="font-weight: bold;">10:00</span></span>
										</div>
										<div>
											<span>房源类型： <span style="font-weight: bold;">公寓</span></span>
										</div>
										<div>
											<span>房间类型： <span style="font-weight: bold;">整套房子/公寓</span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
                                <span>便利设施</span>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-6">
										<div>
											<i class="fa fa-car"></i>
											<span>厨房</span>
										</div>
										<div>
											<i class="fa fa-car"></i>
											<span>电视</span>
										</div>
									</div>
									<div class="col-md-6">
										<div>
											<i class="fa fa-car"></i>
											<span>生活必需品</span>
										</div>
										<div>
											<i class="fa fa-car"></i>
											<span>暖气</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>价格</span>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-6">
                                        <div>
											<span>额外房客：<span style="font-weight: bold;">￥103 / 晚 每超出一人计</span></span>
										</div>
										<div>
											<span>清洁费：<span style="font-weight: bold;">￥137</span></span>
										</div>
									</div>
									<div class="col-md-6">
										<div>
											<span>退订政策：<span style="font-weight: bold;">灵活</span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
                                <span>描述</span>
							</div>
							<div class="col-md-9">
								<span>很干净,无宠物,禁烟在这一切的心脏贴切。分钟,从市区或国会山了。 Lyft,尤伯杯和轻轨方便地访问。</span>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>《房屋守则》</span>
							</div>
							<div class="col-md-9">
								<p>禁止吸烟</p>
								<p>不适合宠物</p>
								<p>入住时间是14:00后</p>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>安全设施</span>
							</div>
							<div class="col-md-9">
								<span>烟雾报警器</span>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>可租状态</span>
							</div>
							<div class="col-md-9">
								<span>最少住宿天数2晚。</span>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>旅游指南</span>
							</div>
							<div class="col-md-9">
								<span>在西雅图可以做的事</span>
							</div>
						</div>
						<div id="pictureWall" class="picGrid space-4-top">
							<div class="row">
							    <div class="col-12 mainPic">
								    <a class="naviPic" style="background-image: url('https://a2.muscache.com/im/pictures/48faaa47-28ad-417f-bf63-c20a290e3506.jpg?aki_policy=x_large');"></a>
							    </div>
							</div>
							<div class="subPic">
								<div class="row">
									<div class="col-md-6">
										<a class="naviPic" style="background-image: url('https://a2.muscache.com/im/pictures/e4973a51-d599-4cc3-b148-9661e79dc9d8.jpg?aki_policy=x_large');"></a>
									</div>
									<div class="col-md-6">
										<a class="naviPic" style="background-image: url('https://a2.muscache.com/im/pictures/cd55f1d2-0315-494c-94f3-4049088fd851.jpg?aki_policy=x_large');"></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="addressContent" style="background-color: #ffffff;">
            <div class="divContain space-8-top space-8">
				<div class="row">
					<div class="col-lg-8">
						<h4>地理位置</h4>
						<hr/>
						<div class="row">
							<div class="col-md-3 text-center">
								<div class="king">
									<img src="https://a0.muscache.com/im/users/9722571/profile_pic/1383158449/original.jpg?aki_policy=profile_x_medium"></img>
								</div>
							</div>
							<div class="col-md-9">
                                <h3 class="space-1"><b>Will</b></h3>
								<div class="space-2">
									<span>西雅图, 华盛顿, 美国 · 注册时间：2013年10月</span>
								</div>
								<div class="space-2">
									<span>Work in the aviation industry and have traveled the world. Love to travel, sports, hike, kayak and fish. </span>
								</div>
								<div class="space-2">
									<span>You can find me playing tennis, volleyball or softball in my down time as well as exploring some of the greatest places on earth.  </span>
								</div>
								<div>
									<span>回复率 <b>100%</b> </span>
								</div>
								<div>
									<span>回复时间: <b>1小时内</b> </span>
								</div>
							</div>
						</div>
						<hr/>
						<h4>地图详情</h4>
					</div>
				</div>
				<div class="">
					<h4>地图详情</h4>
                    <div></div>
				</div>
			</div>
		</div>
		<div id="referenceContent">
			<h4 class="space-4">相类似产品</h4>
			<div id="referenceContainer">
				<div class="singleReference" ng-repeat="relevantWish in relevantWishs" id="{{relevantWish.wishId}}">
					<div class="imageDiv">
						<image src="../js/images/SS001011001.jpg" alt="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制"></image>
					</div>
					<div class="priceFloat">
						<span>￥</span><span>300</span>
					</div>
					<div class="addToWishFloat">
					</div>
					<div class="refDesc">
						<span>福寿园</span>
					</div>
				</div>
				<div class="singleReference" ng-repeat="relevantWish in relevantWishs" id="{{relevantWish.wishId}}">
					<div class="imageDiv">
						<image src="../js/images/SS001011001.jpg" alt="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制"></image>
					</div>
					<div class="priceFloat">
						<span>￥</span><span>300</span>
					</div>
					<div class="addToWishFloat">
					</div>
					<div class="refDesc">
						<span>福寿园</span>
					</div>
				</div>
				<div class="singleReference" ng-repeat="relevantWish in relevantWishs" id="{{relevantWish.wishId}}">
					<div class="imageDiv">
						<image src="../js/images/SS001011001.jpg" alt="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制"></image>
					</div>
					<div class="priceFloat">
						<span>￥</span><span>300</span>
					</div>
					<div class="addToWishFloat">
					</div>
					<div class="refDesc">
						<span>福寿园</span>
					</div>
				</div>
				<div class="singleReference" ng-repeat="relevantWish in relevantWishs" id="{{relevantWish.wishId}}">
					<div class="imageDiv">
						<image src="../js/images/SS001011001.jpg" alt="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制"></image>
					</div>
					<div class="priceFloat">
						<span>￥</span><span>300</span>
					</div>
					<div class="addToWishFloat">
					</div>
					<div class="refDesc">
						<span>福寿园</span>
					</div>
				</div>
			</div>
		</div>
		<div id="otherType">
			<hr/>
			<h4>您还可以看看其他的类别</h4>
			<div id="otherNavi">
				<a href="catagoryProduct.html?cata=II-JJ">骨灰盒</a>&nbsp;·
				<a href="catagoryProduct.html?cata=SS">寿衣</a>&nbsp;·
				<a href="catagoryProduct.html?cata=M-S-W">祭祀随葬</a>&nbsp;·
				<a href="catagoryProduct.html?cata=G-H-L-T">礼仪服务</a>&nbsp;·
				<a href="catagoryProduct.html?cata=VV-O">祭扫用品</a>&nbsp;·
				<a href="catagoryProduct.html?cata=P-Q-R-U">套装用品</a>&nbsp;·
				<a href="catagoryProduct.html?cata=NN-X-N">其他产品</a>
			</div>
		</div>
	</div>
    <IFRAME NAME="content_frame" width=100% height=246 marginwidth=0 marginheight=0 SRC="/component/bottomBar.html" frameborder = 0></IFRAME>
</body>
</html>