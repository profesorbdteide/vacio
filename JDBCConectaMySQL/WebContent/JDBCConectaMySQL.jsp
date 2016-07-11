<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import= "java.sql.*" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8" />
  <title>Conexión a MySQL vía JDBC desde un JSP</title>
</head>
<body>
 <%
 // Inicializa los objetos conexion, instruccion y resultado
  Connection conexion = null;
  Statement sentencia = null;
  ResultSet resultado = null;
  
  try {
      // cargar clase de controlador de base de datos
      Class.forName("com.mysql.jdbc.Driver");
      // crear el objeto de conexión a la base de datos
      conexion = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=");
      // crear objeto Statement para realizar consultas a la base de datos
      sentencia = conexion.createStatement();
      // un objeto ResultSet, almacena los datos de resultados de una consulta
      String consultaSQL = "SELECT Host,User FROM user";
      resultado = sentencia.executeQuery(consultaSQL);
      out.println("HOST -- USER<br/>");
      while(resultado.next()) {
          out.println(resultado.getString(1) + "&emsp;" + resultado.getString(2) + " <br/>");
      }
  } catch (ClassNotFoundException e) {
	  out.println(e); 
  } catch (SQLException e) {
	  out.println(e); 
  } catch (Exception e){
	  out.println(e); 
  } finally {            
      try {
			resultado.close();
			sentencia.close();
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
 %>
</body>
</html>