import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.Appointment;
import util.Info;
import util.UtilDB;

@WebServlet("/SimpleSearchHB")
public class SimpleSearchHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleSearchHB() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keywordTitle").trim();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");

      List<Appointment> listBooks = null;
      if (keyword != null && !keyword.isEmpty()) {
    	  listBooks = UtilDB.listAppointments(keyword);
      } else {
    	  listBooks = UtilDB.listAppointments();
      }
      display(listBooks, out);
      out.println("</ul>");
      out.println("<a href=/" + projectName + "/" + searchWebName + ">Search Data</a> <br>");
      out.println("</body></html>");
   }

   void display(List<Appointment> appointmentList, PrintWriter out) {
      for (Appointment appointment : appointmentList) {
         System.out.println("[DBG] " + appointment.getId() + ", " //
    		 + appointment.getFirstName() + ", " //
             + appointment.getLastName() + ", " //
             + appointment.getAddress() + ", " //
             + appointment.getDate() + ", " //
             + appointment.getTime() + ", " //
           	 + appointment.getDetails());

         out.println("<li>" + appointment.getId() + ", " //
        		 + appointment.getFirstName() + ", " //
                 + appointment.getLastName() + ", " //
                 + appointment.getAddress() + ", " //
                 + appointment.getDate() + ", " //
                 + appointment.getTime() + ", " //
                 + appointment.getDetails() + "</li>");
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
