jQuery(document).ready(function($){
	var showServiceOnline = function(){
		var outputHtml="<div class=\"floating_ck\"><dl><dt></dt><dd class=\"consult\">";
		outputHtml+="<span>在线咨询</span><div class=\"floating_left\"><li><a id=\"qq1\">客服小王</a></li><li><a id=\"qq2\">客服小李</a></li></div>";
		outputHtml+="</dd><dd class=\"qrcord\"><span>扫一扫</span><div class=\"floating_left floating_ewm\"><img src=\"../js/images/erCode.jpg\"></img></div>";
		outputHtml+="</dd><dd class=\"quote\">	<span>电话咨询</span><div class=\"floating_left\"><p class=\"qrcord_p01\">全国免费服务热线</p>";
		outputHtml+="<p class=\"qrcord_p02\"><b>400-166-0030<b></p></div></dd></dl></div><iframe id=\"qqIframe\" height=0 width=0 src=''></iframe>";
		$("#serviceOnline").html(outputHtml);
	}
	
	
	showServiceOnline();
	$("#qq1").click(function(){
		$("#qqIframe").attr("src","tencent://message/?Menu=yes&uin=2432340482&site=qq&menu=yes");
	});
	$("#qq2").click(function(){
		$("#qqIframe").attr("src","tencent://message/?Menu=yes&uin=2750143174&site=qq&menu=yes");
	});
});

