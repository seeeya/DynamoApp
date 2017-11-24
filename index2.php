<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" >
<title>LutakkoApp TBD</title>
 
 <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-108664137-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-108664137-1');
 
</script>
<link rel="stylesheet" type="text/css" href="./css/stylesheets/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="./js/jquery.rss.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.4/moment.min.js"></script>
<script src="./js/fiilu.js"></script>
<script src="./js/tether.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>


<script>
 
</script>

</head>
<body id="index" class="home">

<?php

/*
$dir    = './';
$files1 = scandir($dir); 

echo "Files in this folder: <br>";
foreach ($files1 as $value) {
	if($value != ".") {
		if($value != "..") {	
		if($value != "index.php" && $value !== "deploy.php" && $value !== ".git"){
			 echo "<a href='$value'>$value</a> <br />\n";
		}
		}
	}
}*/

// file_get_contents call instead
$str = file_get_contents('https://www.sodexo.fi/ruokalistat/output/daily_json/5865/2017/11/24/fi');
//echo $str;
// decode JSON
$json = json_decode($str, true);

foreach ($json['courses'] as $field) {
   if ($field['category'] == 'Mix & Match'){continue;}   
   echo $field['category'];
   echo $field['title_fi'] . PHP_EOL;
   echo $field['desc_fi'] . PHP_EOL;
   echo $field['price'] . PHP_EOL . PHP_EOL;
}


?>



<!--
<div class="content">
<div class="fiilu-buttons"></div>
<div class="food-fiilu"></div> -->
</div>
</body>
</html>
