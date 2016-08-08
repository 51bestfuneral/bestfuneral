/**
 * 
 */

var app = angular.module('myApp', []);

function buildObject(level) {
	var levelOwnList = [];
	for (i = 0; i < level.length; i++) {
		var levelOwn;

		if (level[i] === "0") {
			levelOwn = {
				hidden : "hidden"
			}
		} else {
			levelOwn = {
				hidden : ""
			}
		}
		levelOwnList.push(levelOwn);
	}
	return levelOwnList;
};

app.controller('wishTypeLevels', function($scope, $http) {

	$http.get("/displayWishType/listWishCategory").success(function(response) {
		$scope.wishCates = response;
	});

	$http.get("/displayWishType/listWishCategorySub").success(
			function(response) {
				$scope.cateSubs = response;
			});

	$http.get("/wishType/list").success(function(response) {
		$scope.wishTypes = response;
	});
});

function generateWishlistByLevel(level) {
	var paras = '&level=' + level;
	$.ajax({
		url : '/wishlist/add?' + paras,
		data : [],
		type : 'post',
		dataType : 'json',
		success : function(data) {
			window.location.href = '/designProcess.html';
		},
		error : function(error) {
		}
	});
}
function getWishlistByLevelAfterChcek(level) {
	$.ajax({
		url : '/wishlist/checkWishlistEmpty',
		data : [],
		type : 'get',
		dataType : 'json',
		success : function(data) {
			var callback = function() {
				generateWishlistByLevel(level);
			}, callback2 = function() {
				window.location.href = '/designProcess.html';
			};
			if (data === 1) {
				popupWarningMessagePage(
						"检测到您已经有一个正在定制的愿望清单，清空原来的重新定制还是继续上次定制？", callback,
						"提示信息", "清空并重新定制", "继续上次定制", callback2);
			} else {
				generateWishlistByLevel(level);
			}
		},
		error : function(error) {
		}
	});
}
function getWishlistByLevel(level) {
	muskCurrentPage();
	$.ajax({
		url : '/login/validateLogin',
		type : 'GET',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			var resultCode = data;

			if (resultCode === "") {
				unmusk();
				popupLogInPage();
			} else {
				getWishlistByLevelAfterChcek(level);
			}
		},
		error : function(error) {
		}
	});
}
function handleProposal() {
	$(".proposalBar").removeClass("barSelect");
	if ($("#taocan6").hasClass("choose")) {
		$("#best").addClass("barSelect");
		return;
	}
	if ($("#taocan5").hasClass("choose") && $("#taocan4").hasClass("choose")) {
		$("#best").addClass("barSelect");
		return;
	}
	if ($("#taocan5").hasClass("choose") && $("#taocan3").hasClass("choose")) {
		$("#best").addClass("barSelect");
		return;
	}
	if ($("#taocan5").hasClass("choose")) {
		$("#better").addClass("barSelect");
		return;
	}
	if ($("#taocan4").hasClass("choose")) {
		$("#better").addClass("barSelect");
		return;
	}
	if ($("#taocan3").hasClass("choose") && $("#taocan2").hasClass("choose")) {
		$("#better").addClass("barSelect");
		return;
	}
	if ($("#taocan3").hasClass("choose")) {
		$("#good").addClass("barSelect");
		return;
	}
	if ($("#taocan2").hasClass("choose") && $("#taocan1").hasClass("choose")) {
		$("#good").addClass("barSelect");
		return;
	}
	if ($(".choose").length === 0) {
		$(".barSelect").removeClass("barSelect");
	} else {
		$("#basic").addClass("barSelect");
		return;
	}
};

$(".designTaocanIconDiv").click(function() {
	if ($(this).hasClass("choose")) {
		$(this).removeClass("choose");
	} else {
		$(this).addClass("choose");
	}
	handleProposal();
});

$("#basicButton").on("click", function() {
	getWishlistByLevel(1);
});
$("#goodButton").on("click", function() {
	getWishlistByLevel(2);
});
$("#betterButton").on("click", function() {

	getWishlistByLevel(3);

});
$("#bestButton").on("click", function() {

	getWishlistByLevel(4);

});