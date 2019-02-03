package kejamart.controller.propertypic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import kejamart.dao.PropertyPicDAO;
import kejamart.helper.Settings;
import kejamart.model.PropertyPics;

@Controller
public class AddPropertyPicController implements Settings {

	PropertyPicDAO propertyPicDAO;
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(AddPropertyPicController.class);	
	
	@RequestMapping(value="/propertypics")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("propic");
		
		request.getSession().setAttribute("errmg", "");
		request.getSession().setAttribute("infomg", "");		

		if (request.getSession().getAttribute(SESSION_USER) != null){

		int idz = (Integer.parseInt(request.getParameter("id")));
		String pname = request.getParameter("name");
			
		int idValue = idz;
		request.getSession().setAttribute(PROPERTY_DETAILS, idValue);
		
		String str = "";
		str = pname;
		request.getSession().setAttribute(PROPERTY_NAME, str);	
		
		System.out.println("ID " +idValue);
		System.out.println("Name" +str);
		
		String broom = request.getParameter("bedrooms");
		request.getSession().setAttribute(PROPERTY_UNIT, broom);		

		if(propertyPicDAO.getPropertyPicsForPropId(idz) != null) {
			
		List<PropertyPics> propertyPics = propertyPicDAO.getPropertyPicsForId(idz);
		List<PropertyPics> picsByProperty = propertyPicDAO.getPropertyPicsForPropId(idz);
		
		if (propertyPicDAO.getPropertyPicsForPropId(idz).listIterator().hasNext()) {
		
		//String picName = propertyPicDAO.getPropertyPicsForPropId(idz).listIterator().next().getFileName();
		
		System.out.println("Property ..." + " || " +idz);
		
		modelAndView.addObject("picsByProperty", picsByProperty);
		modelAndView.addObject("propertyPics", propertyPics);
		
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
		 
	@RequestMapping(value="/propertypics", method=RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="propertyPics") PropertyPics propertyPics,
			@RequestParam CommonsMultipartFile file) throws Exception {
          
		System.out.println("Add Button clicked");
		int idp = 0;
		idp = (request.getSession().getAttribute(PROPERTY_DETAILS).hashCode());
		int profValue = 0;
		profValue = (request.getSession().getAttribute(LOGIN_DETAILS).hashCode());
		String pp = request.getSession().getAttribute(PROPERTY_NAME).toString();
		
		// Create Path
		String pu = request.getSession().getAttribute(PROPERTY_UNIT).toString();
		String prp = pp+pu;
		
		
		Date date = new Date();
		SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dat = new java.sql.Date(date.getTime());
		String emailUser = request.getSession().getAttribute(SESSION_USER).toString();
		String filename = file.getOriginalFilename();          
		boolean checkFileName = propertyPicDAO.checkFileName(filename);
		List<PropertyPics> pc = propertyPicDAO.getPropertyPicsForPropId(idp);
		int totPics = 0;
		totPics = pc.size();
		//String propertyName = prp;
		       
		       if(totPics < 12) {		    	   
		       
                String imgPath =  "/KejaniGallery";
                String imgPath2 =  "D:/kejani/KejaniGallery";
                //String imgPath2 =  "/kejani/KejaniGallery";                
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
				
         	   if  (filename != "" && (x > y)) {
         	   
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
         		  
         	    propertyPics.setFileName(filename);
                propertyPics.setProfileId(profValue);
            	propertyPics.setPropertyId(idp);
            	propertyPics.setPropertyName(prp);
            	propertyPics.setEmail(emailUser);	
            	propertyPics.setPath(imgPath+separator+prp+separator+filename);
            	propertyPics.setCreatedDate(dat); 
            	propertyPics.setStatus("0");
            	propertyPicDAO.addPropertyPic(propertyPics); 
            	
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
  
        return "propic";
    }
	
	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
}

