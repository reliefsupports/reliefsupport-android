<?php
date_default_timezone_set('Asia/Colombo');
 $date = date('Y-m-d H:i:s');
 
	$name = $_POST['name'];
	$telephone = $_POST['telephone'];
	$address = $_POST['address'];
	$city = $_POST['city'];
	$donation = $_POST['donation'];
	$information = $_POST['information'];
	$name = $_POST['name'];
	$name = $_POST['name'];
	include_once('connection.php');

	$query = "INSERT INTO donations (name, telephone, address, city, donation, information, created_at, updated_at, identifier) 
	
	VALUES ('$name', '$telephone', '$address', '$city', '$donation', '$information', '$date', '$date', 'a')";

	$result = mysqli_query($connection, $query);

	while($rows = mysqli_fetch_array($result)){
		$ress[] = $rows;
	}
mysqli_close($connection);
print json_encode(utf8ize($ress), JSON_UNESCAPED_SLASHES);

function utf8ize($d) {
    if (is_array($d)) {
        foreach ($d as $k => $v) {
            $d[$k] = utf8ize($v);
        }
    } else if (is_string ($d)) {
        return utf8_encode($d);
    }
    return $d;
}
        
?>