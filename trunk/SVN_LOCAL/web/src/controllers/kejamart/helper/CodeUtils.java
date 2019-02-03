package kejamart.helper;

import java.util.Random;

/**
 * 
 * Generate activation code gotten from username and random number
 * 
 */
public class CodeUtils {
	
	/**
	 * Random number generator
	 * @return random number String
	 */
	public String randomNumberGenerator() {
		String str = new String("1234567890");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= (str.length()); i++) {
			te = r.nextInt(i);
			sb.append(str.charAt(te));
		}
		return sb.substring(5).toString();
	}

	/**
	 * Read user email and return random string generated from email
	 * @param email
	 * @return
	 */
	public String randomNameGenerator(String email) {
		String str = new String(email);
		StringBuffer sb = new StringBuffer();
		String f = null;
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= (str.length() / 3); i++) {
			te = r.nextInt(i);
			sb.append(str.charAt(te));
			String b = sb.toString().replaceAll("-", "");
			f = b.trim();
		}
		return f.toUpperCase();
	}

	public String generateCode(String email) {
		CodeUtils a = new CodeUtils();
		String result = a.randomNumberGenerator();
		String remail = a.randomNameGenerator(email);
		return (result + remail).toString();
	}

	public static void main(String st[]) {
		CodeUtils a = new CodeUtils();
		String g = a.generateCode("Paul Kevin") + a.generateCode("p@email.com");
		System.out.println(g);
		
	}
}