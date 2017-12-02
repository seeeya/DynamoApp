<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>LutakkoApp Analytics</title>
  <style>
    body {
      font-family: sans-serif;
    }
    #header td {
      padding: 15px;
      background-color: #ccd9ff;
    }
    .result td {
      border: 1px solid #ccd9ff;
      padding: 10px;
    }
  </style>
</head>
<body>
  <h1>LutakkoApp Visits</h1>
  <table>
    <tr id="header"><td>Place</td><td>Time of enter</td><td>Time of exit</td><td>Duration of visit (minutes)</td><td>Visitor device</td></tr>
    <?php
      require_once("/home/phakala/mysql_conn.php");
      if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
      }
      $sql = "SELECT * FROM visit";
      $result = mysqli_query($conn, $sql);

      if (mysqli_num_rows($result) != 0) {
        while($row = mysqli_fetch_assoc($result)) {
          echo "<tr class='result'><td>". $row["place"]."</td><td>". $row["enter_time"]."</td><td>". $row["exit_time"]."</td><td>". $row["duration"]."</td><td>". $row["visitor"]."</td><tr>";
        }
      }
    ?>
  </table>
</body>
</html>
