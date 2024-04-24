<?php
$hostname="";
$user="";
$pass="";
$dbname="";



$con=mysqli_connect($hostname,$user,$pass,$dbname) or die("error in connection");

$username=$_POST["name"];
$fullname=$_POST["fname"];
$email=$_POST["email"];
$password=$_POST["password"];

$kind='user';
$query = "insert into users  values ('".$username."','".$fullname."','".$email."','".$password."','".$kind."')";


$ins=mysqli_query($con,$query);

if($ins){
    echo "SUCCESFULLY REGISTERED";
}
else{
    echo "REGISTRATION FAILED";
}


?>