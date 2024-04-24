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

// Prepare and bind the query
$query = "UPDATE events
SET ename = ?, edate = ?, evenue = ?, etime = ?, eorg = ?, contact = ? WHERE e_id = ?;";
$stmt = $conn->prepare($query);
$stmt->bind_param("sssssss", $ename, $edate, $evenue, $etime, $eorg, $cont,$e_id);

// Set values from POST data
$e_id = $_POST["id"];
$ename = $_POST["ename"];
$edate = $_POST["date"];
$evenue = $_POST["venue"];
$etime = $_POST["time"];
$eorg = $_POST["org"];
$cont = $_POST["contact"];

// Execute the query
if ($stmt->execute()) {
    echo "updated successfully";
} else {
    echo "Error in updation: " . $stmt->error;
}

// Close the connection
$stmt->close();
$conn->close();
?>
