package co.grandcircus.FinalProject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.FinalProject.entity.CityAndStateInfo;
import co.grandcircus.FinalProject.entity.AutoCompleteResponse;
 

@RestController
public class AutoCompleteController {
	
	@Autowired
	ApiService apiServ;
	
@GetMapping("cityStateInfo") 
public List<String> getAutoComplete(HttpServletRequest request) { 
	String cityText = (String)request.getParameter("cityText");
	AutoCompleteResponse response = apiServ.getCityStateResponse(cityText);
	List<CityAndStateInfo> CityAndStateInfo=response.getCityAndStateInfo();
	List<String> autoCompleteOutput =new ArrayList<String>();
	
	 
 
for (CityAndStateInfo cityState : CityAndStateInfo) {
		
		autoCompleteOutput.add(cityState.getCity() +"-"+ cityState.getState());
	}
 
	return autoCompleteOutput;
} 

}
