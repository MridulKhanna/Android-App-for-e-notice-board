<?php
define('HOST','mysql.hostinger.in');
	define('USER','user');
	define('PASS','password');
	define('DB','database');
	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 
$sql = "select * from notices";
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('NoticeID'=>$row[0],
'Title'=>$row[1],
'NoticeText'=>$row[2],
'UploadBy'=>$row[3],
'NoticeDept'=>$row[4],
'IsFE'=>$row[5],
'IsSE'=>$row[6],
'IsTE'=>$row[7],
'IsBE'=>$row[8],
'NoticeDate'=>$row[9],
'IsPlacement'=>$row[10]

));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>
