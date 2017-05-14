<?php
	// Include confi.php
	include_once('confi.php');

	 $qur = mysql_query("SELECT id_pregunta, descripcion, respuesta_correcta FROM Preguntas WHERE categoria=0 ORDER BY RAND() LIMIT 1;");
		//$result =array();
		while($r = mysql_fetch_array($qur)){
			extract($r);
			$result = array("id_pregunta" => $id_pregunta, "descripcion" => $descripcion, 'respuesta_correcta' => $respuesta_correcta); 
		}
                
        $query2 = mysql_query("SELECT id_respuesta, descripcion FROM Respuestas WHERE "
                        . "id_respuesta='$respuesta_correcta';");
                $result2 = array();
                while($r3 = mysql_fetch_array($query2)){
			extract($r3);
			$result2 = array("id_respuesta" => $id_respuesta, "descripcion" => $descripcion); 
		}
        $json = array("pregunta" => $result,"respuesta"=>$result2);
	@mysql_close($conn);

	/* Output header */
	header('Content-type: application/json');
	echo json_encode($json);
        
        