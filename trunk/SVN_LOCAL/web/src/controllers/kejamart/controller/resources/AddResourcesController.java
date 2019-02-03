package kejamart.controller.resources;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kejamart.controller.main.UserSession;
import kejamart.dao.ResourcesDAO;
import kejamart.helper.Settings;
import kejamart.model.Property;
import kejamart.model.Resources;

@Controller
public class AddResourcesController implements Settings {

	ResourcesDAO resourcesDAO;
	UserSession userSession = new UserSession();

	public static Logger logger = Logger.getLogger(AddResourcesController.class);

	@RequestMapping(value="/resources")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {

		ModelAndView modelAndView = new ModelAndView("resource");

		request.getSession().setAttribute("errmg", "");
		request.getSession().setAttribute("infomg", "");

		if (request.getSession().getAttribute(SESSION_USER) != null){

		int idz = 0;
		idz = Integer.parseInt(request.getParameter("id"));
		String pname = request.getParameter("name");

		int idValue = 0;
		idValue = idz;
		request.getSession().setAttribute(PROPERTY_DETAILS, idValue);

		String str = "";
		str = pname;
		request.getSession().setAttribute(PROPERTY_NAME, str);

		System.out.println("ID " +idValue);
		System.out.println("Name" +str);

		String broom = request.getParameter("bedrooms");
		request.getSession().setAttribute(PROPERTY_UNIT, broom);

		if(resourcesDAO.getResourcesForPropId(idz) != null) {

		List<Resources> resources = resourcesDAO.getResourcesForId(idz);
		List<Resources> resourcesByProperty = resourcesDAO.getResourcesForPropId(idz);

		if (resourcesDAO.getResourcesForPropId(idz).listIterator().hasNext()) {

		System.out.println("Property ..." + " || " +idz);

		modelAndView.addObject("resourcesByProperty", resourcesByProperty);
		modelAndView.addObject("resources", resources);

		} else {

			System.out.println("No Images Found");
		}

		} else {
			System.out.println("No Records Found");
		}

		System.out.println("User authorized to access add property page");
	    return modelAndView;

		} else {

		ModelAndView model = new ModelAndView("restricted");
		System.out.println("Visitor NOT authorized to access property page");

		return model;

		}

	}

	@RequestMapping(value="/resources", method=RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="resources") Resources resources,
			@RequestParam CommonsMultipartFile file) throws Exception {

		System.out.println("Add Button clicked");
		int idp = 0;
		idp = request.getSession().getAttribute(PROPERTY_DETAILS).hashCode();
		int profValue = 0;
		profValue = request.getSession().getAttribute(LOGIN_DETAILS).hashCode();

		String pp = request.getSession().getAttribute(PROPERTY_NAME).toString();

		// Create Path
		String pu = request.getSession().getAttribute(PROPERTY_UNIT).toString();
		String prp = pp+pu;

		System.out.println("Main Pic Path " + prp + " _ " + pu);

		Date date = new Date();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dat = new java.sql.Date(date.getTime());
		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
		String filename = file.getOriginalFilename();
		boolean checkFileName = resourcesDAO.checkFileName(filename);
		List<Resources> pc = resourcesDAO.getResourcesForPropId(idp);
		int totPics =0;
		//totPics = pc.size();
		totPics = pc.size();
		//String propertyName = prp;

		       if(totPics < 8) {

                String imgPath =  "/KejaniResources";
                String imgPath2 =  "D:/kejani/KejaniResources";
                //String imgPath2 =  "/kejani/KejaniResources";
                String separator = "/";

         	   File dir = new File(imgPath2+separator+prp+separator);
				if (!dir.exists()) {
					dir.mkdirs();
					System.out.println("Path created: " +imgPath2+separator+prp+separator);
				} else {
					System.out.println("Path exists: " +imgPath2+separator+prp+separator);
				}

      	       byte barr[] = file.getBytes();
      	       int y = barr.length;
      	       int x = 4999999;
      	       System.out.println("Picture size ... " +y);

         	   if  (filename != "") {

             	   byte barx[] = file.getBytes();
              	   int a = barx.length;
              	   int b = 4999999;
              	   System.out.println("Picture size ... " +a);

            	   BufferedOutputStream bout = new BufferedOutputStream(
            	              new FileOutputStream(imgPath2+separator+prp+separator+filename));

             	   bout.write(barr);
             	   System.out.println("Picture Saved in folder ... " +filename);

            	   bout.flush();
             	   bout.close();

                resources.setFileName(filename);
                resources.setProfileId(profValue);
            	resources.setPropertyId(idp);
            	resources.setPropertyName(prp);
            	resources.setEmail(emailUser);
            	resources.setPath(imgPath+separator+prp+separator+filename);
            	//resources.setPath(imgPath2+separator+prp+separator+filename);
            	resources.setCreatedDate(dat);
            	resourcesDAO.addResource(resources);

            	System.out.println("Picture Saved in database ... " +filename);
            	request.getSession().setAttribute("infomg", "&nbsp;Picture Saved in database ... " +filename);

     	       } else {

                System.err.println("Please select a photo to upload ... ");
                request.getSession().setAttribute("errmg", "&nbsp;Please select a photo to upload ...");

     	       }  if ((y > x)) {

                System.err.println("Picture is too large. Above 5 MB not allowed ... " +barr.length);
                request.getSession().setAttribute("errmg", "&nbsp;Picture is too large. Above 5 MB not allowed ... " +barr.length + " KB's");

     	       }

		       } else {

		    	request.getSession().setAttribute("errmg", "&nbsp;Maximum pictures uploaded for property: 12 Max<p><br>");
		    	System.out.println("Maximum pictures uploaded for property: 12 Max");

		       }

        return "resource";
    }

	public ResourcesDAO getResourcesDAO() {
		return resourcesDAO;
	}

	public void setResourcesDAO(ResourcesDAO resourcesDAO){
		this.resourcesDAO = resourcesDAO;
	}
}

