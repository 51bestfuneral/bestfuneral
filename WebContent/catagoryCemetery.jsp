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
<link href="/js/bootstrap-3.3.6-dist/css/bootstrap.css" rel="stylesheet">
<script src="/js/bootstrap-3.3.6-dist/js/bootstrap.js"></script>
<script src="/js/generalUsage.js"></script>
<link href="/css/categoryProduct.css" rel="stylesheet">
<link rel="stylesheet" href="/js/Font-Awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/js/AccountInfo.css">
</head>
<body ng-app="catagoryApp" ng-controller="catagory" ng-init="initFunc(${filterId})">
<div class="container">
<IFRAME NAME="content_frame" width=100% SRC="/component/topBar.html?cat=cemetery" frameborder = 0 height = "67px"></IFRAME>
<div class="frameMain">
	<div class="productContainer">
		<div id="productToolBar">
			<div id="chosenBar">
				<div class="headerColumn">
					<p>您已选择:</p>
				</div>
				<div id="chosenItems">
					<div class="chosenItem" hidden>
						<span></span><span class="removeItem" id="itemFilter1">x</span>
					</div>
					<div class="chosenItem" hidden>
						<span></span><span class="removeItem" id="itemFilter2">x</span>
					</div>
					<div class="chosenItem" hidden>
						<span></span><span class="removeItem" id="itemFilter3">x</span>
					</div>
				</div>
			</div>
			<div class="normalBar" ng-repeat="filterGroup in filterGroups" on-finish-render-filters1>
				<div class="headerColumn" id="searchBar{{$index+1}}">
				    <i class="{{filterGroup.groupIcon}}" aria-hidden="true" style="{{filterGroup.style}}"></i>
					<p ng-bind="filterGroup.groupName"></p>
				</div>
				<div class="optionItem" id='{{filter.filterCode}}' ng-repeat="filter in filterGroup.filters track by $index">
					<p ng-bind="filter.filterName"></p>
				</div>
			</div>
		</div>
		<div id="sortBar">
			<div class="sortItem" id="cemeteryName">
				<p>名称排序</p>
				<i class="fa fa-sort" aria-hidden="true" style="display: none;"></i>
			</div>
			<div class="sortItem" id="district">
				<p>区域排序</p>
				<i class="fa fa-sort" aria-hidden="true" style="display: none;"></i>
			</div>
			<div class="sortItem" id="price">
				<p>平均价格</p>
				<i class="fa fa-sort" aria-hidden="true" style="display: none;"></i>
			</div>
		</div>
		<div id="loadingDiv">
			<i class="fa fa-refresh fa-spin fa-3x fa-fw"></i>
		</div>
		<div id="productGrid" hidden>
			<div class="productItem {{itemLayout.positionClass}}" id="{{itemLayout.cemeteryId}}" ng-repeat="itemLayout in itemLayouts" on-finish-render-filters>
				<div class="imageDiv">
					<img src="{{itemLayout.descImgUrl}}" class="image"></img>
				</div>
				<div class="imageDivMask">
				</div>
				<div class="addToCart" style="float:left;margin-left: 5px;">
					<i class="fa fa-university" aria-hidden="true"></i><span ng-bind="itemLayout.district"></span>
				</div>
				<div class="inforDiv">
					<div class="productDesc">
						<p ng-bind="itemLayout.cemeteryName" style="color:#333;font-weight: bold;"></p>
						<p ng-bind="itemLayout.address" style="font-size: 12px;"></p>
					</div>
					<div class="productPrice">
						<p>参考价:</p>
						<p>￥<span ng-bind="itemLayout.price.toFixed(0)" style="color:#ff5274;font-size: 14px;"></span></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<IFRAME NAME="content_frame" width=100% height=246 marginwidth=0 marginheight=0 SRC="/component/bottomBar.html" frameborder = 0></IFRAME>
</div>
</body>
<script type="text/javascript">
var app = angular.module('catagoryApp', []),
    cataFilter,
	queryedFilter,
	wishlistId,
	orderBy,
	itemFilter1 = '',
	itemFilter2 = '',
	itemFilter3 = '',
	orderBy = '',
	reverse = '',
	productElementFilter = '?generalCode=CE';
