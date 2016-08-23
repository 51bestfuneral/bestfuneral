<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<title>念念--墓地信息</title>
<link href="/js/images/niannian.ico" rel="shortcut icon" />
<meta name="description" content="念念网致力于打造智能葬礼产品和服务定制工具。念念主张“不要让你爱的人走的太匆忙”，鼓励充分尊重逝者意愿的提前定制，已实现“殡葬服务价格透明化、互联网化、个性化”为己任 。念念，也是中国最大的日系骨灰盒骨灰坛销售公司，最有特色的祭扫祭祀用品服务商。" /> 
<meta name="keywords" content="念念,念念网,葬礼定制,殡葬,上海殡葬,殡葬服务,上海墓地， 骨灰盒，骨灰坛，寿衣，日本骨灰盒，葬礼花费，佛事服务，私人定制，生前契约，葬礼保险,提前定制" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="pragma" content="no-cache" /> 
<meta http-equiv="content-style-type" content="text/css" />
<meta http-equiv="content-script-type" content="text/javascript" /> 
<link href="/css/cemetery.css" rel="stylesheet">
<link href="/js/personal.css" rel="stylesheet">
<link href="/component/css/secondLevelMenu.css" rel="stylesheet">
<script src="/js/generalUsage.js"></script>
</head>
<body ng-app="productApp" ng-controller="cemetery" ng-init='testFunc(${cemetery})'>
<IFRAME NAME="content_frame" width=100% SRC="/component/topBar.html?cat=cemetery" frameborder = 0 height = "67px"></IFRAME>
<div id="mainHeader"  style={{"background: url("+cemetery.descImgUrl+");"}} >
    <div id="leftTop">
	    <p><span class="headerLabel"><a href="cemeteryList.html">墓地信息</a></span>／<span  ng-bind="cemetery.cemeteryName"></span></p>
	</div>
	<div id="centerText">
        <h1 ng-bind="cemetery.cemeteryName"></h1>
	    <h2 ng-bind="cemetery.district"></h2>
	</div>
	<div id="labelsArea">
		<span class="keylabel label-default"  ng-repeat="keyword in cemetery.keywordsList" ng-bind="keyword.keyword"></span>
		
	</div>
</div>
<div class="container">
<!-- <div class="redline" id="redline1"></div>
<div class="redline" id="redline2"></div>
<div class="redline" id="redline3"></div>
<div class="redline" id="redline4"></div>
<div class="redline" id="redline5"></div>
<div class="redline" id="redline6"></div> -->
<div id="cemeteryFilter" ng-cloak>
    <div id="filter">
		<div id="filterCata">
			<div class="filterCata">
			    <p>墓穴</p>
			</div>
			<div class="filterCata">
			    <p>碑型</p>
			</div>
			
		</div>
		<div id="filterContent">
			<div class="filterContent">
			    <div ng-repeat="graveStyle in cemetery.graveStyleList" >
				    <p id={{'g'+graveStyle.id}} ng-click="updatePrice('g',graveStyle.id)" ng-bind="graveStyle.description"></p>
				</div>
			
			</div>
			<div class="filterContent">
			    <div  ng-repeat="epigraphStyle in cemetery.epigraphStyleList" >
				    <p id={{'e'+epigraphStyle.id}} ng-click="updatePrice('e',epigraphStyle.id)"  ng-bind="epigraphStyle.style"></p>
				</div>
				
			</div>
		
			
			</div>
		</div>
	</div>
	<div id="reserver" ng-cloak  style={{display}} >
		
		<h3><span ng-bind="cemeteryPrice.description"></span>:￥<span ng-bind="cemeteryPrice.price"></span></h3>
		<button class="reserverBtn" id="visitButton">在线预约</button>
		<p>我们会帮您安排参观访问</p>

	</div>
</div>
<div id="features" ng-cloak>
    <div id="featureContent">
	    <div id="singleFeature">
		    <div id="mainFeature">
			    <div class="leftFeatureImg" >
			    	
			    <img src={{cemetery.descImgUrl}}></img>
				</div>
				<div class="rightFeatureText">
				    <h1>简介</h1>
				    <p ng-bind-html="cemetery.cemeteryDesc|trustAsHtml"></p>
				</div>
			</div>
			<div id="locationFeature">
			    <div class="leftlocationText">
				     <h1>特色</h1>
