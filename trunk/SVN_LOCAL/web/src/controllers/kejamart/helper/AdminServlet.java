package kejamart.helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Redirect servlet
 *
 */
@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		String contextPath = request.getContextPath();
		response.sendRedirect(response.encodeRedirectURL(contextPath
				+ "/admin.html"));
	}
}