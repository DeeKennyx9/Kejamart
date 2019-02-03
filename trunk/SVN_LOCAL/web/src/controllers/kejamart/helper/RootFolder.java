package kejamart.helper;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import kejamart.helper.Settings;

public class RootFolder implements Settings {

	private static ApplicationContext ctx;

	private static WebApplicationContext webApplicationContex;

	private static ServletContext webctx;

	private static Properties mime;

	private static String systemDirectory = null;

	public static String fileSeparator = File.separator;

	/**
	 * 
	 * @param cctx
	 */
	public static void setFSContextValues(String cctx) {
		systemDirectory = cctx;
	}

	/**
	 * 
	 * @return webctx
	 */
	public static String getSystemDirectoryFromWebContext() {
		return ((String) webctx.getAttribute(SYSTEMDIRECTORY));
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
				+ RootFolder.class.getCanonicalName());
	}

	/**
	 * 
	 * @param ctx
	 */
	public static void setApplicationContext(ApplicationContext ctx) {
		RootFolder.ctx = ctx;
	}

	public static ServletContext getWebContext() {
		return webctx;
	}

	public static WebApplicationContext getWebApplicationContex() {
		return webApplicationContex;
	}

	public static String getSystemDirectory() {
		return systemDirectory;
	}

	public static void setSystemDirectory(String systemDirectory) {
		RootFolder.systemDirectory = systemDirectory;
	}

	public static Properties getMime() {
		return mime;
	}
}
