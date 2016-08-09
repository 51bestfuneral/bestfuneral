/**
 * 
 */

function GetRandomNum(Min,Max)
{   
    var Range = Max - Min;   
    var Rand = Math.random();   
    return(Min + Math.round(Rand * Range));   
}

function muskPage() {
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
				opacity: 0 
			}).fadeIn('slow', function(){ 
	// 淡入淡出效果 
				$(this).fadeTo('slow', 0.5); 
		});
}

function muskPageForProduct(id, type) {
	    var parentWidth = $(".coverDiv").width(),
		    frameWidth = $(".detailFrame").width();
				
		muskPage();
		$(".detailFrame").attr("src","component/productDetail"+ type +".html?id="+id);
		$(".closeCoverDiv").css({
			right: (parentWidth-frameWidth)/2 - 30,
			left: 'auto',
			top: 0
		});
		$(".coverDiv").fadeIn(1000);
};

$(".statusBarPoint").click(function() {
	var id = parseInt($(this)[0].id) - 1,
	    left = id* 175 - 15;
	$("#cursorPoint").animate({'margin-left': left+'px'});
	if (id === 0) {
    	$("#otherPrice")[0].innerText = '20000';
    	$("#nianPrice")[0].innerText = '15000';
    	$("#savePrice")[0].innerText = '5000';
    }
    if (id === 1) {
    	$("#otherPrice")[0].innerText = '24000';
    	$("#nianPrice")[0].innerText = '17000';
    	$("#savePrice")[0].innerText = '7000';
    }
    if (id === 2) {
    	$("#otherPrice")[0].innerText = '30000';
    	$("#nianPrice")[0].innerText = '20000';
    	$("#savePrice")[0].innerText = '10000';
    }
    if (id === 3) {
    	$("#otherPrice")[0].innerText = '42000';
    	$("#nianPrice")[0].innerText = '25000';
    	$("#savePrice")[0].innerText = '17000';
    }
});

$(".startDesignButton").click(function() {
    window.location.href='designProposal.html';
});

$("#startButton").click(function() {
    window.location.href='designProposal.html';
});

$(".startPurchseBtn").click(function() {
    window.location.href='/productAndService.html';
});

$(".cemeteryBtn").click(function() {
    window.location.href='/cemeteryList.html';
});

$("#message").click(function() {
    window.location.href='designProposal.html';
});

$("#detailCalculator").click(function() {
	window.location.href='/designProposal.html';
});

$("body").on("click",".singleMember",function() {
	var id = $(this)[0].id;
    $(".membersContent").css({'opacity': '0'});
	$("#memberDesc"+id).css({'opacity': '1'});
});

var allfeatures = ['给他一个合适的葬礼',
                   '记录下他生前的每一个瞬间',
                   '让他的意愿在葬礼上实现'],
    features = allfeatures[0].replace(/(.)(?=[^$])/g,"$1,").split(","),
    featureIndex = 0;
    index = 0,
    start = true,
    remove = false;
var changeText = function() {
	var target = $(".mainContent1").find("#dynamicText");
	if (start) {
		remove = false;
		if (index < features.length) {
		    target[0].innerText = target[0].innerText + features[index];
		    index = index + 1;
		}
		else {
			start = false;
			index = 0;
			if (featureIndex === allfeatures.length - 1) {
				featureIndex = 0;
			}
			else {
				featureIndex = featureIndex + 1;
			}
			features = allfeatures[featureIndex].replace(/(.)(?=[^$])/g,"$1,").split(",");
			setTimeout(function() {
				remove = true;
			}, 3000);
		}
	}
};

var removeText = function() {
	var target = $(".mainContent1").find("#dynamicText");
	if (remove) {
	    target[0].innerText = target[0].innerText.substring(0, target[0].innerText.length - 1);
	    if (target[0].innerText === '') {
	    	if (!start) {
		    	if (target.hasClass('dynamicStyle1')) {
		    		target.removeClass('dynamicStyle1');
		    		target.addClass('dynamicStyle2');
		    		$(".transitionImg").css({"opacity": 0})
		    		$($(".transitionImg")[1]).css({"opacity": 1});
		    	}
		    	else if (target.hasClass('dynamicStyle2')) {
		    		target.removeClass('dynamicStyle2');
		    		target.addClass('dynamicStyle3');
		    		$(".transitionImg").css({"opacity": 0})
		    		$($(".transitionImg")[2]).css({"opacity": 1});
		    	}
		    	else {
		    		target.removeClass('dynamicStyle3');
		    		target.addClass('dynamicStyle1');
		    		$(".transitionImg").css({"opacity": 0})
		    		$($(".transitionImg")[0]).css({"opacity": 1});
		    	}
	    	}
	    	start = true;
	    }
	}
};

var fadeText = function() {
	var target = $(".mainContent1").find("#fadeText");
	if (target.is(":hidden")) {
		target.fadeIn();
	}
	else {
		target.fadeOut();
	}
};