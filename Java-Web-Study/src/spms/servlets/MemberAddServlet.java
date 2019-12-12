package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    RequestDispatcher rd=request.getRequestDispatcher("/member/MemberForm.jsp");
    rd.include(request, response);
    
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      ServletContext sc = this.getServletContext();
      conn = (Connection) sc.getAttribute("conn");
      stmt = conn.prepareStatement("INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE) VALUE(?,?,?,NOW(),NOW())");
      stmt.setString(1, request.getParameter("email"));
      stmt.setString(2, request.getParameter("name"));
      stmt.setString(3, request.getParameter("password"));
      stmt.executeUpdate();

      response.sendRedirect("list");
    } catch (Exception e) {
      RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
      request.setAttribute("error", e);
      rd.forward(request, response);
    } finally {
      try {
        if (stmt != null) {
          stmt.close();
        }
      } catch (Exception e) {
      }
    }
  }
}
