package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;  // Connection, Statement y ResultSet

@WebServlet("/JDBCConectaMySQL")
public class JDBCConectaMySQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
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
            while (resultado.next()) {
                out.println(resultado.getString(1) + "\t" + resultado.getString(2) + " <br />");
            }
		} catch(ClassNotFoundException e) {
			out.println(e); 
        } catch(SQLException e) {
			out.println(e); 
        } catch(Exception e) {
			out.println(e); 
        } finally {            
			out.close();
            try {
				resultado.close();
				sentencia.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}        
        }
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}