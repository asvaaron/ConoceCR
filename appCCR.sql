
create table Categorias (
  id_categoria INT NOT NULL,
  descripcion VARCHAR(100) NULL,
  PRIMARY KEY (id_categoria));

create table Respuestas(
	id_respuesta int,
	descripcion varchar(100),
	categoria int,
CONSTRAINT PK_Respuestas PRIMARY KEY (id_respuesta),
CONSTRAINT FK_RespuestasCategorias FOREIGN KEY (categoria)
REFERENCES Categorias(id_categoria)
);

create table Preguntas(
	id_pregunta int,
	descripcion varchar(100),
	respuesta_correcta int,
	categoria int,
CONSTRAINT PK_Preguntas PRIMARY KEY (id_pregunta),
CONSTRAINT FK_PreguntasCategorias FOREIGN KEY (categoria)
REFERENCES Categorias(id_categoria),
CONSTRAINT FK_PreguntasRespuestas FOREIGN KEY (respuesta_correcta)
REFERENCES Respuestas(id_respuesta)
);

create table Usuarios(
	id_usuario int  NOT NULL AUTO_INCREMENT,
	nombre varchar(100),
CONSTRAINT PK_Usuarios PRIMARY KEY (id_usuario)
);

create table Puntajes(
	id_puntaje int NOT NULL AUTO_INCREMENT,
	usuario int,
	puntaje int,
	fecha datetime,
CONSTRAINT PK_Puntajes PRIMARY KEY (id_puntaje),
CONSTRAINT FK_UsuariosRespuestas FOREIGN KEY (usuario)
REFERENCES Usuarios(id_usuario)
);

create table Intentos(
	puntaje int,
	pregunta int,
    usuario int,
	respuesta_seleccionada int,
	CONSTRAINT FK_PreguntasIntentos FOREIGN KEY (pregunta)
REFERENCES Preguntas(id_pregunta),
	CONSTRAINT FK_UsuarioIntentos FOREIGN KEY (usuario)
REFERENCES Usuarios(id_usuario),
	CONSTRAINT FK_RespuestasIntentos FOREIGN KEY (respuesta_seleccionada)
REFERENCES Respuestas(id_respuesta)
);

