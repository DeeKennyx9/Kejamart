package kejamart.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kejamart.helper.Settings;

@SuppressWarnings("serial")
public class CaptchaServlet extends HttpServlet implements Settings{
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int width = 160;
		int height = 50;

		Random r = new Random();
		char randomno[][] = {
				{ (char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + '1'),
						(char) (r.nextInt('Z' - 'A' + 1) + '2'),
						(char) (r.nextInt('Z' - 'A' + 1) + '3'),
						(char) (r.nextInt('Z' - 'A' + 1) + '4'),
						(char) (r.nextInt('Z' - 'A' + 1) + '5'),
						(char) (r.nextInt('Z' - 'A' + 1) + '6') },
				{ (char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A') },
				{ (char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A'),
						(char) (r.nextInt('Z' - 'A' + 1) + 'A') } };

		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();

		Font font = new Font("Georgia", Font.BOLD, 13);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);
		//g2d.setBackground(Color.white);

		GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, height / 2,
			Color.white, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);

		g2d.setColor(Color.black);

		String captcha = String.copyValueOf(randomno[0]);
		request.getSession().setAttribute(CAPTCHA, captcha);

		int x = 0;
		int y = 0;

		for (int i = 0; i < randomno[0].length; i++) {
			x += 10 + (Math.abs(r.nextInt()) % 15);
			y = 20 + Math.abs(r.nextInt()) % 20;
			g2d.drawChars(randomno[0], i, 1, x, y);
		}
		g2d.dispose();

		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(bufferedImage, "png", os);
		os.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}