//Scripts By N. V.

$(document).ready(function() {
	
	getFood();
});

function getFood() {
	jQuery(function($) {
		  $(".food-fiilu").rss("https://www.fazer.fi/api/location/menurss/current?pageId=29801&language=fi",
		  {
			entryTemplate:'<div class="days"><h1>{title}</h1><br/><div class="food">{body}</div></div>'
		  })
	});
	orderList();
}

function orderList() {
	$("br").remove();
	$(".food-fiilu").prepend($(".days table"));
	$(".days table").remove();
	
	$("h2").insertAfter("<div class='foodList'>");
	$('.food-fiilu div h2').each(function(){ 
    var $set = $(this).nextUntil("h2").andSelf();
    $set.wrapAll('<div class="day' + $(this).html() +'" />');
});
	
	
	
}
