<?php
	// Include confi.php
	include_once('confi.php');

	//$uid = isset($_GET['uid']) ? mysql_real_escape_string($_GET['uid']) :  "";
	//if(!empty($uid)){
		$qur = mysql_query("SELECT id_pregunta, descripcion, respuesta_correcta FROM Preguntas ORDER BY RAND() LIMIT 1;");
		//$result =array();
		while($r = mysql_fetch_array($qur)){
			extract($r);
			$result = array("id_pregunta" => $id_pregunta, "descripcion" => $descripcion, 'respuesta_correcta' => $respuesta_correcta); 
		}
               //@mysql_close($conn); 
                
                $query = mysql_query("SELECT id_respuesta, descripcion
FROM Respuestas WHERE id_respuesta<>'$respuesta_correcta' ORDER BY RAND() LIMIT 3;");
                $res =array();
                while($r2 = mysql_fetch_array($query)){
			extract($r2);
			$res[] = array("id_respuesta" => $id_respuesta, "descripcion" => $descripcion); 
		}
              //@mysql_close($conn);
                
                $query2 = mysql_query("SELECT id_respuesta, descripcion FROM Respuestas WHERE "
                        . "id_respuesta='$respuesta_correcta';");
                $result2 = array();
                while($r3 = mysql_fetch_array($query2)){
			extract($r3);
			$result2[] = array("id_respuesta" => $id_respuesta, "descripcion" => $descripcion); 
		}
                
                shuffle($result2);
                $arresp = array_merge($result2,$res);
                        shuffle($arresp);
		$json = array("pregunta" => $result,"respuestas"=>$arresp);
	@mysql_close($conn);

	/* Output header */
	header('Content-type: application/json');
	echo json_encode($json);