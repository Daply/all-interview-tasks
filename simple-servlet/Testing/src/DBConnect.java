import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect{

    public String findClass(HttpServletRequest request, HttpServletResponse response, String keyword) throws IOException, ServletException{
        Connection con = null;
        Statement stmt = null;
        ResultSet classes = null;
        ResultSet keywords = null;
        int numberOfClass = 0;
        String classToFind = "";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection ("jdbc:mysql://localhost/classes", "root", "root");
            stmt = con.createStatement();
            keywords = stmt.executeQuery("select * from classes.keywords");
            while(keywords.next()) {
            	if(keywords.getString("keyword").equals(keyword))
            		numberOfClass = keywords.getInt("binded_class");
            }
            classes = stmt.executeQuery("select * from classes.classes");
            while(classes.next()) {
            	if(classes.getInt("id") == numberOfClass)
            		classToFind = classes.getString("classname");
            }
        } catch (SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        }
        finally {
            try  {
             if(classes != null) {
                 classes.close();
                 classes = null;
             }
             if(keywords != null) {
                 keywords.close();
                 keywords = null;
             }
             if(stmt != null) {
                 stmt.close();
                 stmt = null;
             }
             if(con != null) {
                 con.close();
                 con = null;
             }
            } catch (SQLException e) {}
        }
        return classToFind;
    }
}
