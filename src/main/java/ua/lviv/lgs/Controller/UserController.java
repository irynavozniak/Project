package ua.lviv.lgs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FacultyService facultyService;
	
	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User() );
		return "registration";
	}
	
	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "/registration";
		userService.save(user);
		return "redirect:/home";
	}
	
	@GetMapping(value = {"/", "/login"})
	public String login(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("faculties", facultyService.getAllFaculties());
		return mav;
	}

}
