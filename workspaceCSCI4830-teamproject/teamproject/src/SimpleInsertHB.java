import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;
import util.UtilDB;
import util.UtilFile;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }
   
   int i = 0;
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	   
	  int id = Integer.parseInt(request.getParameter("id").trim());
	  String firstName = request.getParameter("firstName").trim();
      String lastName = request.getParameter("lastName").trim();
	  String address = request.getParameter("address").trim();
	  String date = request.getParameter("date").trim();
	  String time = request.getParameter("time").trim();
	  String details = request.getParameter("details").trim();
      UtilDB.createAppointments(id, firstName, lastName, address, date, time, details);

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String DBTitle = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + DBTitle + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + DBTitle + "</h1>\n");
      out.println("<ul>");
      out.println("<li> First Name: " + firstName);
      out.println("<li> Last Name: " + lastName);
      out.println("<li> Address: " + address);
      out.println("<li> Date: " + date);
      out.println("<li> Time: " + time);
      out.println("<li> Details: " + details);

      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}