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
<script src="/js/angular-1.4.0-rc.2/angular.js"></script>
<link rel="stylesheet" href="/js/Font-Awesome-4.4.0/css/font-awesome.min.css">
<link href="/js/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet">
<script src="/js/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.js"></script>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<script src="/js/baiduMap.js" charset="UTF-8"></script>
<link href="/css/common.css" rel="stylesheet">
<script src="/js/generalUsage.js"></script>
<script src="../js/timepicker/js/moment-with-locales.js"></script>
<script src="../js/timepicker/js/bootstrap-datetimepicker.js"></script>
</head>
<style>
#mainImage {
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
	height: 90px;
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
.reTopbar-fixed {
	position: fixed;
	top: 0px;
	margin-top: 0px;
	width: 315px;
}
#reserve {
	width: 315px;
	margin-top: -40px;
	z-index: 1001;
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
	cursor: pointer;
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
#cemeteryMapContext {
	width: 100%;
	height: 479px;
	object-fit: contain;
}
.commentMap {
	width: 315px;
	height: 184px;
	opacity: 0.8;
	text-align: center;
	position: absolute;
	margin-top: -295px;
	margin-left: 402px;
	padding: 25px;
}
.commentMap h1 {
	font-size: 44px;
	line-height: 66px;
	color: #ffffff;
	margin-bottom: 12px;
}
.commentMap h2 {
	font-size: 20px;
	line-height: 32px;
	color: #ffffff;
	margin: 0px;

}
#naviBar {
	position: fixed;
	background-color: #484848;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	top: 0px;
	z-index: 1000;
	width: 100%;
}
.singleNavi {
	margin: 10px 14px 0px 14px;
	padding-bottom: 5px;
	float: left;
	color: #bbb;
	cursor: pointer;
	border-bottom: 5px solid;
	border-color: transparent;
}
.singleNavi:hover {
	color: #ffffff;
}
.inNavi {
	border-color: #bbb;
}
.bootstrap-datetimepicker-widget {
	width: 200px;
}
.bootstrap-datetimepicker-widget .table-condensed {
	width: 100%;
}
</style>
<body ng-app="productApp" ng-controller="cemetery" ng-init='initFunc(${cemetery})'>
    <IFRAME NAME="content_frame" width=100% SRC="/component/topBar.html?cat=cemetery" frameborder = 0 height = "67px"></IFRAME>
    <div class="container">
	    <div id="mainImage" style="background-image: url({{cemetery.headImg}});">
		</div>
		<div class="panel" id="middleContent">
			<div class="divContain" id="midContain">
				<div class="row">
					<div id="midLeft" class="col-lg-8 space-4-top space-4">
						<div>
							<div class="row">
								<div class="col-md-3 text-center">
									<div class="king">
										<img src="{{cemetery.headImg}}"></img>
									</div>
								</div>
								<div id="mainInfo" class="col-md-9">
									<div class="space-1">
										<h3 ng-bind="cemetery.cemeteryName">福寿园</h3>
									</div>
									<div class="space-3 text-muted">
										<span ng-bind="cemetery.address">上海市外青松公路7270弄600号 </span>
									</div>
									<div class="row row-condensed text-muted text-center">
                                        <div class="col-sm-3 icon-size-2" ng-repeat="icon in cemetery.keywordsList">
											<i class="{{icon.imgUrl}}" aria-hidden="true"></i>
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
                                        <div class="col-sm-3" ng-repeat="icon in cemetery.keywordsList">
											<span ng-bind="icon.keyword"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class ="col-lg-4" style="height: 171px;">
						<div id="reserve">
							<div id="reTopbar">
                                <span class="h3">$ <span ng-bind="cemetery.price"></span></span>
								<span style="float: right;">（参考均价）</span>
							</div>
							<div class="panel panel-body" id="infoForm">
								<div class="row row-condensed">
									<div class="col-md-9">
                                        <div class="row row-condensed space-2">
                                            <div class="col-md-12">
                                                <label>参观日期</label>
												<input type="text" placeholder="年-月-日" id="datetimepicker1"/>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<label>人数</label>
										<input type="text" placeholder="人"/>
									</div>
								</div>
								<div class="row space-4">
									<div class="col-md-12">
										<label>联系人手机号码</label>
										<input type="text" placeholder="输入手机号码" id="phoneNumber"/>
									</div>
								</div>
								<div class="space-2">
									<button class="reserveBtn btn-primary btn-block btn-lg"><span>申请参观</span></button>
								</div>
								<div class="text-center">
									<span class="text-muted">申请参观不会收取任何费用</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel" id="mainContent" style="background-color: #f5f5f5;">
			<div class="divContain">
                <div class="row">
					<div class="col-lg-8 space-8 space-8-top">
						<div>
							<h4><span style="font-weight: bold;">关于此墓园</span></h4>
							<p class="space-2-top"><span ng-bind="cemetery.cemeteryDesc">上海福寿园地处青浦城南，毗邻松江。优美的自然景色、独特的人文景观以及风格迥异的艺术雕塑，使整个园区气势恢宏、风范典雅。</span></p>
						</div>
						<hr/>
						<div class="row">
                            <div class="col-md-3">
                                <span>详细信息</span>
							</div>
							<div class="col-md-9">
                                <div class="row">
									<div class="col-md-6">
                                        <div>
											<span>始建时间： <span style="font-weight: bold;" ng-bind="cemetery.fund"></span></span>
										</div>
										<div>
											<span>墓园性质： <span style="font-weight: bold;" ng-bind="cemetery.firm"></span></span>
										</div>
										<div>
											<span>墓园级别： <span style="font-weight: bold;" ng-bind="cemetery.level"></span></span>
										</div>
										<div>
											<span>停车场： <span style="font-weight: bold;" ng-bind="cemetery.stop"></span></span>
										</div>
										<div>
											<span>碑文样式： <span style="font-weight: bold;" ng-bind="cemetery.textStyle"></span></span>
										</div>
									</div>
									<div class="col-md-6">
										<div>
											<span>开园时间： <span style="font-weight: bold;" ng-bind="cemetery.openTime"></span></span>
										</div>
										<div>
											<span>闭园事件： <span style="font-weight: bold;" ng-bind="cemetery.closeTime"></span></span>
										</div>
										<div>
											<span>公墓规模： <span style="font-weight: bold;" ng-bind="cemetery.size"></span></span>
										</div>
										<div>
											<span>墓园名人： <span style="font-weight: bold;" ng-bind="cemetery.famous"></span></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
                                <span>在售墓型</span>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-6" ng-repeat="style in cemetery.cemeteryPrices">
										<div>
											<i class="fa fa-university" aria-hidden="true"></i>
											<span ng-bind="style.graveStyle"></span>
											<span ng-bind="style.epigraphStyle"></span>
											<span ng-bind="style.price" class="pinked"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>园区</span>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-6" ng-repeat="style in cemetery.graveZoneList">
                                        <div>
											<span ng-bind="style.zone"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
                                <span>特色</span>
							</div>
							<div class="col-md-9">
								<span ng-bind="cemetery.feature"></span>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>购墓须知</span>
							</div>
							<div class="col-md-9">
								<p ng-repeat="notice in cemetery.notices" ng-bind="notice"></p>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>免费服务</span>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div>
										<div class="col-md-6" ng-repeat="service in cemetery.freeService">
											<div>
												<span ng-bind="service"></span>
											</div>
										</div>
									</div>
									<div class="col-md-12 space-2-top">
										<span class="pinked">以上仅供参考，可能会有变动</span>
									</div>
								</div>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>墓地景观</span>
							</div>
							<div class="col-md-9">
								<span ng-bind="cemetery.views"></span>
							</div>
						</div>
						<hr/>
						<div class="row">
							<div class="col-md-3">
								<span>特色服务</span>
							</div>
							<div class="col-md-9">
								<span ng-bind="cemetery.specialService"></span>
							</div>
						</div>
						<div id="pictureWall" class="picGrid space-4-top">
							<div class="row">
							    <div class="col-12 mainPic">
								    <a class="naviPic" style="background-image: url('{{cemetery.descImgUrl}}');"></a>
							    </div>
							</div>
							<div class="subPic">
								<div class="row">
									<div class="col-md-6">
										<a class="naviPic" style="background-image: url('{{cemetery.featureImgUrl}}');"></a>
									</div>
									<div class="col-md-6">
										<a class="naviPic" style="background-image: url('{{cemetery.locationImgUrl}}');"></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="addressContent" class="panel" style="background-color: #ffffff;">
            <div class="divContain space-8-top space-8">
				<div class="row">
					<div class="col-lg-8">
						<h4>交通信息</h4>
						<hr/>
						<div class="row">
							<div class="col-md-3 text-center">
								<div class="king">
									<img src="{{cemetery.headImg}}"></img>
								</div>
							</div>
							<div class="col-md-9">
                                <h3 class="space-1"><b ng-bind="cemetery.cemeteryName"></b></h3>
								<div class="space-2">
									<span ng-bind="cemetery.address"></span>
								</div>
								<div ng-repeat="path in cemetery.TrafficInfo">
									<span ng-bind="path"></span>
								</div>
								<div class="space-2 space-2-top">
									<span>墓园内有停车场，车位500个，10元/小时，附近商场也可停车  </span>
								</div>
								<div>
									<span>好评率： <b>100%</b> </span>
								</div>
								<div>
									<span>墓园步行时间: <b>1小时内</b> </span>
								</div>
							</div>
						</div>
						<hr/>
					</div>
				</div>
				<div>
					<h4 class="space-4">地图详情</h4>
					<div id="cemeteryMap">
						<div id="cemeteryMapContext">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="referenceContent">
			<h4 class="space-4">同区域的其它墓地</h4>
			<div id="referenceContainer">
				<div class="row">
					<div class="singleReference col-md-4" ng-repeat="refer in referList" ng-click="naviToCemetery(refer.cemeteryId)">
						<div class="imageDiv">
							<image src="{{refer.headImg}}" alt="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制"></image>
						</div>
						<div class="priceFloat">
							<span>￥</span><span ng-bind="refer.price"></span>
						</div>
						<div class="addToWishFloat">
						</div>
						<div class="refDesc">
							<span ng-bind="refer.cemeteryName"></span>
						</div>
					</div>
			    </div>
			</div>
		</div>
		<div id="otherType">
			<hr/>
			<h4>您还可以看看其他的区的墓园</h4>
			<div id="otherNavi">
				<a href="/cemetery/g/24">浦东新区</a>&nbsp;·
				<a href="/cemetery/g/21">青浦区</a>&nbsp;·
				<a href="/cemetery/g/27">金山</a>&nbsp;·
				<a href="/cemetery/g/22">奉贤区</a>&nbsp;·
				<a href="/cemetery/g/23">崇明县</a>&nbsp;·
				<a href="/cemetery/g/25">松江区</a>&nbsp;·
				<a href="/cemetery/g/26">嘉定区</a>&nbsp;
			</div>
		</div>
		<div id="naviBar" hidden>
            <div class="divContain">
                <div class="row">
					<div class="singleNavi">
						<span>照片</span>
					</div>
					<div class="singleNavi">
						<span>详细信息</span>
					</div>
					<div class="singleNavi">
						<span>交通信息</span>
					</div>
					<div class="singleNavi">
						<span>其它选择</span>
					</div>
				</div>
			</div>
		</div>
	</div>
    <IFRAME NAME="content_frame" width=100% height=246 marginwidth=0 marginheight=0 SRC="/component/bottomBar.html" frameborder = 0></IFRAME>
