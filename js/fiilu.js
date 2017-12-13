//Scripts By N. V.

var current = "";
$(document).ready(function() {
	if($(".food-fiilu").length) {
	getFood();
	}
	if($(".bitti-today").length) {
	getSodexo("bitti");
	}
	if($(".rajis-today").length) {
	getSodexo("rajis");
	}
	
	
});
$( window ).load(function() {
  	orderList();
		$('.menu a#toggle').click(function() {
	  $('.menu ul').slideToggle(200, function() {
		// Animation complete.
	  });
	});
	/*
	$( ".sodexo-today h3" ).click(function() {
		var c = $(this).attr("data-num");
		$(".sodexo-today .otherdata"+c).slideToggle();
	});
	*/
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
	

}
function orderList() {
	var buttons = false;
	$("br").remove();
	//$(".food-fiilu").prepend($(".days table"));
	//$(".food-fiilu table").addClass("table-bordered");
	//$(".food-fiilu table:nth-child(2)").remove();
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
function getSodexo(restaurant) {
console.log("Loading food!");

var urli = "https://walkonen.fi/sodexo.php?r="+restaurant;


	$.ajax({
			url : urli,
			dataType : 'json',
			success : function(result) {
				var elem = result.courses.length - 1 ;
				if(elem == 0) {
					if(restaurant == "rajis") {
						$(".rajis-today").append("<h2>We are not open today</h2>");
					}
					if(restaurant == "bitti") {
					$(".bitti-today").append("<h2>We are not open today</h2>");
					}
				}
				else {
				for(var num = 0;num < elem; num++) {
				var data = "<div class='food'><h3 class='title' data-num='"+num+"'>"+result.courses[num].title_en+"";
				if(result.courses[num].properties) {
				data += "("+result.courses[num].properties+")";
				}
				if(result.courses[num].price) {
				data += " <span class='price'>"+ result.courses[num].price +" €</span>";
				}
				data += "</h3>";
				data+="<div class='otherdata"+num+" otherdata'>";
				if(result.courses[num].category) {
				data += "<p class='category'>Category: "+ result.courses[num].category +"</p>";
				}
				if(result.courses[num].desc_en) {
				data +=	"<p class='desc'>"+ result.courses[num].desc_en + "</p>";
				}
				data+= "</div></div>";
				if(restaurant == "rajis") {
				$(".rajis-today").append(data);
				}
				if(restaurant == "bitti") {
					$(".bitti-today").append(data);	
				}
			}
			}}
	});
}



