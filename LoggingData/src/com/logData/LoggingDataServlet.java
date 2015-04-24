package com.logData;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LogEventDAO;
import com.mongo.Mongo1;
import com.mongo.Mongo2;
import com.mongo.Mongo3;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.vo.LogEvent;
import com.vo.User;

/**
 * Servlet implementation class LoggingDataServlet
 */
public class LoggingDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoggingDataServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Control 4");
    
//		Mongo3 mongo = new Mongo3();
//		mongo.insertObject();
		LogEventDAO logEventDAO = new LogEventDAO();
		List<LogEvent> eventList = logEventDAO.getLogEvents();
		
		HttpSession session = request.getSession();
		session.setAttribute("eventList", eventList);
		System.out.println("eventList object :"+eventList);
		response.sendRedirect("myjsp");
		
	}
	

}
