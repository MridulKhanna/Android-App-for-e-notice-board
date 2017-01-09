
Skip to content
This repository

    Pull requests
    Issues
    Gist

    @MridulKhanna

0
0

    0

MridulKhanna/Firebase-Cloud-Messaging
Code
Issues 0
Pull requests 0
Projects 0
Wiki
Pulse
Graphs
Settings
Firebase-Cloud-Messaging/PHP_SCRIPTS/push_notifications.php
64707ef 11 days ago
@MridulKhanna MridulKhanna Update push_notifications.php
53 lines (48 sloc) 1.34 KB
<?php 
if($_SERVER['REQUEST_METHOD']=='POST')
{
echo "Notication sent";
}
else
{
echo "Not sent";
}
	function send_notification ($tokens, $message)
	{
		$url = 'https://fcm.googleapis.com/fcm/send';
		$fields = array(
			 'registration_ids' => $tokens,
			 'data' => $message
			);
		$headers = array(
			'Authorization:key =unique_key',
			'Content-Type: application/json'
			);
	   $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);           
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
	}
	
	$conn = mysqli_connect("mysql.hostinger.in","user","password","database");
	$sql = " Select Token From users";
	$result = mysqli_query($conn,$sql);
	$tokens = array();
	if(mysqli_num_rows($result) > 0 ){
		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["Token"];
		}
	}
	mysqli_close($conn);
       
	$message_status = send_notification($tokens, $message);
	echo $message_status;
 ?>		

    Contact GitHub API Training Shop Blog About 

    © 2017 GitHub, Inc. Terms Privacy Security Status Help 

