<?php

include_once('confi.php');

$qur = mysql_query("
SELECT usuarios.nombre as Nombre_Usuario,puntajes.puntaje as Puntaje,puntajes.fecha as Fecha from puntajes, usuarios where usuarios.id_usuario=puntajes.usuario ORDER BY puntaje DESC LIMIT 10;");
//$result =array();
while ($r = mysql_fetch_array($qur)) {
    extract($r);
    $result = array("Nombre_Usuario" => $Nombre_Usuario, "Puntaje" => $Puntaje, 'Fecha' => $Fecha);
}

@mysql_close($conn);

	/* Output header */
	header('Content-type: application/json');
	echo json_encode($result);