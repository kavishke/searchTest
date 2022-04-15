
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchBook
 */
@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
			response.setContentType("text/html"); 
			HttpSession session = request.getSession(true);
			List booklist=new ArrayList();
			Connection con = null;

			String url = "jdbc:mysql://localhost:3306/";
			String db = "searchbook";
			String driver = "com.mysql.jdbc.Driver";
			String user = "root";
			String pass = "";

			String isbncode="";
			String bookcategory="";
			isbncode=request.getParameter("isbncode");
			bookcategory=request.getParameter("bookcategories");
			String sqlqueary="SELECT * FROM book WHERE book_name LIKE '%%' ";
			if(isbncode!=null && !(isbncode.equals(""))){
			sqlqueary+=" and isbn_code='"+isbncode+"'";
			}
			if(bookcategory!=null && !(bookcategory.equals("-1"))){
			sqlqueary+=" and category='"+bookcategory+"'";
			}

			try{
			Class.forName(driver);
			con = DriverManager.getConnection(url+db, user, pass);
			try{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlqueary);
			while (rs.next()) {
			List book=new ArrayList();
			book.add(rs.getInt(1));
			book.add(rs.getInt(2));
			book.add(rs.getString(3));
			book.add(rs.getString(4));
			booklist.add(book);
			}
			}catch (SQLException s){
			System.out.println("SQL statement is not executed!");
			}
			}
			catch (Exception e){
			e.printStackTrace();
			}
			request.setAttribute("booklist",booklist); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchbook.jsp");
			dispatcher.forward(request, response); 
			}

}
