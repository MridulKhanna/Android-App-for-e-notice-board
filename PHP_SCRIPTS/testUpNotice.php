<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		
        $NoticeID = $_POST['NoticeID'];
	$Title=$_POST['Title'];
	$NoticeText=$_POST['NoticeText'];
	$UploadBy = $_POST['UploadBy'];
	$NoticeDept = $_POST['NoticeDept'];
		
	$IsFE = $_POST['IsFE'];
	$IsSE = $_POST['IsSE'];
	$IsTE = $_POST['IsTE'];
	$IsBE = $_POST['IsBE'];
	$NoticeDate = $_POST['NoticeDate'];
	$IsPlacement = $_POST['Placement'];
	
	require_once('dbConnect.php');			
	$sql = "INSERT INTO notices (NoticeID,  Title, NoticeText, UploadBy, NoticeDept,IsFE, IsSE,IsTE,IsBE,NoticeDate,IsPlacement) VALUES('$NoticeID','$Title','$NoticeText','$UploadBy','$NoticeDept','$IsFE','$IsSE','$IsTE','$IsBE','$NoticeDate','$IsPlacement')";
	
	$stmt = mysqli_prepare($con,$sql);		
		mysqli_stmt_execute($stmt);				//executs the query1
		
		$check = mysqli_stmt_affected_rows($stmt);		//returns total no of affected rows
		
		if($check == 1){
			echo "Notice Uploaded Successfully";
		}else{
			echo "Error Uploading Notice";
		}
		mysqli_close($con);					//close the connection
	}else{
		echo "Error";
	}
?>	






		