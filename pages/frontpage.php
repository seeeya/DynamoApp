<?php 
require_once("functions.php");
$page = new sitesData();
?>

<h2>Welcome to LutakkoApp</h2> 
<h4>You can find content from here:</h4>
<?=$page->listMenu();?>