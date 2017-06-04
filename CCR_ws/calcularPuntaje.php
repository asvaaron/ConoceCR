<?php

$id_usr = isset($_GET['id_usr']) ? mysql_real_escape_string($_GET['id_usr']) : "";

$insert = "CALL calcularPuntaje(:Input_Value,@ppuntaje)";
$bdd = new PDO('mysql:host=localhost;dbname=appccr', 'root', 'root');

$stmt = $bdd->prepare($insert);     
$stmt->bindParam(':Input_Value', $id_usr, PDO::PARAM_STR); 

$stmt->execute();
$tabResultat = $stmt->fetch();
$ppuntaje = $tabResultat['ppuntaje'];
$result = array("id_usuario" => $id_usr, "puntaje" => $ppuntaje);
    header('Content-type: application/json');
    echo json_encode($result);

