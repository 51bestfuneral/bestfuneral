var zoomRate = 10;
var maxRate = 500;
var minRate = 50;
var currZoom = 100;

function GetCookie(name){
if (parent.document.cookie != "") {
zoomc = parent.document.cookie.split("; ");
for (var i=0; i < zoomc.length; i++) {
zoomv = zoomc[i].split("=");
if (zoomv[0] == name) {
return  unescape(zoomv[1]);
}
}
}else{
return "";
}
}
function SetCookie(name,value){
	parent.document.cookie = name + "=" + escape (value)+";";
}

function zoomInOut(contentid, how) {
	
if(GetCookie("zoomVal") != null && GetCookie("zoomVal") != ""){
	parent.document.all[contentid].style.zoom = GetCookie("zoomVal");
currZoom=GetCookie("zoomVal");

}
else{
	parent.document.all[contentid].style.zoom = '100%';
currZoom = '100%';
}

if(how == "same"){
	parent.document.style.zoom = '100%';
	currZoom = '100%';	
}
if (((how == "in") && (parseInt(currZoom) >= maxRate)) || ((how == "out") && (parseInt(currZoom) <= minRate)) ) {
return;
}
if (how == "in") {
	parent.document.all[contentid].style.zoom = parseInt(parent.document.all[contentid].style.zoom)+zoomRate+'%';
}
else {
	parent.document.all[contentid].style.zoom = parseInt(parent.document.all[contentid].style.zoom)-zoomRate+'%'
}
SetCookie("zoomVal",parent.document.all[contentid].style.zoom);
showCurrZoom(contentid);
}

function initZoom(contentid) {
	

if(GetCookie("zoomVal") != null && GetCookie("zoomVal") != ""){
	parent.document.all[contentid].style.zoom = GetCookie("zoomVal");
currZoom=GetCookie("zoomVal");
}
else{
	parent.document.all[contentid].style.zoom = '100%';
currZoom = '100%';
}
showCurrZoom(contentid);
}
function showCurrZoom(contentid) {

	parent.document.all['showZoom'].innerText = parent.document.all[contentid].style.zoom;
}