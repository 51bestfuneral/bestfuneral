/**
 * 
 */

	$("#design_").click(function() {
		window.location.href = "/designProposal.html";
	});
	
	$("#shopping_").click(function() {
		window.location.href = "/productAndService.html";
	});
	
	$("#cemetery_").click(function() {
		window.location.href = "/cemeteryList.html";
	});

	$("#startButton").click(function() {
		window.location.href = "/designProposal.html";
	});
	
	$("#wechatFrame").draggable();
	
	$("#weChat").hover(function() {

		$("#wechatFrame").show();
	});
	
	$("#wechatFrame").click(function() {
		$(this).fadeOut();
		$(".jquery_addmask").remove();
	});
	
	$(".closeCoverDiv").click(function() {
		$(".coverDiv").fadeOut();
		$(".jquery_addmask").remove();
		$(".productItem").css({
			opacity : 1
		});
	});