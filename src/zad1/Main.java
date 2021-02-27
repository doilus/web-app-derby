package zad1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



@WebServlet(urlPatterns = "/Main")
public class Main extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	DataSource dataSource;
	ServletContext context;
	String presentationServ;
	String getParamsServ;
	Command command ;

	
	
	public void init() throws ServletException{
		try {
			 Context init = new InitialContext();
			 Context contx = (Context) init.lookup("java:comp/env");
			 dataSource = (DataSource) contx.lookup("jdbc/ksidb");
		} catch ( NamingException e) {
			e.printStackTrace();
		} 
		 
	}


	/*
	 * Metody HttpServlet
	 * */
	
	
	public void serviceRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		
		response.setContentType("text/html; charset=windows-1250");
		PrintWriter writer = response.getWriter();
		writer.println("<h2> Lista dostępnych książek</h2>");
		 Connection conn = null;
		try {
			synchronized (dataSource) {
				conn = dataSource.getConnection();
			}
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from pozycje");
			writer.println("<ol>");
			while (rs.next()) {
				String title = rs.getString("TYTUL");
				float price = rs.getFloat("CENA");
				writer.println("<li>" + title + " - cena " + price + "</li>");
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			writer.println(e.getMessage());
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception exc) {}
		}
		writer.close();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		serviceRequest(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		serviceRequest(request,response);
	}
	
	
	
	
	/*
	//metoda wyświetlająca dane z tabeli Autor
	private static void SelectAutors() throws SQLException, IOException, ClassNotFoundException {
		Statement stmt;
		Connection conn = null;
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
		 conn = DriverManager.getConnection(dbURL); 
	
		stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery("select * from Autor");
        ResultSetMetaData rsmd = results.getMetaData();
        int numberCols = rsmd.getColumnCount();
        for (int i=1; i<=numberCols; i++)
        {
            //print Column Names
           System.out.println(rsmd.getColumnLabel(i)+"\t\t");  
        }
        System.out.println();
        while(results.next())
        {
            int id = results.getInt(1);
            String autorName = results.getString(2);
           // String cityName = results.getString(3);
            System.out.println(id + "\t\t" + autorName);
        }
        results.close();
        stmt.close();
	}*/

}
