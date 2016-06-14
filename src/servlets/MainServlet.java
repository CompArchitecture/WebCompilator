package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * Created by andro on 6/13/16.
 */
@WebServlet("/zaza")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
		maxFileSize=1024*1024*10,      // 10MB
		maxRequestSize=1024*1024*50)
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String appPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String savePath = appPath + File.separator;

		// creates the save directory if it does not exists
//		File fileSaveDir = new File(savePath);
//		if (!fileSaveDir.exists()) {
//			noinspection ResultOfMethodCallIgnored
//			fileSaveDir.mkdir();
//		}

		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			part.write(savePath + File.separator + fileName);
		}

		request.setAttribute("message", "Upload has been done successfully!");
		getServletContext().getRequestDispatcher("/message.jsp").forward(
				request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("received get message");
    }

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}
