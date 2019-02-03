package kejamart.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unchecked")
public class LoginFilterx implements Filter {
	protected FilterConfig filterConfig;

	List revokeList;

	/**
	 * init() : init() method called when the filter is instantiated. This
	 * filter is instantiated the first time j_security_check is invoked for the
	 * application (When a protected servlet in the application is accessed).
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		// read revoked user list
		revokeList = new ArrayList();
		readConfig();
	}

	/**
	 * destroy() : destroy() method called when the filter is taken out of
	 * service.
	 */
	public void destroy() {
		this.filterConfig = null;
		revokeList = null;
	}

	/**
	 * doFilter() : doFilter() method called before the servlet to which this
	 * filter is mapped is invoked. Since this filter is mapped to
	 * j_security_check,this method is called before j_security_check action is
	 * posted.
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws java.io.IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// pre login action

		// get username
		String username = req.getParameter("j_username");

		// if user is in revoked list send error
		if (revokeList.contains(username)) {
			res
					.sendError(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// call next filter in the chain : let j_security_check authenticate
		// user
		chain.doFilter(request, response);

		// post login action

	}

	/**
	 * readConfig() : Reads revoked user list file and creates a revoked user
	 * list.
	 */
	private void readConfig() {
		if (filterConfig != null) {

			// get the revoked user list file and open it.
			BufferedReader in;
			try {
				String filename = filterConfig.getInitParameter("RevokedUsers");
				in = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException fnfe) {
				return;
			}

			// read all the revoked users and add to revokeList.
			String userName;
			try {
				while ((userName = in.readLine()) != null)
					revokeList.add(userName);
			} catch (IOException ioe) {
			}

		}

	}

}
