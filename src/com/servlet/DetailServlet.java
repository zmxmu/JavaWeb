package com.servlet;

import com.dao.*;
import com.entity.Manifest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class DetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String buildNumber =  request.getParameter("buildNumber");
        ApkDao bigFileDao = new BigFileDao();
		ApkDao componentDao = new ComponentDao();
		ApkDao duplicateFileDao = new DuplicateFileDao();
		ApkDao methodGroupDao = new MethodGroupDao();
		ApkDao pNGFileDao = new PNGFileDao();
		ApkDao unUsedAssetDao = new UnUsedAssetDao();
		ApkDao unUsedResDao = new UnUsedResDao();
		ApkDao unZipFileDao = new UnZipFileDao();

		List<Manifest> bigFileList;
		List<Manifest> componentList;
		List<Manifest> unZipFileList;
		List<Manifest> duplicateFileList;
		List<Manifest> methodGroupList;
		List<Manifest> pNGFileList;
		List<Manifest> unUsedAssetList;
		List<Manifest> unUsedResList;

		try {
			bigFileList = bigFileDao.queryAll(buildNumber);
			componentList = componentDao.queryAll(buildNumber);
			duplicateFileList = duplicateFileDao.queryAll(buildNumber);
			methodGroupList = methodGroupDao.queryAll(buildNumber);
			pNGFileList = pNGFileDao.queryAll(buildNumber);
			unUsedAssetList = unUsedAssetDao.queryAll(buildNumber);
			unUsedResList = unUsedResDao.queryAll(buildNumber);
			unZipFileList = unZipFileDao.queryAll(buildNumber);

			request.getSession().setAttribute("bigFileList", bigFileList);
			request.getSession().setAttribute("componentList", componentList);
			request.getSession().setAttribute("duplicateFileList", duplicateFileList);
			request.getSession().setAttribute("methodGroupList", methodGroupList);
			request.getSession().setAttribute("pNGFileList", pNGFileList);
			request.getSession().setAttribute("unUsedAssetList", unUsedAssetList);
			request.getSession().setAttribute("unUsedResList", unUsedResList);
			request.getSession().setAttribute("unZipFileList", unZipFileList);

			request.getRequestDispatcher("/detail.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
