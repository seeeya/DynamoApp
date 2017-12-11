<?php
$page = $_GET["page"];
if ($page == "fiilu") {
	$restaurant = "Ravintola Fiilu";
}
else if ($page == "bittipannu") {
	$restaurant = "JAMK Ravintola Bittipannu";
}
else if ($page == "rajakatu") {
	$restaurant = "JAMK Ravintola Radis";
}
require_once("/home/phakala/mysql_conn.php");

function getAverage() {
	if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
      }
      $sql = "SELECT AVG(score) AS score FROM review WHERE place = '".$restaurant."'";
      $result = mysqli_query($conn, $sql);
      if (mysqli_num_rows($result) != 0) {
        while($row = mysqli_fetch_assoc($result)) {
          $average = $row['score'];
		  $rounded = round($average);
		  $extra = 5 - $rounded;
		  for ($i=0;$i<$rounded;$i++) {
			  echo "<span class='fa fa-star checked'></span>";
		  }
		  for ($i=0;$i<$extra;$i++) {
			  echo "<span class='fa fa-star'></span>";
		  }
        }
      }
	  else echo "Cant get ratings";
}
      
?>