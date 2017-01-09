<?php 
	if (isset($_POST["Token"])){
		
		   $_uv_Token=$_POST["Token"];
		   $conn = mysqli_connect("mysql.hostinger.in","user","password","database");
		   $query="insert into users values('$_uv_Token')
                   ON DUPLICATE KEY UPDATE Token = '$_uv_Token';";
              
      mysqli_query($conn,$query);
      mysqli_close($conn);
	}
 ?>
