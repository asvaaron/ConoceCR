using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Configuration;
using System.Data.SqlClient;
using System.Web.Script.Serialization;
using System.Web.Script.Services;



namespace appCCRWS {

    class Pregunta{
        public int id_pregunta { get; set; }
        public string descripcion { get; set; }
        public int respuesta_correcta { get; set; }
    };
    class Respuesta {
        public int id_respuesta { get; set; }
        public string descripcion { get; set; }
    };

    class PreguntaCompleta {
        public Pregunta pregunta { get; set; }
        public Respuesta[] respuestas { get; set; }
    }


    /// <summary>
    /// Summary description for CCR_WS
    /// </summary>
    [WebService(Namespace = "http://conoce.cr/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class CCR_WS : System.Web.Services.WebService {
        public String connectionString = "Data Source=.\\SQLEXPRESS;Initial Catalog=appCCR;User id=sa;Password=1234;";

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public string obtenerPregunta_aleatoria() {
            Pregunta pregunta = null;
            SqlDataReader reader;
            SqlConnection conn = new SqlConnection(connectionString);
            SqlCommand command = new SqlCommand("obtenerPregunta_aleatoria", conn);
            command.CommandType = CommandType.StoredProcedure;
            conn.Open();
            reader = command.ExecuteReader();
            int id, rc;
            string desc;
            Respuesta[] respuestas = null;
            while (reader.Read()) {
                id = Int32.Parse(reader["id_pregunta"].ToString());
                desc = reader["descripcion"].ToString();
                rc = Int32.Parse(reader["respuesta_correcta"].ToString());           
                respuestas = obtenerRespuestas(rc);
                pregunta = new Pregunta();
                pregunta.id_pregunta = id;
                pregunta.descripcion = desc;
                pregunta.respuesta_correcta = rc;
            }
            PreguntaCompleta pc = new PreguntaCompleta();
            pc.pregunta = pregunta;
            pc.respuestas = respuestas;
            conn.Close();
            return new JavaScriptSerializer().Serialize(pc);
        }

      
        private Respuesta[] obtenerRespuestas(int correcta) {
            Respuesta[] respuestas = new Respuesta[4];
            SqlDataReader reader;
            SqlConnection conn = new SqlConnection(connectionString);
            SqlCommand command = new SqlCommand("obtenerRespuestas", conn);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.Add("@correcta", SqlDbType.Int).Value = correcta;
            conn.Open();
            reader = command.ExecuteReader();
            int i = 0;
            while (reader.Read()) {
                respuestas[i] = new Respuesta();
                respuestas[i].id_respuesta= Int32.Parse(reader["id_respuesta"].ToString());
                respuestas[i].descripcion = reader["descripcion"].ToString();
                i++;
            }
            respuestas[i] = new Respuesta();
            respuestas[3] = obtenerRespuestaCorrecta(correcta);
            Random rnd = new Random();
            Respuesta[] resp = respuestas.OrderBy(x => rnd.Next()).ToArray();
            conn.Close();
            return resp;
        }

        private Respuesta obtenerRespuestaCorrecta(int correcta) {
            Respuesta respuestas = new Respuesta();
            SqlDataReader reader;
            SqlConnection conn = new SqlConnection(connectionString);
            SqlCommand command = new SqlCommand("obtenerRespuestaCorrecta", conn);
            command.CommandType = CommandType.StoredProcedure;
            command.Parameters.Add("@correcta", SqlDbType.Int).Value = correcta;
            conn.Open();
            reader = command.ExecuteReader();
            while (reader.Read()) {
                respuestas.id_respuesta = Int32.Parse(reader["id_respuesta"].ToString());
                respuestas.descripcion = reader["descripcion"].ToString();
            }
            conn.Close();
            return respuestas;
        }

    }
}
