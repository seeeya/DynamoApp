
 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Smashing HTML5!</title>
 
 <!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-108664137-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-108664137-1');
</script>
  
  
  
</head>
 
<body id="index" class="home">
<?php
$dir    = './';
$files1 = scandir($dir);

echo "Files in this folder: <br>";
foreach ($files1 as $value) {
	if($value != ".") {
		if($value != "..") {	
		if($value != "index.php" && $value !== "deploy.php"){
			 echo "<a href='$value'>$value</a> <br />\n";
		}
		}
		
	}
   
}

?>

</body>
</html>
