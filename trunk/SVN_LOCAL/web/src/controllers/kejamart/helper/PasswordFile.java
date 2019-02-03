package kejamart.helper;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import kejamart.helper.Settings;

/**
 * 
 * Gets root folder of application Used when doing a file
 *         upload to the root folder set
 * 
 */
public class PasswordFile implements Settings {

	private static ApplicationContext ctx;

	private static WebApplicationContext webApplicationContex;

	private static ServletContext webctx;

	private static Properties mime;

	private static String passwordDirectory = null;

	public static String fileSeparator = File.separator;

	/**
	 * 
	 * @param cctx
	 */
	public static void setFSContextValues(String cctx) {
		passwordDirectory = cctx;
	}

	/**
	 * 
	 * @return webctx
	 */
	public static String getSystemDirectoryFromWebContext() {
		return ((String) webctx.getAttribute(ROOTPASSWORD));
	}

	/**
	 * 
	 * @return ctx
	 */
	public static ApplicationContext getApplicationContext() {
		if (ctx != null)
			return ctx;
		throw new RuntimeException("\n--------------------------------\n"
				+ "\n\nContext is not set. \nFrom:"
				+ PasswordFile.class.getCanonicalName());
	}

	/**
	 * 
	 * @param ctx
	 */
	public static void setApplicationContext(ApplicationContext ctx) {
		PasswordFile.ctx = ctx;
	}

	public static ServletContext getWebContext() {
		return webctx;
	}

	public static WebApplicationContext getWebApplicationContex() {
		return webApplicationContex;
	}

	/**
	 * @return the passwordDirectory
	 */
	public static String getPasswordDirectory() {
		return passwordDirectory;
	}

	/**
	 * @param passwordDirectory the passwordDirectory to set
	 */
	public static void setPasswordDirectory(String passwordDirectory) {
		PasswordFile.passwordDirectory = passwordDirectory;
	}

	public static Properties getMime() {
		return mime;
	}
}
