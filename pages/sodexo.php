<?php
// file_get_contents call instead
$str = file_get_contents('https://www.sodexo.fi/ruokalistat/output/daily_json/5865/2017/11/24/fi');
//echo $str;
// decode JSON
$json = json_decode($str, true);

foreach ($json['courses'] as $field) {
   if ($field['category'] == 'Mix & Match'){continue;}   
   echo $field['category'];
   echo $field['title_fi'];
   echo "\r\n";
   echo $field['desc_fi'];
   echo "\r\n";
   echo $field['price'];
   echo "\r\n";
   echo "\r\n";
}


?>