</body>
<script charset="UTF-8">
	var app = angular.module('productApp', []);
	var cemeteryId;
	app.controller('cemetery', function($scope, $http) {
		$scope.initFunc = function(cemetery) {
			$scope.cemetery = cemetery;
			cemeteryId = cemetery.cemeteryId;
			initMap(cemetery.cemeteryName,cemetery.address);
			$scope.referList = cemetery.otherCemeteries;
		}
		$scope.naviToCemetery = function(id) {
			window.open('/cemetery/'+id);
		}
	});
	$(window).scroll(function(){
		var topPosition = document.body.scrollTop;
		var startPosition = $("#middleContent").offset().top - 40;
		if (topPosition > startPosition) {
			$("#naviBar").show();
		}
		else {
			$("#naviBar").hide();
		}

		if (topPosition >= startPosition && topPosition <=$("#addressContent").offset().top) {
			$("#reserve").css({
				'position': 'fixed',
				'top': '0px',
				'margin-top': '0px'
			});
			if ($("#reTopbar").hasClass("reTopbar-fixed")) {
				$("#reTopbar").removeClass("reTopbar-fixed");
			}
		}
		else if (topPosition < startPosition) {
			$("#reserve").css({
				'position': 'relative',
				'margin-top': '-40px'
			});
			if ($("#reTopbar").hasClass("reTopbar-fixed")) {
				$("#reTopbar").removeClass("reTopbar-fixed");
			}
		}
		else {
			$("#reserve").css({
				'position': 'absolute',
				'top': $("#addressContent").offset().top - $("#middleContent").offset().top+40
			});
			$("#reTopbar").addClass("reTopbar-fixed");
		}

		if (topPosition >=$("#referenceContent").offset().top-40) {
			if (!$($(".singleNavi")[3]).hasClass("inNavi")) {
				$(".singleNavi").removeClass("inNavi");
				$($(".singleNavi")[3]).addClass("inNavi");
			}
		}
		else if (topPosition >=$("#addressContent").offset().top-40) {
			if (!$($(".singleNavi")[2]).hasClass("inNavi")) {
				$(".singleNavi").removeClass("inNavi");
				$($(".singleNavi")[2]).addClass("inNavi");
			}
		}
		else if (topPosition >=$("#mainContent").offset().top -40) {
			if (!$($(".singleNavi")[1]).hasClass("inNavi")) {
				$(".singleNavi").removeClass("inNavi");
				$($(".singleNavi")[1]).addClass("inNavi");
			}
		}
	});
	$($(".singleNavi")[0]).click(function() {
        $("html,body").animate({scrollTop:$("#mainImage").offset().top-40},300);
	});
	$($(".singleNavi")[1]).click(function() {
		$("html,body").animate({scrollTop:$("#mainContent").offset().top-40},300);
	});
	$($(".singleNavi")[2]).click(function() {
		$("html,body").animate({scrollTop:$("#addressContent").offset().top-40},300);
	});
	$($(".singleNavi")[3]).click(function() {
		$("html,body").animate({scrollTop:$("#referenceContent").offset().top-40},300);
	});
	$('#datetimepicker1').datetimepicker({
		format: "YYYY-MM-DD",
		locale: "zh-cn"
	});
	$(".reserveBtn").click(function(e) {
		if ($("#phoneNumber")[0].value === null || $("#phoneNumber")[0].value === '') {
			$("#phoneNumber").focus();
			e.stopPropagation();
		}
		else if ($("#datetimepicker1")[0].value === '') {
			$("#datetimepicker1").focus();
			e.stopPropagation();
		}
		else {
			sendVistReuqest(cemeteryId, $("#datetimepicker1")[0].value, $("#phoneNumber")[0].value);
		}
	});
</script>
</html>