<?php
$hostname = "";
$username = "";
$password = "";
$dbname = "";

// Create connection
$conn = new mysqli($hostname, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$query = "INSERT INTO events (e_id, ename, edate, evenue, etime, eorg, contact) VALUES (?, ?, ?, ?, ?, ?, ?)";
$stmt = $conn->prepare($query);
$stmt->bind_param("sssssss", $e_id, $ename, $edate, $evenue, $etime, $eorg, $cont);


$e_id = $_POST["id"];
$ename = $_POST["ename"];
$edate = $_POST["date"];
$evenue = $_POST["venue"];
$etime = $_POST["time"];
$eorg = $_POST["org"];
$cont = $_POST["contact"];


if ($stmt->execute()) {
    echo "Event added successfully";
} else {
    echo "Error in insertion: " . $stmt->error;
}

// Close the connection
$stmt->close();
$conn->close();
?>
