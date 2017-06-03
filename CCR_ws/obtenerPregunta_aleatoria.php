<?php
error_reporting(0);
// Include confi.php

include_once('confi.php');
rpt:
//$cat = isset($_GET['cat']) ? mysql_real_escape_string($_GET['cat']) :  "";
//if(!empty($uid)){
$que = mysql_query("SELECT id_categoria from Categorias WHERE id_categoria<>0 ORDER BY RAND() LIMIT 1;");
while ($rr = mysql_fetch_array($que)) {
    $cat = $rr['id_categoria'];
}

if ($cat != "" || $cat!=null) {

    $qur = mysql_query("SELECT id_pregunta, descripcion, respuesta_correcta FROM Preguntas WHERE categoria='$cat' ORDER BY RAND() LIMIT 1;");
    //$result =array();
    while ($r = mysql_fetch_array($qur)) {
        extract($r);
        $result = array("id_pregunta" => $id_pregunta, "descripcion" => $descripcion, 'respuesta_correcta' => $respuesta_correcta);
    }
    //@mysql_close($conn); 
    if ($respuesta_correcta != "" || $respuesta_correcta!=null) {
        $query = mysql_query("SELECT id_respuesta, descripcion
FROM Respuestas WHERE id_respuesta<>'$respuesta_correcta' AND categoria='$cat' ORDER BY RAND() LIMIT 3;");
        $res = array();
        while ($r2 = mysql_fetch_array($query)) {
            extract($r2);
            $res[] = array("id_respuesta" => $id_respuesta, "descripcion" => $descripcion);
        }
        //@mysql_close($conn);

        $query2 = mysql_query("SELECT id_respuesta, descripcion FROM Respuestas WHERE "
                . "id_respuesta='$respuesta_correcta';");
        $result2 = array();
        while ($r3 = mysql_fetch_array($query2)) {
            extract($r3);
            $result2[] = array("id_respuesta" => $id_respuesta, "descripcion" => $descripcion);
        }

        shuffle($result2);
        $arresp = array_merge($result2, $res);
        shuffle($arresp);
        $json = array("pregunta" => $result, "respuestas" => $arresp);
        @mysql_close($conn);

        
    } else {
 
        goto rpt;
    }
} else {
    
    goto rpt;
}

/* Output header */
        header('Content-type: application/json');
        
        if($json!="" || $json!=null){
        echo json_encode($json);
        
        }else{goto rpt;}