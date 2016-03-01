app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});
app.controller('products', function($scope, $http) {
	$http.get("/wish/list"+productFilter).success(function(response) {
	    var j = 1,
		    counter = 1,
		    numberInRow = 3,
			objectsInRow = [],
			layouts = [],
			layouts1 = [],
			layouts2 = [],
			layouts3 = [],
			evenNumber = ((response.length/3).toFixed(0)/3).toFixed(0);
		for (var i=0;i<response.length;i++) {
		    var singleProduct = response[i];

			if (j <= numberInRow) {
			    objectsInRow.push(singleProduct);
			}

			if (j === numberInRow || i === response.length-1) {
			    var layout = {},
				    active = '',
					itemLayout = objectsInRow;

				if (itemLayout[numberInRow-1]) {
				    itemLayout[numberInRow-1].positionClass = 'lastProduct';
				}
				layout = {
				    'index': counter,
					'active': active,
					'itemLayout': itemLayout
				};
				if (counter <= evenNumber) {
					if (layouts1.length === 0) {
						layout.active = 'active';
					}
					layouts1.push(layout);
				}
				else if (counter > evenNumber && counter <= evenNumber*2) {
					if (layouts2.length === 0) {
						layout.active = 'active';
					}
					layouts2.push(layout);
				}
				else {
					if (layouts3.length === 0) {
						layout.active = 'active';
					}
					layouts3.push(layout);
				}
			    j = 0;
				counter++;
				objectsInRow = [];
			}
			j++;
		}
		layouts.push(layouts1);
		layouts.push(layouts2);
		layouts.push(layouts3);
		$scope.subLayouts = layouts;
	});
	$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
		$(".productCarousel").carousel({
			wrap: false
		});
		$(".productCarousel").carousel('pause');
		$(".productCarousel").each(function() {
			var pages = $(this).find(".item");

			if (pages.length === 1) {
				$(this).find(".right").hide();
			}
		});
		$(".productCarousel").on('slid.bs.carousel', function() {
			var pages = $(this).find(".item");
			
			if ($(pages[pages.length-1]).hasClass("active")) {
			    $(this).find(".right").fadeOut();
			}
			else {
				$(this).find(".right").fadeIn();
			}
			if ($(pages[0]).hasClass("active")){
				$(this).find(".left").fadeOut();
			}
			else {
				$(this).find(".left").fadeIn();
			}
		});
    });
});
window.onload=function(){
	$("body").on("click",".productItem",function() {
        $(this).removeClass("productWishShadow");
        window.parent.location.href="/productDetail3.html?id="+$(this)[0].id;
	});
	$("body").on("mousedown",".productItem",function() {
        $(this).addClass("productWishShadow");
	});
}