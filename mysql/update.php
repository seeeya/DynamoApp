<?php
$dbname = "dynamoapp";
$dbuser = "dynamoapp";
$dbpassword = "yFgyeJicK59a3bEq";
$server = "walkonen.fi";
$conn = mysqli_connect($server, $dbuser, $dbpassword, $dbname);
$place = $_GET["place"];
$time = $_GET["time"];
$id = $_GET["id"];
$duration = $_GET["duration"];
$exit = $_GET["exit"];
$query = "UPDATE visit SET duration = ".$duration.", exit_time = '".$exit."'
 WHERE place = '".$place."' AND enter_time = '".$time."' AND visitor = '".$id."';
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