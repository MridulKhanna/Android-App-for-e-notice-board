<?php
	define('HOST','mysql.hostinger.in');
	define('USER','user');
	define('PASS','password');
	define('DB','database');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
?>
