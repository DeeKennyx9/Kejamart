package kejamart.controller.propertypic;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kejamart.controller.main.UserSession;
import kejamart.dao.PropertyPicDAO;
import kejamart.helper.Settings;
import kejamart.model.PropertyPics;

//@EnableWebMvc
@Controller
public class DeletePicController implements Settings{

	PropertyPicDAO propertyPicDAO;
	
	UserSession userSession = new UserSession();
	
	public static Logger logger = Logger.getLogger(DeletePicController.class);		
	
	
	@RequestMapping(value="/deletepics", method=RequestMethod.POST)
    public String deletePic(Model model, HttpServletRequest request, @RequestParam CommonsMultipartFile file) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("id"));
    	String filename = file.getOriginalFilename();
    	
        //String imgPath =  "/KejaniGallery";
        String imgPath2 =  "D:/kejani/KejaniGallery";
        //String imgPath2 =  "/kejani/KejaniGallery";                
        String separator = "/";   
        
        File fil = new File(imgPath2+separator+filename);
        
        System.out.println("Directory check 1: " +fil);

    	System.out.println("Delete button clicked: ");

    	//Delete record from database
    	
    	propertyPicDAO.removePic(id);
    	
    	//Delete file from disk
    	
    	PropertyPics propertyPics = propertyPicDAO.getPicById(id);
    	
    	System.out.println("Directory check 2: " +propertyPics.getFileName());
    	
    	if (filename == (propertyPics.getFileName())) {
            	
    	fil.delete();
    	
	    }

    	System.out.println("Picture Deleted for user:  >|< " +id);
    	
        return "redirect:" + "picturelist.html";
        
    }	


	public PropertyPicDAO getPropertyPicDAO() {
		return propertyPicDAO;
	}
	
	public void setPropertyPicDAO(PropertyPicDAO propertyPicDAO){
		this.propertyPicDAO = propertyPicDAO;
	}
}