<?php 
require_once("functions.php");
$page = new sitesData();
?>
<div class="page-container">
<h2>Welcome to LunchApp</h2> 
<h4>You can find content from here:</h4>
<?=$page->listMenu();?>

</div>