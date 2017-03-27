USE appCCR;  
GO

CREATE PROCEDURE [dbo].[obtenerPregunta_aleatoria] 
AS   
SELECT TOP 1 id_pregunta, descripcion, respuesta_correcta
FROM Preguntas
ORDER BY NEWID()  
GO
  
CREATE PROCEDURE [dbo].[obtenerRespuestas]
@correcta int
AS   
SELECT TOP 3 id_respuesta, descripcion
FROM Respuestas WHERE id_respuesta<>@correcta
ORDER BY NEWID() 
GO

CREATE PROCEDURE [dbo].[obtenerRespuestaCorrecta]
@correcta int
AS   
SELECT id_respuesta, descripcion
FROM Respuestas WHERE id_respuesta=@correcta
GO