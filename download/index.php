<?php
$place = $_GET["place"];
if ($place == "fiilu") {
	$restaurant = "Ravintola Fiilu";
}
else if ($place == "sodexo") {
	$restaurant = "JAMK Ravintola Bittipannu";
}
?>
<head>
	<title>Want some lunch?</title>
	<meta name="description" content="Check out <?php echo $restaurant ?>!">
	<style>
		body {font-family:sans-serif; text-align: center;}
		p {color:blue; font-size: 40;}
		a {padding: 5%; background-color: blue; color: white; text-align:center;display:block; font-size: 40;}
		#below {font-size: 20;}
		h1 {font-size: 62;}
	</style>
	
</head>
<body>
<h1>View menus and reviews in LunchApp!</h1>
<p>You are near <?php echo $restaurant ?>, want to check it out?
	<br>Open with the app, or download the app here!</p>
<a href="intent://place/?place=<?php echo $place?>#Intent;scheme=lutakko;package=com.example.panu.lutakko;end">Lets go!</a>
</body>