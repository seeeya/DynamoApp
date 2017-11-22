<?php
require "mysql_conn.php";
$place = $_POST("place");
$entertime = $_POST("time");
$id = $_POST("id");
$query = "INSERT into visit (place, enter_time, visitor VALUES ('$place', '$time', 'id')";
if($conn->query($query) == TRUE) {
	echo "Insert successful";
}
else echo "Error " . $conn->error;
conn->close();
?>