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
		$('<div id="loginFrameCoat" hidden><iframe src="component/logIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
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
		$('<div id="loginFrameCoat" hidden><iframe src="component/signIn.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
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
		$('<div id="loginFrameCoat" hidden><iframe src="component/changePassword.html" id="loginFrame"></iframe></div>').appendTo(document.body).css({ 
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
