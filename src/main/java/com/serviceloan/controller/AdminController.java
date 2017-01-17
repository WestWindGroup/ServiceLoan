package com.serviceloan.controller;


import com.serviceloan.model.*;
import com.serviceloan.service.*;
import com.serviceloan.validator.UserEditValidator;
import com.serviceloan.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for Admin's pages
 *
 * @author Eugene Artemrnko
 */

@Controller
public class AdminController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RateInterestService interestService;

    @Autowired
    private CreditStatusService statusService;

    @Autowired
    private CreditTypeService typeService;

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @Autowired
    private UserEditValidator userEditValidator;



    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin/adminHome";
    }

    @RequestMapping(value = "/admin/listRoles", method = RequestMethod.GET)
    public String listRoles(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("listRoles", this.roleService.getAll());

        return "admin/role/listRoles";
    }

    @RequestMapping(value = "/admin/addRole", method = RequestMethod.GET)
    public String addRoleGet(Model model) {
        Role role = new Role();
//        role.setName(" ");
        model.addAttribute("role", role);

        return "admin/role/addRole";
    }

    @RequestMapping(value = "/admin/addRole", method = RequestMethod.POST)
    public ModelAndView addRolePost(@ModelAttribute(name = "role")Role role, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {

            modelAndView.setViewName("admin/role/addRole");
            modelAndView.addObject("role", role);
            return modelAndView;
        }
        roleService.save(role);

        modelAndView.setViewName("admin/role/listRoles");
        modelAndView.addObject("listRoles", this.roleService.getAll());
        modelAndView.addObject("role", new Role());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.getAll());

        return "admin/user/listUsers";
    }

    @ModelAttribute("userForm")
    public User createUser(){
        return new User();
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        model.addAttribute("userForm", new User());

        return "admin/user/addUser";
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userRegistrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/user/addUser";
        }

        userService.save(userForm);

        return "admin/user/listUsers";
    }


    @RequestMapping(value = "/admin/listRates", method = RequestMethod.GET)
    public String listRates(Model model) {
        model.addAttribute("rate", new RateInterest());
        model.addAttribute("listRates", this.interestService.getAll());

        return "admin/rateInterest/listRates";
    }

    @RequestMapping(value = "/admin/listStatuses", method = RequestMethod.GET)
    public String listStatuses(Model model) {
        model.addAttribute("status", new CreditStatus());
        model.addAttribute("listStatuses", this.statusService.getAll());

        return "admin/creditStatus/listStatuses";
    }

    @RequestMapping(value = "/admin/listTypes", method = RequestMethod.GET)
    public String listTypes(Model model) {
        model.addAttribute("type", new CreditType());
        model.addAttribute("listTypes", this.typeService.getAll());

        return "admin/creditType/listTypes";
    }
}
