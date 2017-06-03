<?php
	// Include confi.php
	include_once('confi.php');

  $sql = "INSERT INTO puntajes (id_puntaje, usuario, puntaje, fecha)
  VALUES ('1','1', '50', '9999-11-30 23:58:58')";

  $qur = mysql_query($sql);

	if ($qur === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

@mysql_close($conn);
