//Scripts By N. V.

var current = "";
$(document).ready(function() {
	if($(".food-fiilu").length) {
	getFood();
	}
});
$( window ).load(function() {
  	orderList();
		$('.menu a#toggle').click(function() {
	  $('.menu ul').slideToggle(200, function() {
		// Animation complete.
	  });
	});
	$( "button" ).click(function() {
		if(current !== "."+$(this).html()) {
			
		$(".day").hide(); 
		$("button").removeClass("active");
		}
		if($(this).hasClass("active")) {
		$("button").removeClass("active");
		}
		else {
			$(this).addClass("active");
		}
		$(this).parent(".buttons").parent(".days").children(".food").children(".day."+$(this).html()).slideToggle(300);
		
		current = "."+$(this).html();
		});
		
});

$('.menu a#toggle').click(function() {
  $('.menu ul').slideToggle(200, function() {
    // Animation complete.
  });
});

$( "h1" ).click(function() {

});
$( "button" ).click(function() {$(".day").hide();	$(this).parent(".buttons").parent(".days").children(".food").children(".day."+$(this).html()).slideToggle(300);});

function getFood() {
	jQuery(function($) {
		  $(".food-fiilu").rss("https://www.fazer.fi/api/location/menurss/current?pageId=29801&language=fi",
		  {
			entryTemplate:'<div class="days"><h1>{title}</h1><br/><div class="food">{body}</div></div>'
		  })
	});
	getSodexo();
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
	var HeightDiv = $("div").height();
    var HeightTable = $("table").height();
    if (HeightTable > HeightDiv) {
        var FontSizeTable = parseInt($("table").css("font-size"), 10);
        while (HeightTable > HeightDiv && FontSizeTable > 5) {
            FontSizeTable--;
            $("table").css("font-size", FontSizeTable);
            HeightTable = $("table").height();
        }
    }
	
	if(window.innerWidth < 500) {

	}
	
}
function getSodexo() {
	console.log("Loading food!")
	$.ajax({
			url : "/sodexo.php",
			dataType : 'json',
			success : function(result) {

				$(".sodexo-today").append(JSONobject);

			}
	});
}



