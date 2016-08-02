function showServiceOnLine() {
	var screenHeight = document.documentElement.clientHeight, centerTop = (screenHeight - 407);
	var outputHtml = "<div class=\"floating_ck\"><dl><dt></dt><dd class=\"consult\">";
	outputHtml += "<span>在线咨询</span><div class=\"floating_left\"><li><a id=\"qq1\">念念小管家</a></li><li><a id=\"qq2\">念念小管家2</a></li></div>";
	outputHtml += "</dd><dd class=\"qrcord\"><span>扫一扫</span><div class=\"floating_left floating_ewm\"><img src=\"/js/images/erCode.jpg\"></img></div>";
	outputHtml += "</dd><dd class=\"quote\">	<span>电话咨询</span><div class=\"floating_left\"><p class=\"qrcord_p01\">全国免费服务热线</p>";
	outputHtml += "<p class=\"qrcord_p02\"><b>400-166-0030<b></p></div></dd></dl></div><iframe id=\"qqIframe\" height=0 width=0 src=''></iframe>";

	$(outputHtml).appendTo(parent.document.body).css({
		position : 'fixed',
		'right' : '5px',
		'top' : '30%',
		'z-index' : 1000,
		'border' : 'none'
	}).fadeIn('slow', function() {
		$(this).fadeTo('slow', 1);
	});

	$(".floating_ck dl dd", parent.document).css({
		'position' : 'relative',
		'width' : '80px',
		'height' : '80px',
		'background-color' : '#646577',
		'border-bottom' : 'solid 1px #555666',
		'text-align' : 'center',
		'background-repeat' : 'no-repeat',
		'background-position' : 'center 20%',
		'cursor' : 'pointer'
	});

	$(".floating_ck dl dd", parent.document).hover(function() {
		$(this).css({
			'background-color' : '#ec4a82',
			'border-bottom' : 'solid 1px #ec4a82'
		});
		$(".floating_left", this).css({
			'display' : 'block'
		});

	}, function() {
		$(this).css({
			'background-color' : '#646577',
			'border-bottom' : 'solid 1px #555666',
		});
		$(".floating_left", this).css({
			'display' : 'none'
		});

	});

	$(".consult", parent.document).css({
		'background-image' : 'url(/js/images/zxicon.png)'
	});

	$(".quote", parent.document).css({
		'background-image' : 'url(/js/images/kficon.png)'
	});

	$(".qrcord", parent.document).css({
		'background-image' : 'url(/js/images/erweima.png)'
	});

	$(".floating_ck dd span", parent.document).css({
		'color' : '#fff',
		'display' : 'block',
		'padding-top' : '54px'
	});

	$(".floating_left", parent.document).css({
		'position' : 'absolute',
		'left' : '-160px',
		'top' : '0px',
		'width' : '160px',
		'height' : '80px',
		'background-color' : '#646577',
		'border-bottom' : 'solid 1px #a40324',
		'display' : 'none'
	});

	$(".floating_left li", parent.document).css({
		'list-style-type' : 'none',
		'margin-top' : '5px'
	});

	$(".floating_left li img", parent.document).css({
		'width' : '20px',
		'height' : '20px'
	});

	$(".floating_left a", parent.document).css({
		'color' : '#EEE',
		'line-height' : '30px'
	});

	$(".floating_ewm", parent.document).css({
		'height' : '160px',
		'top' : '-40px'
	});

	$(".floating_ewm img", parent.document).css({
		'display' : 'block',
		'width' : '145px',
		'height' : '145px',
		'margin' : 'auto',
		'margin-top' : '7px'
	});
	$(".floating_left .qrcord_p01", parent.document).css({
		'color' : '#EEE',
		'font-size' : '18px',
		'line-height' : '20px',
		'margin-top' : '15px'
	});
	$(".floating_left .qrcord_p02", parent.document).css({
		'color' : '#EEE',
		'font-size' : '18px',
		'margin-top' : '5px'
	});
	$("#qq1", parent.document)
			.click(
					function() {
						$("#qqIframe", parent.document)
								.attr("src",
										"tencent://message/?Menu=yes&uin=2432340482&site=qq&menu=yes");
					});
	$("#qq2", parent.document)
			.click(
					function() {
						$("#qqIframe", parent.document)
								.attr("src",
										"tencent://message/?Menu=yes&uin=2750143174&site=qq&menu=yes");
					});
}

showServiceOnLine();
