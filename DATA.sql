-- Categorias 

insert into Categorias(id_categoria,descripcion) values(0,"Coordenadas Mapa");
insert into Categorias(id_categoria,descripcion) values(1,"Elevaciones y Volcanes");
insert into Categorias(id_categoria,descripcion) values(2,"Playas");
insert into Categorias(id_categoria,descripcion) values(3,"Islas");

-- Respuestas 
insert into respuestas(id_respuesta,descripcion,categoria) values(0,"Coordenada Incorrecta",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(1,"Cerro Chirripo",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(2,"Volcan Irazu",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(3,"Volcan Poas",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(4,"Cerro Cacho Negro",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(5,"Volcan Miravalles",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(6,"Parque Nacional Braulio Carrillo_10.160180908178564,-83.97468566894531",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(7,"Isla Uvita_9.993559,-83.011633",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(8,"Parue Nacional Los Quetzales_9.535248,-83.892748",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(9,"Parque Nacional Tapanti_9.750915,-83.779895",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(10,"Parque Nacional Juan Castro Blanco_10.272861,-84.334059",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(11,"Isla del Coco",3);
insert into Respuestas(id_respuesta,descripcion,categoria) values(12,"Isla Calero",3);
insert into Respuestas(id_respuesta,descripcion,categoria) values(13,"Isla San Lucas",3);
insert into Respuestas(id_respuesta,descripcion,categoria) values(14,"Isla Chira",3);
insert into Respuestas(id_respuesta,descripcion,categoria) values(15,"Isla del Caño",3);




-- Preguntas 

insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(1,"Cual es la elevacion mas alta de nuestro pais",1,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(2,"Cual de las siguientes elevaciones tiene una altura aproximada de 3432msnm",2,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(3,"En cual de las siguientes elevaciones se encuentra el proyecto geotermico mas grande del pais",5,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(4,"Donde se encuentra el Parque Nacional Braulio Carrillo",6,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(5,"Donde se encuentra Puerto Limon",7,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(6,"Donde se encuentra el Parque Nacional Los Quetzales",8,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(7,"Donde se encuentra el Parque Nacional Tapanti",9,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(8,"Donde se encuentra el Parque Nacional  Juan Castro Blanco",10,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(9,"Cual de las siguientes islas es Patrimonio Natural de la Humanidad",11,3);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(10,"Cual de las siguientes islas fue la sede del centro penitenciaro mas famoso de Costa Rica",13,3);
