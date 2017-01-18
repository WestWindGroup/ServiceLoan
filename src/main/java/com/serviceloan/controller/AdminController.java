package com.serviceloan.controller;


import com.serviceloan.model.*;
import com.serviceloan.service.*;
import com.serviceloan.validator.RoleValidator;
import com.serviceloan.validator.UserEditValidator;
import com.serviceloan.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private MessageSource messageSource;


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
    public ModelAndView addRolePost(@ModelAttribute(name = "role") Role role, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        roleValidator.validate(role,bindingResult);

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

    @RequestMapping(value = "/admin/deleteRole/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRolesGet(@PathVariable long id) {

        Role role = roleService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/role/deleteRole");
        modelAndView.addObject("role", role);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteRole/{id}", method = RequestMethod.POST)
    public ModelAndView deleteRolePost(@ModelAttribute(name = "role") Role skillForm, BindingResult bindingResult,
                                       @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.getById(id);

        if (role.getUsers().size() != 0) {
            modelAndView.addObject("errorDelete",
                    messageSource.getMessage("key.delete.impossible", null, localeResolver.resolveLocale(request)));
            bindingResult.addError(null);
        }


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/role/deleteRole");
            modelAndView.addObject("role", role);
            return modelAndView;
        }

        roleService.remove(role);

        modelAndView.setViewName("admin/role/listRoles");
        modelAndView.addObject("listRoles", this.roleService.getAll());
        modelAndView.addObject("role", new Role());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editRole/{id}", method = RequestMethod.GET)
    public ModelAndView editRoleGet(@PathVariable long id) {

        Role role = roleService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/role/editRole");
        modelAndView.addObject("role", role);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editRole/{id}", method = RequestMethod.POST)
    public ModelAndView editRolePost(@ModelAttribute(name = "role")Role role,BindingResult bindingResult,
                                   @PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();

        Role roleFromDataBase = roleService.getById(id);

        if(!role.equals(roleFromDataBase)){

            roleValidator.validate(role,bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("admin/role/editRole");
                modelAndView.addObject("role", role);
                return modelAndView;
            }

            roleService.save(role);
        }

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
    public User createUser() {
        return new User();
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public String addUserGet(Model model) {
        model.addAttribute("userForm", new User());

        return "admin/user/addUser";
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ModelAndView addUserPost(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        userForm.setFirstName(new String(userForm.getFirstName().getBytes("ISO-8859-1"), "UTF-8"));
        userForm.setLastName(new String(userForm.getLastName().getBytes("ISO-8859-1"), "UTF-8"));
        userRegistrationValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/user/addUser");
            modelAndView.addObject("userForm", userForm);
            return modelAndView;
        }

        userService.save(userForm);
        modelAndView.setViewName("admin/user/listUsers");
        modelAndView.addObject("listUsers", this.userService.getAll());
        modelAndView.addObject("userForm", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/editUser/{id}", method = RequestMethod.GET)
    public ModelAndView editUserGet(@PathVariable long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/editUser");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editUser/{id}", method = RequestMethod.POST)
    public ModelAndView editUserPost(@ModelAttribute("user") User user, BindingResult bindingResult,
                                     @PathVariable long id, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();

        User userFromDataBase = userService.getById(id);

        String newPassword = request.getParameter("newPassword");

        if (newPassword.equals("")) {
            user.setPassword(userFromDataBase.getPassword());
        } else {
            user.setPassword(newPassword);
        }


        if (!user.equals(userFromDataBase)) {

            userEditValidator.validate(user, bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("admin/user/editUser");
                modelAndView.addObject("user", user);
                return modelAndView;
            }

            userService.save(user);
        }
        modelAndView.setViewName("admin/user/listUsers");
        modelAndView.addObject("listUsers", this.userService.getAll());
        modelAndView.addObject("user", new User());

        return modelAndView;

    }

    @RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUserGet(@PathVariable long id) {

        User user = userService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user/deleteUser");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/deleteUser/{id}", method = RequestMethod.POST)
    public ModelAndView deleteUserPost(@ModelAttribute(name = "user") User skillForm, BindingResult bindingResult,
                                       @PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getById(id);


        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/user/deleteUser");
            modelAndView.addObject("user", user);
            return modelAndView;
        }

        userService.remove(user);

        modelAndView.setViewName("admin/user/listUsers");
        modelAndView.addObject("listUsers", this.userService.getAll());
        modelAndView.addObject("user", new User());

        return modelAndView;
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
