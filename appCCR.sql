create table Respuestas(
	id_respuesta int,
	descripcion varchar(100),
CONSTRAINT PK_Respuestas PRIMARY KEY (id_respuesta)
);

create table Preguntas(
	id_pregunta int,
	descripcion varchar(100),
	respuesta_correcta int,
CONSTRAINT PK_Preguntas PRIMARY KEY (id_pregunta),
CONSTRAINT FK_PreguntasRespuestas FOREIGN KEY (respuesta_correcta)
REFERENCES Respuestas(id_respuesta)
);

create table Usuarios(
	id_usuario int,
	nombre varchar(100),
	contrasena varchar(100),
CONSTRAINT PK_Usuarios PRIMARY KEY (id_usuario)
);

create table Puntajes(
	id_puntaje int,
	usuario int,
	puntaje int,
	fecha datetime,
CONSTRAINT PK_Puntajes PRIMARY KEY (id_puntaje),
CONSTRAINT FK_UsuariosRespuestas FOREIGN KEY (usuario)
REFERENCES Usuarios(id_usuario)
);

create table Intentos(
	usuario int,
	puntaje int,
	pregunta int,
	respuesta_seleccionada int,
	CONSTRAINT FK_PreguntasIntentos FOREIGN KEY (pregunta)
REFERENCES Preguntas(id_pregunta),
	CONSTRAINT FK_PuntajesIntentos FOREIGN KEY (puntaje)
REFERENCES Puntajes(id_puntaje),
	CONSTRAINT FK_UsuarioIntentos FOREIGN KEY (usuario)
REFERENCES Usuarios(id_usuario),
	CONSTRAINT FK_RespuestasIntentos FOREIGN KEY (respuesta_seleccionada)
REFERENCES Respuestas(id_respuesta),
);



insert into Respuestas(id_respuesta,descripcion) values(1,"Cerro Chirripo");
insert into Respuestas(id_respuesta,descripcion) values(2,"Volcan Irazu");
insert into Respuestas(id_respuesta,descripcion) values(3,"Volcan Poas");
insert into Respuestas(id_respuesta,descripcion) values(4,"Cerro Cacho Negro");
insert into Respuestas(id_respuesta,descripcion) values(5,"Volcan Miravalles");

insert into Preguntas(id_pregunta,descripcion,respuesta_correcta) values(1,"Cual es la elevacion mas alta de nuestro pais",1);
insert into Preguntas(id_pregunta,descripcion,respuesta_correcta) values(2,"Cual de las siguientes elevaciones tiene una altura aproximada de 3432msnm",2);