<p ng-bind-html="cemetery.feature|trustAsHtml"></p>				</div>
				<div class="rightlocationImg">
					 <img src={{cemetery.featureImgUrl}}></img>
				</div>
			</div>
			<div id="equipmentFeature">
			    <div class="leftEquipmentImg">
			    	
					 <img src={{cemetery.locationImgUrl}}></img>
			
				</div>
				<div class="rightEquipmentText">
				    <h1>交通信息</h1>
				    <p ng-bind-html="cemetery.trafficInfo|trustAsHtml"></p>
				</div>
			</div>
			
		
			
		</div>
	</div>
</div>
<div id="cemeteryMap">
<div id="cemeteryMapContext">
  </div>
</div>
</div>
</div>
<IFRAME NAME="content_frame" width=100% height=246 marginwidth=0 marginheight=0 SRC="/component/bottomBar.html" frameborder = 0></IFRAME>
</body>
<script src="/js/jquery-2.1.4.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/sliderx.js"></script>
<script src="/js/angular-1.4.0-rc.2/angular.js"></script>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.js"></script>
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<script src="/js/Bootstrap/boot.js"></script>
<script src="/js/generalUsage.js"></script>
<script>
    var gid;
    var eid;
	var app = angular.module('productApp', []),
	cemeteryListBean = [];
	app.filter('trustAsHtml', function($sce) {
		  return function(html) {
		    return $sce.trustAsHtml(html);
		  };
	});
    app.controller('cemetery', function($scope, $http) {
	 	$scope.selectedGraveStyleId=-1;
	 	$scope.selectedEpigraphStyleId=-1;
	 	$scope.display="";
	    $scope.testFunc = function(cemetery) {
			$scope.cemetery = cemetery;
			cemeteryListBean.push(cemetery);
			initMap(cemetery.cemeteryName,cemetery.address);
			$http.get("/cemetery/listCemeteryPrice?id="+cemetery.cemeteryId).success(function(response) {
				$scope.cemeteryPrice = response[0];
				gid=$scope.cemeteryPrice.graveStyleId;
				eid=$scope.cemeteryPrice.epigraphStyleId;
				$("#g"+gid).css("background-color","#7E7E7E");
				$("#e"+eid).css("background-color","#7E7E7E");
				$scope.selectedGraveStyleId=$scope.cemeteryPrice.graveStyleId;
				$scope.selectedEpigraphStyleId=$scope.cemeteryPrice.epigraphStyleId;
			});
		}
        $scope.updatePrice = function(from,id){
	        if(from=='e') {
		        $("#e"+$scope.selectedEpigraphStyleId).css("background-color","");
		        $scope.selectedEpigraphStyleId=id;
		        $("#e"+$scope.selectedEpigraphStyleId).css("background-color","#7E7E7E");
            }
			else{
			    $("#g"+$scope.selectedGraveStyleId).css("background-color","");
                $scope.selectedGraveStyleId=id;
		        $("#g"+$scope.selectedGraveStyleId).css("background-color","#7E7E7E");
			}
	        if($scope.selectedEpigraphStyleId!=-1&&$scope.selectedGraveStyleId!=-1) {
		        $http.get("/cemetery/getCemeteryPriceBygraveStyleIdAndEpigraphStyleId?cemeteryId="+$scope.cemetery.cemeteryId+"&graveStyleId="+$scope.selectedGraveStyleId+"&epigraphStyleId="+$scope.selectedEpigraphStyleId).success(function(response) {
	                $scope.cemeteryPrice = response;
	                if($scope.cemeteryPrice==null||$scope.cemeteryPrice=="") {
	                    $scope.display="visibility: hidden;";
	                }else{
			            $scope.display="";
                    }
	            });
			}
		}
	});
	app.controller('products', function($scope, $http) {
		$http.get("/wish/list").success(function(response) {
			var j = 1,
				counter = 1,
				numberInRow = 4,
				objectsInRow = [],
				layouts = [];
			for (var i=0;i<response.length;i++) {
				var singleProduct = response[i];

				if (j <= numberInRow) {
					objectsInRow.push(singleProduct);
				}

				if (j === numberInRow) {
					var layout = {},
						active = '',
						itemLayout = objectsInRow;

					if (counter === 1) {
						active = 'active'
					}
					layout = {
						'index': counter,
						'active': active,
						'itemLayout': itemLayout
					};
					layouts.push(layout);
					j = 0;
					counter++;
					objectsInRow = [];
				}
				j++;
			}
			$scope.layouts = layouts;
		});
		$http.get("/message/list").success(function(response) {
			response[0].active = 'active';
			$scope.messages = response;
		});
	});
