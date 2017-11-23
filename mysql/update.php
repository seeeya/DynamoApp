<?php
require __DIR__ . "/home/phakala/mysql_conn.php";
$place = $_GET["place"];
$time = $_GET["time"];
$id = $_GET["id"];
$duration = $_GET["duration"];
$duration = intval($duration);
$exit = $_GET["exit"];
$query = "UPDATE visit SET duration = ".$duration.", exit_time = '".$exit."'
 WHERE place = '".$place."' AND enter_time = '".$time."' AND visitor = '".$id."'";
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
if (mysqli_query($conn, $query)) {
    echo "Success";
} else {
    echo "Error: " . $query . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);


?>