app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});
app.directive('onFinishRenderFilters1', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished1');
                });
            }
        }
    };
});
app.controller('catagory', function($scope, $http) {
	var reloadProducts = function() {
		var queryFilter = "?valid=1"+itemFilter1 + itemFilter2 + itemFilter3 + orderBy + reverse;
		
		if (queryedFilter !== queryFilter) {
		    $("#productGrid").fadeOut();
		    queryedFilter = queryFilter;
		    $("#loadingDiv").animate({
				'height': '120px'
			});
			$http.get("/cemetery/list"+queryFilter).success(function(response) {
				for (var i=1;i<=response.length;i++) {
					if (i%4 == 0) {
						response[i-1].positionClass = "rowlast";
					}
				}
				$scope.itemLayouts = response;
				$("#loadingDiv").animate({
					'height': '0px'
				});
				$("#productGrid").fadeIn();
			});
		}
	}
	$scope.initFunc = function(filterId) {
		var level,
				number;

		if (filterId !== null && filterId !== undefined) {
			level = filterId.toString().substr(0, 1);
			number = filterId.toString().substr(1, 2);

			if (level === '1') {
				itemFilter1 = "&filter1="+number;
			}
			else if (level === '2') {
				itemFilter2 = "&filter2="+number;
			}
			else {
				itemFilter3 = "&filter3="+number;
			}
		}
		reloadProducts();
	}
	$http.get("/filter/list"+productElementFilter).success(function(response) {
	    var filterGroups = [],
			filterGroup1 = {},
			filterGroup2 = {},
			filterGroup3 = {},
			filters1 = [],
		    filters2 = [],
			filters3 = [];
		for (var i=0;i<response.length;i++) {
			if (response[i].filterLevel === 1) {
				filters1.push(response[i]);
			}
			else if (response[i].filterLevel === 2) {
				filters2.push(response[i]);
			}
			else if (response[i].filterLevel === 3) {
				filters3.push(response[i]);
			}
		}
		if (filters1.length >0) {
			filterGroup1.filters = filters1;
			filterGroup1.groupName = '花费:';
			filterGroup1.groupIcon = 'fa fa-usd';
			filterGroup1.style = 'color: #419cca;font-size:20px;margin-left: 3px;';
			filterGroups.push(filterGroup1);
		}
		if (filters2.length >0) {
			filterGroup2.filters = filters2;
			filterGroup2.groupName = '地区:';
			filterGroup2.groupIcon = 'fa fa-car';
			filterGroup2.style = 'color: #9fae37;font-size:16px;';
			filterGroups.push(filterGroup2);
		}
		if (filters3.length >0) {
			filterGroup3.filters = filters3;
			filterGroup3.groupName = '特色:';
			filterGroup3.groupIcon = 'fa fa-sitemap';
			filterGroup3.style = 'color: #e44885';
			filterGroups.push(filterGroup3);
		}
		$scope.filterGroups = filterGroups;
	});
	$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
	    $(".productItem").click(function() {
			$(this).removeClass("productWishShadow");
			window.open("/cemetery/"+$(this)[0].id);
		});
		$("body").on("mousedown",".productItem",function() {
			$(this).addClass("productWishShadow");
		});
	});
	var initFilters = function() {
		var num,
			element;
		if (itemFilter1 !== '') {
            num = itemFilter1.substr(9,10);
			element = $($(".normalBar")[0]).find("#"+num);
			$("#itemFilter1").prev()[0].innerText = element.find("p")[0].innerText;
			$("#itemFilter1").closest(".chosenItem").fadeIn();
		}
		else if (itemFilter2 !== '') {
			num = itemFilter2.substr(9,10);
			element = $($(".normalBar")[1]).find("#"+num);
			$("#itemFilter2").prev()[0].innerText = element.find("p")[0].innerText;
			$("#itemFilter2").closest(".chosenItem").fadeIn();
		}
		else if (itemFilter3 !== '') {
			num = itemFilter3.substr(9,10);
			element = $($(".normalBar")[2]).find("#"+num);
			$("#itemFilter3").prev()[0].innerText = element.find("p")[0].innerText;
			$("#itemFilter3").closest(".chosenItem").fadeIn();
		}
	}
	$scope.$on('ngRepeatFinished1', function (ngRepeatFinishedEvent) {
		initFilters();
	    $("body").on("click", ".removeItem", function() {
			if ($(this)[0].id === 'itemFilter1') {
				itemFilter1 = '';
			}
			else if ($(this)[0].id === 'itemFilter2') {
				itemFilter2 = '';
			}
			else {
				itemFilter3 = '';
			}
			$(this).closest(".chosenItem").fadeOut();
			reloadProducts();
		});
		$(".normalBar").each(function(idx) {
			$(this).find(".optionItem").click(function() {

				if (idx === 0) {
					itemFilter1 = "&filter1="+$(this)[0].id;
					$("#itemFilter1").prev()[0].innerText = $(this).find("p")[0].innerText;
					$("#itemFilter1").closest(".chosenItem").fadeIn();
				}
				else if (idx === 1) {
					itemFilter2 = "&filter2="+$(this)[0].id;
					$("#itemFilter2").prev()[0].innerText = $(this).find("p")[0].innerText;
					$("#itemFilter2").closest(".chosenItem").fadeIn();
				}
				else {
					itemFilter3 = "&filter3="+$(this)[0].id;
					$("#itemFilter3").prev()[0].innerText = $(this).find("p")[0].innerText;
					$("#itemFilter3").closest(".chosenItem").fadeIn();
				}
				reloadProducts();
			});
		});
	});
	$("body").on("click", ".filterIcon", function () {
		var $this = $(this);
		
		if ($this[0].id === "mainPage") {
		    window.location.href='productAndService.html';
		}
        else if (!$this.hasClass("selected")) {
		    window.location.href='catagoryProduct.html?cata='+$this[0].id;
		}
	});
	$(".sortItem").click(function() {
	    $(".sortItem").find("i").hide();
	    if ($(this).find("i").hasClass("fa-sort")) {
		    $(this).find("i").removeClass("fa-sort");
			$(this).find("i").addClass("fa-sort-asc");
			$(this).find("i").show();
			reverse = "";
		}
		else if ($(this).find("i").hasClass("fa-sort-desc")) {
		    $(this).find("i").removeClass("fa-sort-desc");
			$(this).find("i").addClass("fa-sort-asc");
			$(this).find("i").show();
			reverse = "";
		}
		else if ($(this).find("i").hasClass("fa-sort-asc")) {
		    $(this).find("i").removeClass("fa-sort-asc");
			$(this).find("i").addClass("fa-sort-desc");
			$(this).find("i").show();
			reverse = "&reverse=1";
		}
		orderBy = "&orderBy=" + $(this)[0].id;
	    reloadProducts();
	});
});
</script>
<script type="text/javascript">
window.onload=function(){
	$(".productItem").click(function() {
			muskCurrentPage();
	});
};
</script>
</html>