package kejamart.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import kejamart.helper.PasswordFile;

public class AdminLoginUtils {

	PasswordFile p = new PasswordFile();
	public static Logger logger = Logger.getLogger(AdminLoginUtils.class);

	@SuppressWarnings("static-access")
	String adminDirectory = p.getPasswordDirectory();

	public static String translateToMD5(String password) {
		String newPassword = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			newPassword = new String(digest.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String newStr = newPassword.replaceAll("\n", "");
		return itrim(newStr);
	}
	
	public static String tdmintranslateToMD5(String password) {
		String newPassword = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] array = digest.digest(password.getBytes());
	        String hash = "";
	        for(byte b : array){
	            hash+= ((int)b)+"";
	        }
			newPassword = hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return newPassword;
	}

	public static void main(String [] args){
		System.out.println(tdmintranslateToMD5("123456"));
	}
	/* remove leading whitespace */
    public static String ltrim(String source) {
        return source.replaceAll("^\\s+", "");
    }

    /* remove trailing whitespace */
    public static String rtrim(String source) {
        return source.replaceAll("\\s+$", "");
    }

    /* replace multiple whitespaces between words with single blank */
    public static String itrim(String source) {
        return source.replaceAll("\\b\\s{2,}\\b", " ");
    }

    /* remove all superfluous whitespaces in source string */
    public static String trim(String source) {
        return itrim(ltrim(rtrim(source)));
    }

    public static String lrtrim(String source){
        return ltrim(rtrim(source));
    }

   /* public static void main(String[] args){
        String oldStr =
         ">     <1-\n2-1-2-1-2-1-2-1-2-1-----2-1-2-1-2-1-2-1-2-1-2-1-2>   <";
        String newStr = oldStr.replaceAll("-", " ");
        
        System.out.println(itrim(newStr));
        
    }*/

	public void createAdminPassword(String content) throws IOException {
		createFile(adminDirectory, translateToMD5(content));
	}

	public void createOrAppendFile(File f, String text) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		bw.write(text);
		bw.newLine();
		bw.flush();
		bw.close();
	}

	public void createFile(String fileName, String content)
			throws IOException {
		File f = new File(fileName);
		createOrAppendFile(f, content);
	}

	/**
	 * Read text file
	 * 
	 * @return
	 */
	public String readTextFile(){
		FileInputStream fstream;
		String outputPassword = "";
		try {
			fstream = new FileInputStream(adminDirectory);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		    	outputPassword += strLine;
		    }
		    in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return outputPassword;
	}

	public void createDocTextFile(String fileName) throws IOException {
		// path to create
		FileWriter ryt = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(ryt);
		out.write("");
		out.close();
	}
}
