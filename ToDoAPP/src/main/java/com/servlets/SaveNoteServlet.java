package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entites.Note;
import com.helper.FactoryProvider;

/**
 * Servlet implementation class SaveNoteServlet
 */
public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SaveNoteServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title= request.getParameter("title");
			String content =request.getParameter("content");
			System.out.println("Title ->"+title);
			System.out.println("Content ->"+content);
			
			Note note = new Note(title, content, new Date());
			System.out.println(note);
			Session session = FactoryProvider.getFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.save(note);
			tx.commit();

			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.println("<h1 style='text-align:center;'>Notes Added SuccessFully</h1>");
			writer.println("<h1 style='text-align:center;'><a href='show_notes.jsp'>Click Here to See All Notes</a></h1>");

			session.close();

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
