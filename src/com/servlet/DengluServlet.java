package com.servlet;

import com.result.TaskResult;
import com.task.ApkTask;
import com.task.TaskFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class DengluServlet extends HttpServlet {
	public DengluServlet(){
		executor = Executors.newFixedThreadPool(1);
	}
	private ExecutorService executor;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int taskType = Integer.parseInt(request.getParameter("taskType"));
		String param = request.getParameter("param");
		ApkTask apkTask = TaskFactory.factory(taskType,param);
		apkTask.init();
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(apkTask!=null){
			try {
				Future<TaskResult> result= executor.submit(apkTask);
				response.getWriter().write(result.get().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		else{
			response.getWriter().write("SKIP!");
		}


//		if(ud.login(taskType, param)){
//			request.setAttribute("xiaoxi", "欢迎用户"+taskType);
//			request.getRequestDispatcher("/success.jsp").forward(request, response);
//		}else{
//			response.sendRedirect("index.jsp");
//		}


	}

}
