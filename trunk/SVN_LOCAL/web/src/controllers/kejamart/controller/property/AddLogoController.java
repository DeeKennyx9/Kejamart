package kejamart.controller.property;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import kejamart.dao.PropertyDAO;
import kejamart.helper.Settings;
import kejamart.model.Property;

@Controller
public class AddLogoController implements Settings {

	PropertyDAO propertyDAO;
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(AddLogoController.class);	
	
	@RequestMapping(value="/propertylogo")
	public ModelAndView listProperty(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("logos");
		
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
		
		String broom = request.getParameter("bedrooms");
		request.getSession().setAttribute(PROPERTY_UNIT, broom);		
		
		System.out.println("ID " +idValue);
		System.out.println("Name" +str);

		if(propertyDAO.getPropertyForId(idz) != null) {
			
		List<Property> property = propertyDAO.getPropertyForId(idz);
		List<Property> picsByProperty = propertyDAO.getPropertyForId(idz);
		
		if (propertyDAO.getPropertyForId(idz).listIterator().hasNext()) {
		
		System.out.println("Property ..." + " || " +idz);
		
		modelAndView.addObject("picsByProperty", picsByProperty);
		modelAndView.addObject("property", property);
		
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
		 
	@RequestMapping(value="/propertylogo", method=RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute(value="property") Property property,
			@RequestParam CommonsMultipartFile file) throws Exception {
          
		System.out.println("Add Button clicked");
		int idp = request.getSession().getAttribute(PROPERTY_DETAILS).hashCode();
		int id = Integer.parseInt(request.getParameter("id"));
		int profValue = 0;
		profValue = request.getSession().getAttribute(LOGIN_DETAILS).hashCode();
		String pp = request.getSession().getAttribute(PROPERTY_NAME).toString();
		
		// Create Path
		Property pr = propertyDAO.getPropertyById(idp);
		String pu = pr.getBedrooms();
		String prp = pp+pu;
		
		System.out.println("Main Pic Path " + prp + " _ " + pu);
				
		System.out.println("Property Details | Login Details | Property Name :" +idp+"||"+profValue+"||"+prp);
		
		String filename = file.getOriginalFilename();          
		List<Property> pc = propertyDAO.getPropertyForId(idp);
		int totPics = 0;
		//String propertyName = prp;	   
		       
                String imgPath =  "/KejaniGallery";
                String imgPath2 =  "D:/kejani/KejaniGallery";                
                //String imgPath2 =  "/kejani/KejaniGallery";
                String separator = "/";   
                
                if(totPics < 1) {	
         	    
         	   File dir = new File(imgPath2+separator+prp+separator);
				if (!dir.exists()) {
					dir.mkdirs();					
					System.out.println("Path created: " +imgPath2+separator+prp+separator);					
				} else {					
					System.out.println("Path exists: "  +imgPath2+separator+prp+separator);
				}
         	    
	      	       byte barr[] = file.getBytes();        	        
	      	       int y = barr.length;
	      	       int x = 4999999;
	      	       System.out.println("Picture size ... " +y); 	  
         	    
         	    String pathstring = imgPath+separator+prp+separator+filename;
         	    
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
         	    
             	property.setFileName(filename);
            	property.setLogo(pathstring);
            	propertyDAO.updateLogo(property, id);
            	
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
  
        return "logos";
    }
	
	public PropertyDAO getPropertyDAO() {
		return propertyDAO;
	}
	
	public void setPropertyDAO(PropertyDAO propertyDAO){
		this.propertyDAO = propertyDAO;
	}
}

