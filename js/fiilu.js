//Scripts By N. V.


$(document).ready(function() {
		$("h2").click(function(){
			var noSpaces = $(this).html().replace(/&nbsp;/gi,'');
			 $(".day."+$.trim(noSpaces)).toggle();
		});
	getFood();
		$("h2").click(function(){
			var noSpaces = $(this).html().replace(/&nbsp;/gi,'');
			 $(".day."+$.trim(noSpaces)).toggle();
		});
});
	$("h2").click(function(){
			var noSpaces = $(this).html().replace(/&nbsp;/gi,'');
			 $(".day."+$.trim(noSpaces)).toggle();
		});
$( window ).load(function() {
  	orderList();
	
});
function getFood() {
	jQuery(function($) {
		  $(".food-fiilu").rss("https://www.fazer.fi/api/location/menurss/current?pageId=29801&language=fi",
		  {
			entryTemplate:'<div class="days"><h1>{title}</h1><br/><div class="food">{body}</div></div>'
		  })
	});

}
function orderList() {
	$("br").remove();
	$(".food-fiilu").prepend($(".days table"));
	$(".food-fiilu table").addClass("table-bordered");
	$(".food-fiilu table:nth-child(2)").remove();
	$(".days table").remove();
	
	$('.food-fiilu div h2').each(function(){
	$(this).parent(".food").parent(".days").children("h1").after('<button type="button" class="btn btn-primary">'+$(this).html()+'</button>');
    var $set = $(this).nextUntil("h2").andSelf();
$set.wrapAll('<div class="day ' + $(this).html() +'" />');
});
	
	
	
}
