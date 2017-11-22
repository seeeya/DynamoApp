//Scripts By N. V.

var current = "";
$(document).ready(function() {
	getFood();
});
$( window ).load(function() {
  	orderList();
	
	$( "button" ).click(function() {
		if(current !== "."+$(this).html()) {
			
		$(".day").hide(); 
		}
		$(this).parent(".buttons").parent(".days").children(".food").children(".day."+$(this).html()).slideToggle(300);
		current = "."+$(this).html();
		});
		
});
$( "h1" ).click(function() {
	
}
$( "button" ).click(function() {$(".day").hide();	$(this).parent(".buttons").parent(".days").children(".food").children(".day."+$(this).html()).slideToggle(300);});

function getFood() {
	jQuery(function($) {
		  $(".food-fiilu").rss("https://www.fazer.fi/api/location/menurss/current?pageId=29801&language=fi",
		  {
			entryTemplate:'<div class="days"><h1>{title}</h1><br/><div class="food">{body}</div></div>'
		  })
	});

}
function orderList() {
	var buttons = false;
	$("br").remove();
	$(".food-fiilu").prepend($(".days table"));
	$(".food-fiilu table").addClass("table-bordered");
	$(".food-fiilu table:nth-child(2)").remove();
	$(".days table").remove();
	$("p strong").parent("p").hide();
	$('.food-fiilu div h2').each(function(){
		if($(this).parent(".food").parent(".days").children(".buttons").length < 1 ) {
				$(this).parent(".food").parent(".days").children("h1").after("<div class='buttons'></div>")
		}$(this).parent(".food").parent(".days").children(".buttons").append('<button type="button" class="btn btn-primary">'+$.trim($(this).html())+'</button>');

	
    var $set = $(this).nextUntil("h2").andSelf();
$set.wrapAll('<div class="day ' + $(this).html().replace(/&nbsp;/gi,'') +'" />'); 
});

	$("button").each(function() { $(this).html($(this).html().replace(/&nbsp;/gi,''));});
	
}

