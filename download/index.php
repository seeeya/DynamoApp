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
	<title>Lutakko</title>
	<meta name="description" content="Hungry? Check out <?php echo $restaurant ?>!">
</head>
<h1>View menus and reviews in LutakkoApp!</h1>
<p>You are near <?php echo $restaurant ?>, want to check it out?
	<br>Open with the app, or download the app here!</p>
<a href="walkonen.fi/apps/dynamoapp/download/redirect.html?place=<?php echo $place?>">Lets go!</a>
<p id="below">The button will prompt you to open the Lutakko App, or if not installed will redirect you to the Play Store</p>
