<?php
require "mysql_conn.php";
$place = $_GET["place"];
$time = $_GET["time"];
$id = $_GET["id"];
$query = "INSERT INTO visit (place, enter_time, visitor) VALUES ('". $place . "', '" . $time . "', '" . $id . "')";
if($conn->query($query) === TRUE) {
	echo "Insert successful";
}
else echo "Error " . $conn->error;
conn->close();

?>