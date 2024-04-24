<?php
session_start();

$hostname = "";
$user = "";
$pass = "";
$dbname = "";
//error_reporting(0);
//ini_set('display_errors', 0);

$con = mysqli_connect($hostname, $user, $pass, $dbname) or die("Error in connection");

        $username = $_POST['name'];    

        $password = $_POST['password'];

        // Check user is exist in the database
        $query    = "SELECT * FROM users WHERE username='".$username."'
                     AND password='".$password."'";

        $result = mysqli_query($con, $query) or die(mysql_error());
        $rows = mysqli_num_rows($result);
        while ($row = mysqli_fetch_assoc($result)) {
            $type= $row['kind'];
        }
        if ($rows == 1)
         {
        
           if($type=="admin"){
             echo 'admin';
           }
           if($type=="user"){
            echo 'user';
          }
         
        } 
        else 
        {
            echo 'no user found';
        }
mysqli_close($con);
?>
