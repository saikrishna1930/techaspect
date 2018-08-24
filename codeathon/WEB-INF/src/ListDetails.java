// Loading required libraries
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ListDetails extends HttpServlet{

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
 
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Student Details";
      
    
      out.println(
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n");
      try {
         // Register JDBC driver
          Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/StudentInformation","root","mgit");

         // Execute SQL query
         Statement stmt = conn.createStatement();
         String sql;
         sql = "SELECT * FROM Student";
         ResultSet rs = stmt.executeQuery(sql);

         // Extract data from result set
	out.println("<table>");
		out.println("<tr><td>Name</td>");
		out.println("<td>Age</td>");
		out.println("<td>branch</td>");
		out.println("<td>year</td>");
		out.println("<td>semester</td>");
		out.println("<td>score</td></tr>");
         while(rs.next()){
            //Retrieve by column name
		out.println("<tr>");
		
            String name = rs.getString("name");
            int age  = rs.getInt("age");
            String branch = rs.getString("branch");
		int year = rs.getInt("year");
		int semester= rs.getInt("semester");
		int score = rs.getInt("score");
            //Display values
            out.println("<td> " + name+ "</td>");
            out.println("<td> " + age + "</td>");
            out.println("<td>" + branch + "</td>");
            out.println("<td> " + year+ "</td>");
		out.println("<td> " +semester + "</td>");
		out.println("<td> " + score+ "</td>");
		out.println("</tr>");
         }
         out.println("</body></html>");

         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if(stmt!=null)
               stmt.close();
         } catch(SQLException se2) {
         } // nothing we can do
         try {
            if(conn!=null)
            conn.close();
         } catch(SQLException se) {
            se.printStackTrace();
         } //end finally try
      } //end try
   }
} 