var zoomList =[{cemetery:'清竹园',zoom:15},{cemetery:'汇龙园',zoom:16},{cemetery:'华南陵园',zoom:17},
               {cemetery:'海港陵园（福寿园）',zoom:18},{cemetery:'天逸静园玫瑰园',zoom:16},{cemetery:'福寿园',zoom:20},
               {cemetery:'至尊园',zoom:16},{cemetery:'淀山湖归园',zoom:17},{cemetery:'九天陵园',zoom:18},
               {cemetery:'福泉山留园',zoom:17},{cemetery:'滨海古园',zoom:17},{cemetery:'海湾园',zoom:21},{cemetery:'永福园陵',zoom:21},
               {cemetery:'华夏公墓',zoom:17},{cemetery:'瀛新古园',zoom:21}];

var polygonPoints=[{'cemetery': '清竹园', 'points':[{point:"121.187644|31.37094"},{point:"121.197131|31.374362"},{point:"121.200149|31.375318"}
					,{point:"121.206114|31.375595"},{point:"121.210749|31.37383"},{point:"121.216462|31.372196"},{point:"121.217971|31.371302"},{point:"121.219157|31.370901"},{point:"121.199712|31.356397"}]}, 
                   {'cemetery': '汇龙园', 'points': [{point:"121.849054|31.088793"},{point:"121.849099|31.088885"},{point:"121.850438|31.08938"},{point:"121.852293|31.086036"}
                   	,{point:"121.852293|31.086036"},{point:"121.852293|31.086036"},{point:"121.853582|31.083361"},{point:"121.852486|31.082975"},{point:"121.852338|31.083029"},{point:"121.850451|31.086164"}]},     
                   {'cemetery': '华南陵园', 'points': [{point:"121.187644|31.37094"}]},
                   {'cemetery': '海港陵园（福寿园）', 'points': [{point:"121.187644|31.37094"}]},
                   {'cemetery': '天逸静园玫瑰园', 'points': [{point:"121.72363|31.28543"},{point:"121.725004|31.286464"},{point:"121.727016|31.283748"},{point:"121.725516|31.282707"}]},
                   {'cemetery': '福寿园', 'points': [{point:"121.187644|31.37094"}]},
                   {'cemetery': '至尊园', 'points': [{point:"121.051878|31.087222"},{point:"121.051357|31.087562"},{point:"121.052453|31.088892"},{point:"121.057753|31.090423"},{point:"121.057933|31.088057"}]},
                   {'cemetery': '滨海古园', 'points': [{point:"121.72531|30.85994"},{point:"121.72558|30.860033"},{point:"121.72664|30.859816"},{point:"121.726729|30.859335"},{point:"121.727484|30.858731"},{point:"121.727017|30.858188"},{point:"121.726909|30.857925"},
                                                   {point:"121.72664|30.857739"},{point:"121.726586|30.857382"},{point:"121.725795|30.857258"},{point:"121.725166|30.857894"},{point:"121.724807|30.858591"}]}];
                 
var markerPoints=[{'cemetery': '清竹园','point':"121.200239|31.36887"},
                 {'cemetery': '汇龙园','point':"121.851893|31.085058"},
                 {'cemetery': '华南陵园','point':"121.615093|31.017948"},
                 {'cemetery': '海港陵园（福寿园）','point':"121.897606|30.934152"},
                 {'cemetery': '天逸静园玫瑰园','point':"121.725283|31.284597"},
                 {'cemetery': '福寿园','point':"121.137293|31.117528"},
                 {'cemetery': '至尊园','point':"121.05552|31.088965"},
                 {'cemetery': '淀山湖归园','point':"121.072981|31.141163"},
                 {'cemetery': '九天陵园','point':"121.096108|31.294172"},
                 {'cemetery': '滨海古园','point':"121.725921|30.858715"},
                 {'cemetery': '海湾园','point':"121.519805|30.821643"},
                 {'cemetery': '永福园陵','point':"121.710847,30.924981"},
                 {'cemetery': '华夏公墓','point':"121.130137|31.059752"},
                 {'cemetery': '瀛新古园','point':"121.532893|31.63693"},
                 {'cemetery': '福泉山留园','point':"121.188725|31.176293"} 
                 ];


function loadScript() {
	 var script = document.createElement("script"); //创建script标签
	 script.type = "text/javascript";
	 script.src = "http://api.map.baidu.com/api?key=pgOKUoG9Ka9htE13UW9HD6yf&v=1.1&services=true"; //异步加载的关键
	 document.body.appendChild(script); //添加到页面
	}

	
