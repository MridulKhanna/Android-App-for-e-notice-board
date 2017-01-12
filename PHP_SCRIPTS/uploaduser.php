<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		
        $user = $_POST['user'];
	$password=$_POST['password'];
	
	require_once('dbConnect.php');			
	$sql = "INSERT INTO authentication (user, password) VALUES('$user','$password')";
	
	$stmt = mysqli_prepare($con,$sql);		
		mysqli_stmt_execute($stmt);				//executs the query1
		
		$check = mysqli_stmt_affected_rows($stmt);		//returns total no of affected rows
		
		if($check == 1){
			echo "User Registered Successfully";
		}else{
			echo "Unable to register";
		}
		mysqli_close($con);					//close the connection
	}else{
		echo "Error";
	}
?>	






		