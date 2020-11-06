
import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Product")
public class Product extends HttpServlet{
    
        Connection con;
        PreparedStatement pst;

        
        int row;
        @Override
        public void doPost(HttpServletRequest req,HttpServletResponse rsp)throws IOException,ServletException
        {
            rsp.setContentType("text/html");
            PrintWriter out=rsp.getWriter();
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/productdb","root","konkon6789");
                String Barcode=req.getParameter("Barcode");
                String name=req.getParameter("pname");
                String color=req.getParameter("color");
                String Description=req.getParameter("Description");
                                      
                pst = con.prepareStatement("insert into data(Barcode,pname,color,Description)values(?,?,?,?)");
                pst.setString(1, Barcode);
                pst.setString(2, name);
                pst.setString(3, color);
                pst.setString(4, Description);
                pst.executeUpdate();
                out.println("<font color='green'>Record Added</font>");
            }
            catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
