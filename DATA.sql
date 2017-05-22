insert into Categorias(id_categoria,descripcion) values(0,"Coordenadas Mapa");
insert into Categorias(id_categoria,descripcion) values(1,"Elevaciones y Volcanes");


insert into Respuestas(id_respuesta,descripcion,categoria) values(1,"Cerro Chirripo",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(2,"Volcan Irazu",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(3,"Volcan Poas",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(4,"Cerro Cacho Negro",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(5,"Volcan Miravalles",1);
insert into Respuestas(id_respuesta,descripcion,categoria) values(6,"Parque Nacional Braulio Carrillo_10.160180908178564,-83.97468566894531",0);
insert into Respuestas(id_respuesta,descripcion,categoria) values(7,"Isla Uvita_9.993559,-83.011633",0);

insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(1,"Cual es la elevacion mas alta de nuestro pais",1,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(2,"Cual de las siguientes elevaciones tiene una altura aproximada de 3432msnm",2,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(3,"En cual de las siguientes elevaciones se encuentra el proyecto geotermico mas grande del pais",1,1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(4,"Donde se encuentra el Parque Nacional Braulio Carrillo",6,0);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta,categoria) values(5,"Donde se encuentra puerto limon",7,0);