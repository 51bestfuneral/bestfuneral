function muskCurrentPage() {
    $(this).css({
	    opacity: 0.5
	});
	$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
		position: 'absolute',
		top: '0px',
		left: '0px',
		'z-index': 1000, 
		width: $(document).width(), 
		height: $(document).height(), 
		'background-color': 'rgba(0,0,0,.6)', 
		opacity: 0.5
	}).fadeIn('slow', function(){ 
// 淡入淡出效果 
	    $(this).fadeTo('slow', 1); 
	});
	var topLoading = (document.documentElement.clientHeight/2)-50;
	$('<div class="loadingShow"><img src="/js/images/5-121204193R0-50.gif"></img></div>').appendTo(document.body).css({ 
		position: 'fixed',
		top: topLoading+'px',
		left: '0px',
		'z-index': 1001, 
		width: $(document).width(), 
		height: '100px',
		'text-align': 'center'
	}).fadeIn('slow', function(){ 
// 淡入淡出效果 
	    $(this).fadeTo('slow', 0.5); 
	});
	$(".loadingShow").show();
}
function unmusk() {
	$(".jquery_addmask").remove();
	$(".loadingShow").remove();
}
function resizeLogin() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-407)/2;
	$("#loginFrameCoat").css({
		top: centerTop+'px'
	});
}
function resizeWarning() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-100)/2;
	$("#loginFrameCoat").css({
		top: centerTop+'px'
	});
}

function removeSelf(transfer) {
	$(".jquery_addmask").fadeOut();
	$("#loginFrameCoat").fadeOut();
	setTimeout(function() {
		$(".jquery_addmask").remove();
		$("#loginFrameCoat").remove();
		if (transfer === 1) {
		    popupLogInPage();
		}
		else if (transfer === 2) {
			popupSignInPage();
		}
		else if (transfer === 3) {
			
			popupchangePassWordPage();
		}
	}, 500); 
}
function popupLogInPage() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-407)/2;
		$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
				position: 'absolute',
				top: '0px',
				left: '0px',
				'z-index': 1000, 
				width: $(document).width(), 
				height: $(document).height(), 
				'background-color': 'rgba(0,0,0,.6)', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 1); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/component/logIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001',
		    'border': 'none'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '380px',
			height: '463px',
			'z-index': 1001,
			'box-sizing': 'border-box',
			'border': 'none'
		});
		window.onresize = resizeLogin;
}

function popupSignInPage() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-407)/2;
		$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
				position: 'absolute',
				top: '0px',
				left: '0px',
				'z-index': 1000, 
				width: $(document).width(), 
				height: $(document).height(), 
				'background-color': 'rgba(0,0,0,.6)', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 1); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/component/signIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001',
			'border': 'none'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '380px',
			height: '463px',
			'z-index': 1001,
			'box-sizing': 'border-box',
			'border': 'none'
		});
		window.onresize = resizeLogin;
}

function checkUserLogin(flag) {
	$.ajax({
		url: '/login/validateLogin',
		type: 'GET',
		contentType: "application/json; charset=UTF-8",
		async: false,
		success:function(data){
            var resultCode =data;

			if (resultCode === "") {
			}
			else {
				flag.code = 1;
			}
		},
		error: function(error) {
		}
	});
}

function popupRequestPage() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-407)/2;
		$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
				position: 'absolute',
				top: '0px',
				left: '0px',
				'z-index': 1000, 
				width: $(document).width(), 
				height: $(document).height(),
				'background-color': 'rgba(0,0,0,.6)', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 1); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/component/visitRequest.html" id="requestFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001',
			'border': 'none'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#requestFrame").css({
			width: '380px',
			height: '433px',
			'z-index': 1001,
			'box-sizing': 'border-box',
			'border': 'none'
		});
		window.onresize = resizeLogin;
}

