<?php
$dbname = "dynamoapp";
$dbuser = "dynamoapp";
$dbpassword = "yFgyeJicK59a3bEq";
$server = "walkonen.fi";
$conn = mysqli_connect($server, $dbuser, $dbpassword, $dbname);
$place = $_GET["place"];
$time = $_GET["time"];
$id = $_GET["id"];
$query = "INSERT INTO visit (place, enter_time, visitor) VALUES ('". $place . "', '" . $time . "', '" . $id . "')";
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