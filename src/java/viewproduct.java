
import java.io.IOException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kostas
 */
@WebServlet("/viewproduct")
public class viewproduct extends HttpServlet{
    Connection con;
        PreparedStatement pst;
        ResultSet rs;

        
        int row;
        @Override
        public void doGet(HttpServletRequest req,HttpServletResponse rsp)throws IOException,ServletException
        {
            rsp.setContentType("text/html");
            PrintWriter out=rsp.getWriter();
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/productdb","root","konkon6789");
                String sql;
                sql="select * from data";
                Statement stmt=con.createStatement();
                rs=stmt.executeQuery(sql);
                out.println("<table cellspacing='0' width='350px' border='1'>");
                out.println("<tr>");
                    out.println("<td> Barcode </td>");
                    out.println("<td> Name </td>");
                    out.println("<td> Color </td>");
                    out.println("<td> Description </td>");
                    
               
                out.println("</ tr>");
                while(rs.next()){
                out.println("<tr>");
                    out.println("<td>" +rs.getString("Barcode")+ "</td>");
                    out.println("<td>" +rs.getString("pname")+ "</td>");
                    out.println("<td>" +rs.getString("color")+ "</td>");
                    out.println("<td>" +rs.getString("Description")+ "</td>");
                    

                out.println("</ tr>");
                    
                }
                out.println("</table>");

            }
            catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