function popupchangePassWordPage() {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-407)/2;
		$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
				position: 'absolute',
				top: '0px',
				left: '0px',
				'z-index': 1000, 
				width: $(document).width(), 
				height: $(document).height(), 
				'background-color': 'rgba(0,0,0,.6)', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 1); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/component/changePassword.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001',
			'border': 'none'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '388px',
			height: '465px',
			'z-index': 1001,
			'box-sizing': 'border-box',
			'border': 'none'
		});
		window.onresize = resizeLogin;
}

function floatAlertBar(content, type) {
	var backgroundColor;
	if (type === 'error') {
		backgroundColor = '#ff5a5f';
	}
	else {
		backgroundColor = '#bfeeb0';
	}
	$('<div class="alertBar"><i class="fa fa-check-circle"></i></span>&nbsp;'+ content +'&nbsp;<a href="/mainPage.html">回到首页</a>&nbsp;|&nbsp;<a href="/designProposal.html">开始定制</a><div class="floatRightClose"><i class="fa fa-times"></i></div></div>').appendTo(document.body).css({ 
		position: 'fixed',
		top: '0px',
		'z-index': 1000, 
		width: $(document).width(),
		padding: '15px',
		'background-color': backgroundColor,
	    'font-size': '14px',
	    'line-height': '1.43',
	    'color': '#565a5c',
	    'border-radius': '2px',
	    '-moz-box-sizing': 'border-box',
        '-webkit-box-sizing': 'border-box',
        'box-sizing': 'border-box',
        'text-align': 'center',
        'display': 'none',
        'margin-top': '-4px'
	});
	$(".alertBar").find("a").css({
		color: '#007a87',
		'font-weight': 'bold'
	});
	$(".alertBar").find(".fa-check-circle").css({
	    'position': 'relative',
	    'top': '5px',
	    'font-size': '30px',
	    'color': '#8bd275'
	});
	$(".alertBar").find(".floatRightClose").css({
	    'float': 'right',
        'margin-top': '-8px',
        'cursor': 'pointer'
	});
	$(".alertBar").click(function() {
		$(".alertBar").fadeOut();
		setTimeout(function() {
			$(".alertBar").remove();
		}, 500);
	});
	$(".alertBar").find(".fa-times").css({
	    'position': 'relative',
	    'top': '5px',
	    'font-size': '40px',
	    'color': '#8bd275'
	});
	$(".alertBar").fadeIn();
	setTimeout(function() {
		$(".alertBar").fadeOut();
		setTimeout(function() {
			$(".alertBar").remove();
		}, 500);
	},5000);
}


function clearWarningmusk () {
	$(".jquery_addmask").remove();
	$(".loginFrameCoat").remove();
	$("#warningMsg").remove();
	$(".loadingShow").remove();
}

