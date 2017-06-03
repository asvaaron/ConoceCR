<?php

include_once('confi.php');

$usr = isset($_GET['usr']) ? mysql_real_escape_string($_GET['usr']) : "";
$idusr = "";
if (!empty($usr)) {
    $sql = mysql_query("INSERT INTO usuarios (nombre) VALUES ('$usr')");
    if ($sql === TRUE) {
        $qur = mysql_query("SELECT id_usuario FROM usuarios WHERE nombre='$usr';");
        while ($r = mysql_fetch_array($qur)) {
            extract($r);    
            $sql1 = mysql_query("INSERT INTO puntajes (usuario,puntaje,fecha) VALUES ('$id_usuario','0',CURRENT_TIMESTAMP)");
            if ($sql1 === TRUE) {
                $json = array("resultado" => "OK", "puntajes" => "OK", "id_usuario" => $id_usuario);
            } else {
                $json = array("resultado" => "error ingresando puntaje");
            }
        }
    } else {
        $json = array("resultado" => "error agregando usuario");
    }
} else {
    $json = array("resultado" => "usr vacio");
}

header('Content-type: application/json');
echo json_encode($json);
@mysql_close($conn);

