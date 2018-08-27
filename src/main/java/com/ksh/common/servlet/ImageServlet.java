package com.ksh.common.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ksh.core.util.Util;

@WebServlet(name = "ImageServlet", urlPatterns = "/images/*")

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// Get image file.

			String fileName = request.getParameter("fileName");
//
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(Util.getResourceFolderPath() + fileName));

			// Get image contents.
			byte[] bytes = new byte[in.available()];

			in.read(bytes);
			in.close();

			// Write image contents to response.
			response.getOutputStream().write(bytes);

			////////////////////////////////////////////////////////
//			File imageFile = new File(Util.getResourceFolderPath() + fileName);
//			BufferedImage image = ImageIO.read(imageFile);
//			ImageIO.write(image, "image/png", response.getOutputStream());
			////////////////////////////////////////////////////////
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
