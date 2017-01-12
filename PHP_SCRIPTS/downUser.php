<?php
define('HOST','mysql.hostinger.in');
	define('USER','user');
	define('PASS','password');
	define('DB','database');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
$sql = "select * from authentication";
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('user'=>$row[0],
'password'=>$row[1]));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>
