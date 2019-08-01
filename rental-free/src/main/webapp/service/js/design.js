function layer_open(el){
	
	var temp = $('#' + el);
	var bg = $('.bg');	//dimmed 레이어를 감지하기 위한 boolean 변수
	//var bg = temp.prev().hasClass('bg');	//dimmed 레이어를 감지하기 위한 boolean 변수

	if(bg){
		$('.layer').fadeIn();
	}else{
		temp.fadeIn();
	}
	
	temp.show();

	// 화면의 중앙에 레이어를 띄운다.
	if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
	else temp.css('top', '0px');
	if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
	else temp.css('left', '0px');

	temp.find('a.pop-btn').click(function(e){
		if(bg){
			$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
			temp.hide();
		}else{
			temp.fadeOut();
		}
		e.preventDefault();
	});

	$('.layer .bg').click(function(e){	//배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
		$('.layer').fadeOut(); 
		temp.hide();
		e.preventDefault();
	});

}					

$(document).ready( function() {
    $('#myCarousel').carousel({
		interval:   4000
	});
	
	var clickEvent = false;
	$('#myCarousel').on('click', '.nav a', function() {
			clickEvent = true;
			$('.nav li').removeClass('active');
			$(this).parent().addClass('active');		
	}).on('slid.bs.carousel', function(e) {
		if(!clickEvent) {
			var count = $('.nav').children().length -1;
			var current = $('.nav li.active');
			current.removeClass('active').next().addClass('active');
			var id = parseInt(current.data('slide-to'));
			if(count == id) {
				$('.nav li').first().addClass('active');	
			}
		}
		clickEvent = false;
	});
});
(function( $ ){
$( document ).ready( function() {
	$( '.input-range').each(function(){
		
		var name = $(this).attr('name');
	
		var value = $(this).attr('data-slider-value');
		var separator = value.indexOf(',');
		if( separator !== -1 ){
			value = value.split(',');
			value.forEach(function(item, i, arr) {
				arr[ i ] = parseFloat( item );
			});
		} else {
			value = parseFloat( value );
		}
		$( this ).slider({
			formatter: function(value) {
			
				return '$' + value;
			},
			min: parseFloat( $( this ).attr('data-slider-min') ),
			max: parseFloat( $( this ).attr('data-slider-max') ), 
			range: $( this ).attr('data-slider-range'),
			value: value,
			tooltip_split: $( this ).attr('data-slider-tooltip_split'),
			tooltip: $( this ).attr('data-slider-tooltip')
		}).on("slide", function(slideEvt) {
			if (name == 'srch_deposit'){ //보증금
				$("#range_srch_deposit").html( Math.round(parseFloat(slideEvt.value[0]) / 1000 * 10)/10 + "~" + Math.round(parseFloat(slideEvt.value[1]) / 10000 * 10)/10 +"억원");
			} else if (name =='srch_month_price'){ // 월세
				$("#range_srch_month_price").html( slideEvt.value[0] + " ~ " + slideEvt.value[1] +"만원");
			}else if (name =='srch_common_area'){ //면적
				$("#range_srch_common_area").html( slideEvt.value[0] + " ~ " + slideEvt.value[1] +"PY");
			}
		});
	});
	
 } );
} )( jQuery );