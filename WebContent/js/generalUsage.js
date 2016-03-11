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
		'background-color': '#ccc', 
		opacity: 0.5
	}).fadeIn('slow', function(){ 
// 淡入淡出效果 
	    $(this).fadeTo('slow', 0.5); 
	});
	var topLoading = (document.documentElement.clientHeight/2)-50;
	$('<div class="loadingShow"><img src="/funeral/js/images/5-121204193R0-50.gif"></img></div>').appendTo(document.body).css({ 
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
				'background-color': '#ccc', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 0.5); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/funeral/pages/component/logIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '380px',
			height: '463px',
			'z-index': 1001,
			border: '5px solid #999'
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
				'background-color': '#ccc', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 0.5); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/funeral/pages/component/signIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '380px',
			height: '463px',
			'z-index': 1001,
			border: '5px solid #999'
		});
		window.onresize = resizeLogin;
}

function checkUserLogin(flag) {
	$.ajax({
		url: '/funeral/login/validateLogin',
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
				'background-color': '#ccc', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 0.5); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/funeral/pages/component/visitRequest.html" id="requestFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#requestFrame").css({
			width: '380px',
			height: '433px',
			'z-index': 1001,
			border: '5px solid #999'
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
				'background-color': '#ccc', 
				opacity: 0 
			}).fadeIn('slow', function(){ 
				$(this).fadeTo('slow', 0.5); 
		});
		$('<div id="loginFrameCoat" hidden><iframe src="/funeral/pages/component/changePassword.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
			height: 'auto',
			width: '100%',
			top: centerTop+'px',
			'text-align': 'center',
			position: 'fixed',
			'z-index': '1001'
		}).fadeIn('slow', function(){ 
			$(this).fadeTo('slow'); 
		});
		$("#loginFrame").css({
			width: '388px',
			height: '465px',
			'z-index': 1001,
			border: '5px solid #999'
		});
		window.onresize = resizeLogin;
}

function floatAlertBar() {
	$('<div class="alertBar"><i class="fa fa-check-circle"></i></span>&nbsp;个人资料更新成功&nbsp;<a href="/funeral/pages/mainPage.html">回到首页</a>&nbsp;|&nbsp;<a href="/funeral/pages/designProposal.html">开始定制</a><div class="floatRightClose"><i class="fa fa-times"></i></div></div>').appendTo(document.body).css({ 
		position: 'fixed',
		top: '0px',
		'z-index': 1000, 
		width: $(document).width(),
		'background-color': '#ccc', 
		padding: '15px',
		'background-color': '#bfeeb0',
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
