import java.io.*;
import java.lang.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class InputFormAction extends HttpServlet{
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
        try{
      String username = request.getParameter("name");
      String age1 = request.getParameter("age");
	 String branch = request.getParameter("branch");
	 String year1 = request.getParameter("year");
	 String  semester1= request.getParameter("semester");
	 String  score1= request.getParameter("score");	
	int age,year,semester,score;
	age=Integer.parseInt(age1);		
	year=Integer.parseInt(year1);	
	semester=Integer.parseInt(semester1);
	score=Integer.parseInt(score1);		


//      out.println(username);
      
      Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/StudentInformation","root","mgit");  
      PreparedStatement pst = con.prepareStatement("insert into Student values(?,?,?,?,?,?)");
      pst.setString(1,username);
      pst.setInt(2,age);
	pst.setString(3,branch);
	pst.setInt(4,year);
	pst.setInt(5,semester);
	pst.setInt(6,score);
      int i = pst.executeUpdate();
      if(i!=0){
        out.println("<br>Record has been inserted");
      }
      else{
        out.println("failed to insert the data");
      }
    }
    catch (Exception e){
      out.println(e);
    }
  }
}