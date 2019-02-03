package kejamart.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import kejamart.helper.RootFolder;

/**
 * 
 * Create log for each page. Note, will not work for pages that have other pages
 * included in them
 *
 */
public class RequestTimerFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = -8918258746329598151L;
	@SuppressWarnings("unused")
	private FilterConfig filterConfig;
	RootFolder rootFolder = new RootFolder();

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	
	public String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());

	}

	@SuppressWarnings("static-access")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			long startTime = System.currentTimeMillis();
			filterChain.doFilter(request, response);
			long endTime = System.currentTimeMillis();
			String requestURI = ((HttpServletRequest) request).getRequestURI();
			String output = "IP: "+ request.getRemoteAddr() + "  " + now("yyyy.MMMMM.dd z hh:mm aaa") +" : " + requestURI + " took -> " + (endTime - startTime)
					+ " ms." ;
			System.out.println(output);
			//createTextFiles("webpagelogs", rootFolder.getSystemDirectory() + "//logs",
				//	output);
		} catch (ServletException sx) {
			log(sx.getMessage());
			return;
		} catch (IOException iox) {
			log(iox.getMessage());
			return;
		}
	}
	
	public void createOrAppendFile (File f, String text) throws IOException {
		 BufferedWriter bw = new BufferedWriter (new FileWriter (f, true));
		 bw.write (text);
		 bw.newLine();
		 bw.flush();
		 bw.close();
	}

	public void createTextFiles(String fileName, String folderPath,
			String content) throws IOException {
		File f = new File(folderPath + "\\" +fileName);
		createOrAppendFile(f, content);
	}
}
