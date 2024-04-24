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
$query = "SELECT * FROM events ORDER BY edate;";
$stmt = $conn->prepare($query);

// Execute the query
if ($stmt->execute()) {
    // Fetch all rows from the result
    $result = $stmt->get_result();
    $events = array();

    while ($row = $result->fetch_assoc()) {
        // Add each row to the events array
        $event = array(
            "id"=>$row["e_id"],
            "name"=>$row["ename"],
            "date" => $row["edate"],
            "venue" => $row["evenue"],
            "time" => $row["etime"],
            "org" => $row["eorg"],
            "contact" => $row["contact"]
        );
        $events[] = $event;
    }

    // Create a response array
    $response = array(
        "status" => "success",
        "message" => "Events retrieved successfully",
        "events" => $events
    );

    // Convert to JSON and send the response
    echo json_encode($response);
} else {
    // Error response
    $response = array(
        "status" => "error",
        "message" => "Error in query execution: " . $stmt->error
    );
    echo json_encode($response);
}

// Close the connection
$stmt->close();
$conn->close();
?>
