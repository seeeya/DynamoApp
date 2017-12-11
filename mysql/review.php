<?php
//Reason why this looks so terrible is limited time and resources. It's hideous but it works.
$place = $_GET["place"];
if ($place == "fiilu") {
	$restaurant = "Ravintola Fiilu";
}
else if ($place == "bittipannu") {
	$restaurant = "JAMK Ravintola Bittipannu";
}
else if ($place == "rajakatu") {
	$restaurant = "JAMK Ravintola Radis";
}
?>
<head>
	<meta charset="utf-8">
	<title>Lutakko</title>
	<meta name="description" content="Hungry? Check out <?php echo $restaurant ?>!">
	<style>
		body {font-family:sans-serif; text-align: center;}
		p {color:blue; font-size: 40;}
		a {padding: 5%; background-color: blue; color: white; text-align:center;display:block; font-size: 40;}
		#below {font-size: 20;}
		h1 {font-size: 62;}
		span {font-size: 60; color:#e0e0d1; user-select: none; -moz-user-select: none; padding: 5%;}
		#button {width: 100%; background-color: blue; color: white; padding: 3%; font-size: 40;}
		.checked {color: blue;}
	</style>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<h1>How did you like <?php echo $restaurant ?>?</h1>
<p>Let us and everyone else know your experience! Leave a rating!</p>
<div class="rating">
<span id="1" class="fa fa-star fa-3x"></span><span id="2" class="fa fa-star fa-3x"></span><span id="3" class="fa fa-star fa-3x"></span><span id="4" class="fa fa-star fa-3x"></span><span id="5" class="fa fa-star fa-3x"></span>
<form method="post">
<input type="hidden" name="rating" value="" id="rating">
<input type="submit" value="Submit review" name="submitbutton" id="button">
</form>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>$(document).ready(function(){
    $("span").click(function(e){
		$( "span" ).each(function( index, element ) {
			$(element).attr('id', index + 1);
			$(element).attr('class', 'fa fa-star fa-3x');
		});
		$(this).attr('id', 'stop');
		$( "span" ).each(function( index, element ) {
			$( element ).attr('class', 'fa fa-star checked fa-3x');
			if ( $( this ).is( "#stop" ) ) {
				$("#rating").val(index + 1);
				return false;
			}
		});
		
    });
});</script>
<?php
require_once("/home/phakala/mysql_conn.php");
if (isset($_POST["submitbutton"])) {
	$score = $_POST['rating'];
	$query = "INSERT INTO review (score, place) VALUES ('".$score."', '".$restaurant."')";
	if (!$conn) {
		die("Connection failed: " . mysqli_connect_error());
	}
	if (mysqli_query($conn, $query)) {
		header("Location: https://walkonen.fi/apps/dynamoapp/?page=".$place);
		exit();
	} else {
		echo "Error: " . $query . "<br>" . mysqli_error($conn);
	}
	mysqli_close($conn);

}
?>