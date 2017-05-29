<?php
	// Include confi.php
	include_once('confi.php');

  $sql = "INSERT INTO 'puntajes' ('id_puntaje', 'usuario', 'puntaje', 'fecha')
  VALUES ('001','John', '50', '11/20/2017')";

  $qur = mysql_query($sql);

@mysql_close($conn);