function popupWarningMessagePage(content, callback, headerContent,
		btn1, btn2, callback2) {
	var screenHeight = document.documentElement.clientHeight,
	    centerTop = (screenHeight-100)/2,
	    btnTxt1 = btn1 === undefined?'确定':btn1,
	    btnTxt2 = btn2 === undefined?'取消':btn2;
		$('<div class="jquery_addmask"> </div>').appendTo(document.body).css({ 
				position: 'absolute',
				top: '0px',
				left: '0px',
				'z-index': 1000, 
				width: $(document).width(), 
				height: $(document).height(),
				'background-color': 'rgba(0,0,0,.6)'
			}).show();
		$('<div id="loginFrameCoat" hidden><div id="warningMsg"><span id="closeIcon"><i class="fa fa-times" aria-hidden="true"></i></span><p class="warningHeader">'+headerContent+'</p><p>'+content+'<span style="font-size: 6px;" id="processingBtn" hidden><i class="fa fa-spinner fa-pulse fa-3x fa-fw"></i></span></p><button class="linkButton confirm">'+btnTxt1+'</button><button class="linkButton cancel">'+btnTxt2+'</button></div></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001',
			'border': 'none',
			'overflow': 'hidden'
		}).show();
		$("#warningMsg").css({
			'border': '1px solid #aeaeae',
	        'padding': '20px 20px 20px 30px',
	        'background': '#fff',
	        'margin': '0px auto',
	        'width' : '370px',
	        'text-align' : 'left'
		});
		$("#warningMsg").find(".warningHeader").css({
		    'font-size': '15px',
	        'color': '#3c3c3c',
	        'font-weight': '700',
	        'margin-bottom': '15px'
		});
		$("#warningMsg").find(".linkButton").css({
		    'border': '1px solid #52a0e5',
		    'height': '28px',
		    'line-height': '28px',
		    'text-align': 'center',
		    '-webkit-border-radius': '1px',
		    '-moz-border-radius': '1px',
		    '-ms-border-radius': '1px',
		    'border-radius': '1px',
		    'background': '#52a0e5',
		    'display': 'inline-block',
		    'margin-right': '10px',
		    'font-weight': '700',
		    'font-size': '12px',
		    'cursor': 'pointer'
		});
		$("#warningMsg").find(".confirm").css({
			'border': '1px solid #52a0e5',
			'background': '#52a0e5',
			'color': '#fff'
		});
		$("#warningMsg").find(".cancel").css({
			'border': '1px solid #d9d9d9',
			'background': '#fff',
			'color': '#3c3c3c'
		});
		$("#closeIcon").css({
			'float': 'right',
	        'color': '#ed4b83',
	        'font-size': '20px',
	        'margin-top': '-5px',
	        'cursor': 'pointer'
		});
		$("#closeIcon").hover(function() {
			$(this).css({
				'background': '#ccc'
			});
		}, function() {
			$(this).css({
				'background': 'transparent'
			});
		});
		$("#closeIcon").click(function() {
			clearWarningmusk();
		});
		$("#warningMsg").find(".cancel").click(function() {

			if (callback2) {

				if (!$(this).hasClass("disabled")) {
					$(".linkButton").addClass("disabled");
					$("#processingBtn").show();
					$(this).css({
						'opacity':'0.6'
					});
					callback2();
				}
			}
			else {
				clearWarningmusk();
			}
		});
		$("#warningMsg").find(".confirm").click(function() {
			if (!$(this).hasClass("disabled")) {
				$(".linkButton").addClass("disabled");
				$("#processingBtn").show();
				$(this).css({
					'opacity':'0.6'
				});
				callback();
			}
		});
		var box_left = ($(window).width()- $("#loginFrameCoat").width()) / 2;
		for(var i=1; 4>=i; i++){
			$("#loginFrameCoat").animate({left:box_left-(10-2*i)},10);
			$("#loginFrameCoat").animate({left:box_left+2*(10-2*i)},10);
		}
		window.onresize = resizeWarning;
}

function verifySession(){
	
	$.ajax({
    	url: '/user/verifySession',
		type: 'GET',
		contentType: "application/json; charset=UTF-8",
		dataType: 'json',
		data: [],
		success:function(data){
			if (data === 0) {
				alert("用户已超时，请重新登录");
				window.location.href = "/needLogIn.html?returnUrl="+window.location.href;
			}
    	},
	    error: function(error) {
		    alert(error);
	    }
    });
	
}

function sendVistReuqest(cemeteryId, reservDte, phone) {
	var reservation = {};

	reservation.phoneNumber = phone;
	reservation.cemeterId = parseInt(cemeteryId);
	reservation.reservDte = reservDte;
	$.ajax({
		url: '/reservation/add',
		type: 'POST',
		contentType: "application/json; charset=UTF-8",
		dataType: 'json',
		data: JSON.stringify(reservation),
		success:function(data){
			if (data === 1) {
				popupLogInPage();
			}
			else if (data === 2) {
				floatAlertBar("您已经预约过了", 'error');
			}
			else if(data ===4){
				floatAlertBar("您选择的预约时间早于今天，请重新选择", 'error');
			}
			else {
				floatAlertBar("预约成功");
			}
		},
		error: function(error) {
			alert(error);
		}
	});
}