function initMap(cemeteryName,cemeteryAddress){
	cemeteryName=$.trim(cemeteryName);
	
	cemeteryAddress =$.trim(cemeteryAddress);
    createMap();//创建地图
    setMapEvent();//设置地图事件
    addMapControl();//向地图添加控件
	    	var options = {
	    	        onSearchComplete: function(results){
	    	        	if (local.getStatus() == BMAP_STATUS_SUCCESS){
	    	                for (var i = 0; i < results.getCurrentNumPois(); i ++){
	    	                	
	    	 		 			var x =results.getPoi(i).point.lng;
	    	 		 			var y =results.getPoi(i).point.lat;
	    	 		 			resetCenterAndZoom(x,y,cemeteryName,cemeteryAddress);
	    	 		 			addRemark(x,y,cemeteryName,cemeteryAddress);//向地图中添加文字标注
	    	 					break;	
	    	                 }
	    	             }
	    	      }
	    	    };
	    	var local = new BMap.LocalSearch(map, options);
	    	local.search(cemeteryAddress);
}

//创建地图函数：
function createMap(){
    var map = new BMap.Map("cemeteryMapContext");//在百度地图容器中创建一个地图
    var point = new BMap.Point(121.194109,31.369983);//定义一个中心点坐标
    map.centerAndZoom(point,15);//设定地图的中心点和坐标并将地图显示在地图容器中
    window.map = map;//将map变量存储在全局 
}

//地图事件设置函数：
function setMapEvent(){
   // map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
   // map.enableScrollWheelZoom();//启用地图滚轮放大缩小
  //  map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
   // map.enableKeyboard();//启用键盘上下左右键移动地图
}

//地图控件添加函数：
function addMapControl(){
    //向地图中添加缩放控件
var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
map.addControl(ctrl_nav);
    //向地图中添加缩略图控件
//var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
//map.addControl(ctrl_ove);
    //向地图中添加比例尺控件
var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
map.addControl(ctrl_sca);
}

//向地图中添加标注
function addRemark(x,y,title,address){
	for(var i=0;i<markerPoints.length;i++){
		 var json = markerPoints[i];
		 var cemetery= json.cemetery;
		 var point = json.point;
		 if(cemetery==title){
				 var p1 = point.split("|")[0];
			     var p2 = point.split("|")[1];
			     var marker = new BMap.Marker(new BMap.Point(p1,p2));  // 创建标注
			     map.addOverlay(marker);
		 } 
	}


var pointsArray =[];
for(var i=0;i<polygonPoints.length;i++){
 var json = polygonPoints[i];
 var cemetery= json.cemetery;
 var points = json.points;
 if(cemetery==title){
	 for(var j=0;j<points.length;j++){
		 var p1 = points[j].point.split("|")[0];
	     var p2 = points[j].point.split("|")[1];
	 	 pointsArray.push(new BMap.Point(p1, p2));
	 }
 }
     
}
var polygon = new BMap.Polygon(pointsArray,{strokeColor:"#FFBBC1", strokeWeight:2, strokeOpacity:0.3,fillColor:"#FFBBC1"});  //创建多边形
map.addOverlay(polygon);
}

function resetCenterAndZoom(x,y,cemeteryName,address){
	var displayAddress=address;
	var index = address.indexOf('区');
	if(index!=-1){
		displayAddress = address.substring(0,index+1)+"<br>"+address.substring(index+1,address.length);
	}else{
		index=address.indexOf('县');
		if(index!=-1){
			displayAddress = address.substring(0,index+1)+"<br>"+address.substring(index+1,address.length);
		}else{
			index=address.indexOf('市');
			if(index!=-1){
				displayAddress = address.substring(0,index+1)+"<br>"+address.substring(index+1,address.length);
			}
		}
	}
	var displayName = cemeteryName;
	if(displayName.length>6){
		displayName = cemeteryName.substring(0,4)+"<br>"+cemeteryName.substring(4,cemeteryName.length);
	}
	var centerPoint = new BMap.Point(x,y);//定义一个中心点坐标
	for(var i=0;i<zoomList.length;i++){
		var json = zoomList[i];
		 var cemetery= json.cemetery;
		 if(cemetery==cemeteryName){
			 
		 	 var zoom = json.zoom;
			 map.centerAndZoom(centerPoint,zoom);
			 var label = new BMap.Label("<div class='commentMap'><h1>"+displayName+"</h1><h2>"+displayAddress+"</h2></div>",{point:centerPoint,offset:new BMap.Size(-800,200)});
			 map.addOverlay(label);
			 label.setStyle({borderColor:"#999"});
			 return;
		 }
	}
}
$(".reserverBtn").click(function() {
	popupRequestPage();
});
//initMap();//创建和初始化地图



</script>
</html>