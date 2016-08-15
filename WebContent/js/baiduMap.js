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
			 var label = new BMap.Label("<div class='commentMap panel'><h3>"+displayName+"</h3><hr/><h5>"+displayAddress+"</h5></div>",{point:centerPoint,offset:new BMap.Size(-800,200)});
			 map.addOverlay(label);
			 label.setStyle({borderColor:"#999"});
			 return;
		 }
	}
}