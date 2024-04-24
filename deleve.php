<?php
$hostname = "localhost";
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
$query = "DELETE FROM events WHERE e_id = ?;";
$stmt = $conn->prepare($query);
$stmt->bind_param("s", $e_id);

// Set values from POST data
$e_id = $_POST["id"];


// Execute the query
if ($stmt->execute()) {
    echo "Event removed successfully";
} else {
    echo "Error in deletion: " . $stmt->error;
}

// Close the connection
$stmt->close();
$conn->close();
?>
