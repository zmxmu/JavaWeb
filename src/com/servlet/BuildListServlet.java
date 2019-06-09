package com.servlet;

import com.dao.ApkDao;
import com.dao.ManifestDao;
import com.entity.Manifest;
import com.result.TaskResult;
import com.task.ApkTask;
import com.task.TaskFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class BuildListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApkDao dao = new ManifestDao();
		List<Manifest> buildList;
		try {
			buildList = dao.queryAll();
			request.getSession().setAttribute("buildList", buildList);
			request.getRequestDispatcher("/buildList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
