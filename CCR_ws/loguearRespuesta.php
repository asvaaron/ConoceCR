<?php

include_once('confi.php');
//http://localhost/CCR_ws/loguearRespuesta.php?usr=1&pnt=20&preg=2&resp_sel=2
$usr = isset($_GET['usr']) ? mysql_real_escape_string($_GET['usr']) :  "";
$pnt = isset($_GET['pnt']) ? mysql_real_escape_string($_GET['pnt']) :  "";
$preg = isset($_GET['preg']) ? mysql_real_escape_string($_GET['preg']) :  "";
$resp_sel = isset($_GET['resp_sel']) ? mysql_real_escape_string($_GET['resp_sel']) :  "";

if(!empty($usr)&& $pnt>=0 && !empty($preg) && $resp_sel>=0){
     
    $sql = mysql_query("INSERT INTO intentos (usuario,puntaje,pregunta,respuesta_seleccionada) VALUES ('$usr','$pnt','$preg','$resp_sel')");
          
    if ($sql === TRUE) {
        $json= array("resultado" => "OK");
    }else {
        $json= array("resultado" => "no sirvio_'$usr','$pnt','$preg','$resp_sel'");
    }

header('Content-type: application/json');
echo json_encode($json);
@mysql_close($conn);


}

