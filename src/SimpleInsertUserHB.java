import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDB;

@WebServlet("/SimpleInsertUserHB")
public class SimpleInsertUserHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertUserHB() {
      super();
   }
   
   int i = 0;
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	   
	  //int id = Integer.parseInt(request.getParameter("id").trim());
	  String firstName = request.getParameter("firstName").trim();
      String lastName = request.getParameter("lastName").trim();
      UtilDB.createUsers(firstName, lastName);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String DBTitle = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + DBTitle + "</title></head>\n" + //
            "<body bgcolor=\"#2D2D2D\">\n" + //
            "<h1 align=\"center\" style=\"color:white;font-family:monospace;\">" + DBTitle + "</h1>\n");
      out.println("<ul>");
      String styling = "style=\"color:white;font-family:monospace;\"";
      //out.println("<li " + styling + "> ID Number: " + id);
      out.println("<li " + styling + "> First Name: " + firstName);
      out.println("<li " + styling + "> Last Name: " + lastName);

      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + insertUser + " style=\"color:#4286f4;font-family:monospace;\">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
