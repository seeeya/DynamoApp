<?php
require_once("pages.php");
class sitesData {
    public function listMenu() {
		global $pages,$notfound,$frontpage;
		echo "<ul class='listMenu'>";
       $array = array_keys($pages);
	   $arrayLength = count($array);
	   
	   for($i = 0 ; $i < $arrayLength ; $i++) {
	   if ($i == 0) {
		   echo "<li><a href='".$array[$i]".'>" .ucfirst($array[$i]). "</a></li>";
	   } 
	   else {
		   echo "<li><a href='?page=".$array[$i]."'>" .ucfirst($array[$i]). "</a></li>";
	   }
	   
	   
	   }
	   echo "</ul>";
    }
	function displayPage($page) {
	  global $pages,$notfound,$frontpage;
	  if (isset($page)) {
		if (isset($pages[$page])) {
		  include($pages[$page]);
		}
		else {
		  include($notfound);
		}
	  }
	  else {
		include($frontpage);
	  }

	}
}
?>