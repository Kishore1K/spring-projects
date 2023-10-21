package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.entites.Note;
import com.helper.FactoryProvider;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response, Model m) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id").trim());
		Session session = FactoryProvider.getFactory().openSession();
		
		Note note = (Note)session.get(Note.class, id);
		System.out.println("Update");
		m.addAttribute("title", note.getTitle());
		m.addAttribute("content", note.getContent());
		
		System.out.println(note);

		response.sendRedirect("edit.jsp");
		session.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String title= request.getParameter("title");
			String content =request.getParameter("content");
			String date = request.getParameter("date");
			System.out.println("Title ->"+title);
			System.out.println("Content ->"+content);
			
			Note note = new Note();
			
			note.setId(id);
			note.setContent(content);
			note.setTitle(title);
			note.setAddedDate(new Date());
			System.out.println(note);
			Session session = FactoryProvider.getFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(note);

			tx.commit();


//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.addObject("msg", "Data Added Successfully");
//			modelAndView.setViewName("add_note");
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
