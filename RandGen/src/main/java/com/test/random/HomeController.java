package com.test.random;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles requests for the application
 */
@Controller
public class HomeController {
	
	RandomService randomService;
	
	 @Autowired
	 public HomeController(RandomService randomService) {
		 this.randomService = randomService;
	 }
	
	 
	@RequestMapping(value = "/rangegeneration", headers="Accept=*/*", method = RequestMethod.GET)
	//@RequestMapping("/rangegeneration")
    @ResponseBody
    public Integer genByRange(@RequestParam Integer min, @RequestParam Integer max, @RequestParam Integer seed) {
        return randomService.genByRange(min, max, seed);
    }
	
	
	@RequestMapping("/profilegeneration")
    @ResponseBody
    public Integer genByProfile(@RequestParam Integer profileid) {
        return randomService.genByProfile(profileid);
    }
	
	
	@RequestMapping("/newprofile")
    @ResponseBody
    public int newProfile(@RequestParam Integer min, @RequestParam Integer max, @RequestParam Integer seed) {
		int profileID = randomService.createProfile(min, max, seed);
        return profileID;
    }
	
	
	@RequestMapping("/delprofile")
    public String deleteProfile(@RequestParam Integer profileid) {
		randomService.deleteProfile(profileid);
        return "deleted";
    }
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "home";
    }
	

}
