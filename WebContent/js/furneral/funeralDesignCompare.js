/**
 * 
 */

 	$(".taocanIconDiv").click(function(){
        window.location.href='designProposal.html';
    });
 	
	$(".taocanIconDiv").hover(function () {
        var $this = $(this);
        $this.addClass("choose");
	});
	
	$(".taocanIconDiv").on("mouseleave", function () {
        var $this = $(this);
        $this.removeClass("choose");
	});
	
	$(".tabIcon").hover(function () {
        var $this = $(this);
        $this.addClass("chooseTab");
		$this.find(".triangle-down").fadeIn(300);
	});
	
	$(".tabIcon").on("mouseleave", function () {
        var $this = $(this);

		if (!$this.hasClass("select")) {
            $this.removeClass("chooseTab");
			$this.find(".triangle-down").fadeOut(300);
		}
	});
	
	$(".tabIcon").click(function() {
	    var $this = $(this);

		if (!$this.hasClass("select")) {
			$(".tabIcon").each(function() {
			    if ($(this).hasClass("select")) {
				    $(this).removeClass("select");
			        $(this).removeClass("chooseTab");
					$(this).find(".triangle-down").fadeOut(300);
				}
            });
		    $this.addClass("select");
			$this.addClass("chooseTab");
	    }
	});
	
	$("#startButton").on("click", function () {
	    window.location.href='designProposal.html';
    });
	
	$("#rightBack").click(function() {
		$("#why3Text2").find("p")[0].innerText = "您可能是40、50后，辛苦操劳了大半生，现在既要帮助孩子照顾孙辈，还要面对父辈和自身的双重养老问题，无论经济上和精神上都面临极大的压力。我们可以帮助您提前进行合理规划，最大程度减轻您的负担。";
		$("#rightFront").fadeIn().css("display","inline");
		$("#middleFront").fadeOut();
		$("#leftFront").fadeOut();
	});
	
	$("#leftBack").click(function() {
		$("#why3Text2").find("p")[0].innerText = "您可能是60、70后，忙于事业和家庭，缺少对传统礼数的认知和经历。一旦身边亲人遭遇不可知的变故，便容易不知所措。我们可以帮助您完善地应对所有的事务，解决您的后顾之忧。";
		$("#leftFront").fadeIn().css("display","inline");
		$("#middleFront").fadeOut();
		$("#rightFront").fadeOut();
	});
	
	$("#middleBack").click(function() {
		$("#why3Text2").find("p")[0].innerText = "您可能是80、90后，正值壮年，社会阅历并不丰富，财富积累可能也有限，但这并不妨碍你们享受生命中最美好的时光。我们可以为您提供一种全新模式的金融理财方案，确保您终身收益。";
		$("#middleFront").fadeIn().css("display","inline");
		$("#rightFront").fadeOut();
		$("#leftFront").fadeOut();
	});
	
	$("#middleFront").fadeIn();