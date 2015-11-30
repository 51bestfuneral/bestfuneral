(function($){
    $.fn.ckSlide = function(opts){
		//.extend() 扩展jQuery类，添加ckSlide方法，参数是对象类型{}
        opts = $.extend({}, $.fn.ckSlide.opts, opts);
        this.each(function(){
            var slidewrap = $(this).find('.ck-slide-wrapper');//轮播元素父对�?
            var slide = slidewrap.find('li');//获取<li>对象�?
            var count = slide.length;//计算对象集长�?
            var that = this;//存放父对�?
            var index = 0;//起始位置
            var time = null;
            $(this).data('opts', opts);//给轮播对象添加参�? 数据
            // next
            $(this).find('.ck-next').on('click', function(){
                if(opts['isAnimate'] == true){
                    return;
                }
                
                var old = index;
                if(index >= count - 1){
                    index = 0;
                }else{
                    index++;
                }
                change.call(that, index, old);//调用图片切换方法�?.call() 每个JS函数都包含的一个非继承而来的方法，主要用来指定函数的作用域 that ，通常不严谨写法是change()，有可能会函数冲突�?
            });
            // prev
            $(this).find('.ck-prev').on('click', function(){
                if(opts['isAnimate'] == true){
                    return;
                }
                
                var old = index;
                if(index <= 0){
                    index = count - 1;
                }else{                                      
                    index--;
                }
                change.call(that, index, old);
            });
			//点击切换相应序号的图�?
            $(this).find('.ck-slidebox li').each(function(cindex){
                $(this).on('click.slidebox', function(){
                    change.call(that, cindex, index);
                    index = cindex;
                });
            });
			//自己添加——鼠标移入小圆点切换轮播图片
			$(this).find('.ck-slidebox li').each(function(cindex){
                $(this).on('mouseover.slidebox', function(){
                    change.call(that, cindex, index);
                    index = cindex;
                });
            });
            
            // 鼠标悬停停止自动播放，显示左右切换按�?
            $(this).on('mouseover', function(){
                if(opts.autoPlay){
                    clearInterval(time);
                }
                $(this).find('.ctrl-slide').css({opacity:0.6});
            });
            //  鼠标离开轮播界面，开始自动播放，同时隐藏按钮
            $(this).on('mouseleave', function(){
                if(opts.autoPlay){
                    startAtuoPlay(opts.interval);
                }
                $(this).find('.ctrl-slide').css({opacity:0.1});
            });
            startAtuoPlay(opts.interval);
            // 自动滚动播放
            function startAtuoPlay(inum){
                if(opts.autoPlay){
                    time  = setInterval(function(){
                        var old = index;
                        if(index >= count - 1){
                            index = 0;
                        }else{
                            index++;
                        }
                        change.call(that, index, old);
                    }, inum);//2�?
                }
            }
            // 修正box  标记居中
            var box = $(this).find('.ck-slidebox');
            box.css({
                'margin-left':-(box.width() / 2)
            })
            // dir  移动方向参数
            switch(opts.dir){
                case "x":
                    opts['width'] = $(this).width();
                    slidewrap.css({
                        'width':count * opts['width']
                    });
                    slide.css({
                        'float':'left',
                        'position':'relative',
						'margin-left':'0px'
                    });
					//.wrap()包裹页面已经定义�?.ck-slide-wrapper以及子元�?
                    slidewrap.wrap('<div class="ck-slide-dir"></div>');
                    slide.show();
                    break;
				case "y":  //添加垂直移动参数
                    opts['height'] = $(this).height();
                    slidewrap.css({
                        'height':count * opts['height']
                    });
                    slide.css({
                        'float':'left',
                        'position':'relative',
						'margin-top':'0px'
                    });
                    slidewrap.wrap('<div class="ck-slide-dir"></div>');
                    slide.show();
                break;
            }
        });
    };
    function change(show, hide){
		//获取之前设置在ckSlide对象上的参数 数据
        var opts = $(this).data('opts');
		//水平移动
        if(opts.dir == 'x'){
            var x = show * opts['width'];
			//animate() 与css()执行结果相同，但是过程不同，前者有渐变动画效果
            $(this).find('.ck-slide-wrapper').stop().animate({'margin-left':-x}, function(){opts['isAnimate'] = false;});
            opts['isAnimate'] = true;//图片在移动过程中设置按钮点击不可用，确保每一次轮播视觉上执行完成�?
        }else if(opts.dir == 'y'){//垂直移动——自己添�?
            var y = show * opts['height'];
            $(this).find('.ck-slide-wrapper').stop().animate({'margin-top':-y}, function(){opts['isAnimate'] = false;});
            opts['isAnimate'] = true;
        }
		else{
			//默认的淡隐淡出效�?
            $(this).find('.ck-slide-wrapper li').eq(hide).stop().animate({opacity:0});
            $(this).find('.ck-slide-wrapper li').eq(show).show().css({opacity:0}).stop().animate({opacity:1});
        }
       //切换对应标记的颜�?
        $(this).find('.ck-slidebox li').removeClass('current');
        $(this).find('.ck-slidebox li').eq(show).addClass('current');
    }
    $.fn.ckSlide.opts = {
		autoPlay: false,//默认不自动播�?
        dir: null,//默认淡隐淡出效果
        isAnimate: false,//默认按钮可用
		interval:2000//默认自动2秒切�? 
		 };
})(jQuery);