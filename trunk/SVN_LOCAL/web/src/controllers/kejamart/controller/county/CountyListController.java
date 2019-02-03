package kejamart.controller.county;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kejamart.dao.CountiesDAO;
import kejamart.helper.Settings;
import kejamart.model.Counties;

@EnableWebMvc
@Controller
public class CountyListController implements Settings{
	
	CountiesDAO countiesDAO;

	public static Logger logger = Logger.getLogger(CountyListController.class);

@RequestMapping(value = "/countylist", method = RequestMethod.GET)
public ModelAndView loadCounty() {

	ModelAndView mav = new ModelAndView();
	List<Counties> countyList = countiesDAO.getCounties();
	
    mav.addObject("countyList", countyList);
    
    return mav;
}

@RequestMapping(value = "/countylist", headers = "Accept=*/*", method = RequestMethod.GET)
public @ResponseBody
List<Counties> loadStates(@RequestParam(value = "id", required = true) int id) throws IllegalStateException {

    return countiesDAO.getCountyForId(id);
}


public CountiesDAO getCountiesDAO() {
	return countiesDAO;
}

public void setCountiesDAO(CountiesDAO countiesDAO){
	this.countiesDAO = countiesDAO;
}

}