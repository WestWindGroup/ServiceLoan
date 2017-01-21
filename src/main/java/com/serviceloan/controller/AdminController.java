package com.serviceloan.controller;


import com.serviceloan.model.*;
import com.serviceloan.service.*;
import com.serviceloan.validator.*;
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
    private RateInterestValidator rateValidator;

    @Autowired
    private CreditTypeValidator typeValidator;

    @Autowired
    private CreditStatusValidator statusValidator;

    @Autowired
    private CreditDurationService durationService;

    @Autowired
    private CreditDurationValidator durationValidator;

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
        model.addAttribute("role", role);

        return "admin/role/addRole";
    }

    @RequestMapping(value = "/admin/addRole", method = RequestMethod.POST)
    public ModelAndView addRolePost(@ModelAttribute(name = "role") Role role, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        roleValidator.validate(role, bindingResult);

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
    public ModelAndView deleteRolePost(@ModelAttribute(name = "role") Role roleForm, BindingResult bindingResult,
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
    public ModelAndView editRolePost(@ModelAttribute(name = "role") Role role, BindingResult bindingResult,
                                     @PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();

        Role roleFromDataBase = roleService.getById(id);

        if (!role.equals(roleFromDataBase)) {

            roleValidator.validate(role, bindingResult);

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
    public ModelAndView editUserPost(@ModelAttribute("user") User userForm, BindingResult bindingResult,
                                     @PathVariable long id, HttpServletRequest request) throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        userForm.setFirstName(new String(userForm.getFirstName().getBytes("ISO-8859-1"), "UTF-8"));
        userForm.setLastName(new String(userForm.getLastName().getBytes("ISO-8859-1"), "UTF-8"));

        User userFromDataBase = userService.getById(id);

        String newPassword = request.getParameter("newPassword");

        if (newPassword.equals("")) {
            userForm.setPassword(userFromDataBase.getPassword());
        } else {
            userForm.setPassword(newPassword);
        }


        if (!userForm.equals(userFromDataBase)) {

            userEditValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("admin/user/editUser");
                modelAndView.addObject("user", userForm);
                return modelAndView;
            }

            userService.save(userForm);
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

    @RequestMapping(value = "/admin/addRate", method = RequestMethod.GET)
    public String addRateGet() {

        return "admin/rateInterest/addRate";
    }

    @RequestMapping(value = "/admin/addRate", method = RequestMethod.POST)
    public ModelAndView addRatePost(HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();

        String min_rate = String.valueOf(rateValidator.getEnv().getProperty("key.min.rate"));
        String max_rate = String.valueOf(rateValidator.getEnv().getProperty("key.max.rate"));
        String msg = messageSource.getMessage(
                "key.credit", new String[]{min_rate, max_rate}, localeResolver.resolveLocale(request));

        String newRate = request.getParameter("rateInput");
        RateInterest rateInterest = new RateInterest();

        modelAndView.addObject("errorRate",msg);
        modelAndView.setViewName("admin/rateInterest/addRate");

        if (!rateValidator.checkCorrect(newRate)) {
            return modelAndView;
        }
        newRate = rateValidator.replacedOnComma(newRate);
        rateInterest.setRate(Double.parseDouble(newRate));

        if (rateValidator.checkCorrectAmount(rateInterest.getRate(),min_rate,max_rate)) {
            return modelAndView;
        }

        interestService.save(rateInterest);
        modelAndView.clear();
        modelAndView.setViewName("admin/rateInterest/listRates");
        modelAndView.addObject("listRates", this.interestService.getAll());
        modelAndView.addObject("rate", new RateInterest());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/editRate/{id}", method = RequestMethod.GET)
    public ModelAndView editRateGet(@PathVariable long id) {
        RateInterest rate = interestService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/rateInterest/editRate");
        modelAndView.addObject("rate", rate);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editRate/{id}", method = RequestMethod.POST)
    public ModelAndView editRatePost(@ModelAttribute("rate") RateInterest rate, @PathVariable long id,
                                     HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();

        RateInterest rateFromDataBase = interestService.getById(id);

        String newRate = request.getParameter("rateInput");

        if (!newRate.equals(String.valueOf(rateFromDataBase.getRate()))) {
            String min_rate = String.valueOf(rateValidator.getEnv().getProperty("key.min.rate"));
            String max_rate = String.valueOf(rateValidator.getEnv().getProperty("key.max.rate"));
            String msg = messageSource.getMessage(
                    "key.credit", new String[]{min_rate, max_rate}, localeResolver.resolveLocale(request));

            RateInterest rateInterest = new RateInterest();

            modelAndView.addObject("errorRate",msg);
            modelAndView.setViewName("admin/rateInterest/addRate");

            if (!rateValidator.checkCorrect(newRate)) {
                return modelAndView;
            }
            newRate = rateValidator.replacedOnComma(newRate);
            rateInterest.setRate(Double.parseDouble(newRate));

            if (rateValidator.checkCorrectAmount(rateInterest.getRate(),min_rate,max_rate)) {
                return modelAndView;
            }
        }
        interestService.save(rate);
        modelAndView.clear();
        modelAndView.setViewName("admin/rateInterest/listRates");
        modelAndView.addObject("listRates", this.interestService.getAll());
        modelAndView.addObject("rate", new RateInterest());

        return modelAndView;

    }

    @RequestMapping(value = "/admin/deleteRate/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRateGet(@PathVariable long id) {

        RateInterest rate = interestService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/rateInterest/deleteRate");
        modelAndView.addObject("rate", rate);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteRate/{id}", method = RequestMethod.POST)
    public ModelAndView deleteRatePost(@ModelAttribute(name = "rate") RateInterest rate, BindingResult bindingResult,
                                       @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();
        RateInterest rateFromBase = interestService.getById(id);

        if ((rateFromBase.getCredits() != null) && (rateFromBase.getCredits().size() != 0)) {
            modelAndView.addObject("errorDelete",
                    messageSource.getMessage("key.delete.impossible", null, localeResolver.resolveLocale(request)));
            bindingResult.addError(null);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/rateInterest/deleteRate");
            modelAndView.addObject("rate", rate);
            return modelAndView;
        }

        interestService.remove(rate);

        modelAndView.setViewName("admin/rateInterest/listRates");
        modelAndView.addObject("listRates", this.interestService.getAll());
        modelAndView.addObject("rate", new RateInterest());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/listDurations", method = RequestMethod.GET)
    public String listDurations(Model model) {
        model.addAttribute("duration", new CreditDuration());
        model.addAttribute("listDurations", this.durationService.getAll());

        return "admin/creditDuration/listDurations";
    }

    @RequestMapping(value = "/admin/addDuration", method = RequestMethod.GET)
    public String addDurationGet() {

        return "admin/creditDuration/addDuration";
    }

    @RequestMapping(value = "/admin/addDuration", method = RequestMethod.POST)
    public ModelAndView addDurationPost(HttpServletRequest request, CookieLocaleResolver localeResolver) {
        ModelAndView modelAndView = new ModelAndView();

        CreditDuration creditDuration = new CreditDuration();
        String newDuration = request.getParameter("durationInput");
        if (!durationValidator.checkCorrectDuration(newDuration, creditDuration, modelAndView, request, localeResolver)) {
            return modelAndView;
        }

        durationService.save(creditDuration);
        modelAndView.setViewName("admin/creditDuration/listDurations");
        modelAndView.addObject("listDurations", this.durationService.getAll());
        modelAndView.addObject("duration", new CreditDuration());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/editDuration/{id}", method = RequestMethod.GET)
    public ModelAndView editDurationGet(@PathVariable long id) {
        CreditDuration duration = durationService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditDuration/editDuration");
        modelAndView.addObject("duration", duration);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editDuration/{id}", method = RequestMethod.POST)
    public ModelAndView editDurationPost(@ModelAttribute("duration") CreditDuration duration, @PathVariable long id,
                                     HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();

        CreditDuration durationFromDataBase = durationService.getById(id);

        String newDuration = request.getParameter("durationInput");

        if (!newDuration.equals(String.valueOf(durationFromDataBase.getDuration()))) {
            if (!durationValidator.checkCorrectDuration(newDuration, duration, modelAndView, request, localeResolver)) {
                return modelAndView;
            }
        }

        durationService.save(duration);
        modelAndView.setViewName("admin/creditDuration/listDurations");
        modelAndView.addObject("listDurations", this.durationService.getAll());
        modelAndView.addObject("duration", new CreditDuration());

        return modelAndView;

    }

    @RequestMapping(value = "/admin/deleteDuration/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDurationGet(@PathVariable long id) {

        CreditDuration duration = durationService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditDuration/deleteDuration");
        modelAndView.addObject("duration", duration);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteDuration/{id}", method = RequestMethod.POST)
    public ModelAndView deleteDurationPost(@ModelAttribute(name = "duration") CreditDuration duration, BindingResult bindingResult,
                                       @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();
        CreditDuration durationFromBase = durationService.getById(id);

        if ((durationFromBase.getCredits() != null) && (durationFromBase.getCredits().size() != 0)) {
            modelAndView.addObject("errorDelete",
                    messageSource.getMessage("key.delete.impossible", null, localeResolver.resolveLocale(request)));
            bindingResult.addError(null);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/creditDuration/deleteDuration");
            modelAndView.addObject("duration", duration);
            return modelAndView;
        }

        durationService.remove(duration);

        modelAndView.setViewName("admin/creditDuration/listDurations");
        modelAndView.addObject("listDurations", this.durationService.getAll());
        modelAndView.addObject("duration", new CreditDuration());

        return modelAndView;
    }



    @RequestMapping(value = "/admin/listTypes", method = RequestMethod.GET)
    public String listTypes(Model model) {
        model.addAttribute("type", new CreditType());
        model.addAttribute("listTypes", this.typeService.getAll());

        return "admin/creditType/listTypes";
    }

    @RequestMapping(value = "/admin/addType", method = RequestMethod.GET)
    public String addTypeGet(Model model) {
        CreditType type = new CreditType();
        model.addAttribute("type", type);

        return "admin/creditType/addType";
    }

    @RequestMapping(value = "/admin/addType", method = RequestMethod.POST)
    public ModelAndView addTypePost(@ModelAttribute(name = "type") CreditType type, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        typeValidator.validate(type, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/creditType/addType");
            modelAndView.addObject("type", type);
            return modelAndView;
        }

        typeService.save(type);

        modelAndView.setViewName("admin/creditType/listTypes");
        modelAndView.addObject("listTypes", this.typeService.getAll());
        modelAndView.addObject("type", new CreditType());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteType/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTypeGet(@PathVariable long id) {

        CreditType type = typeService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditType/deleteType");
        modelAndView.addObject("type", type);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteType/{id}", method = RequestMethod.POST)
    public ModelAndView deleteTypePost(@ModelAttribute(name = "type") CreditType type, BindingResult bindingResult,
                                       @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();
        CreditType typeFromBase = typeService.getById(id);

        if (typeFromBase.getCredits().size() != 0) {
            modelAndView.addObject("errorDelete",
                    messageSource.getMessage("key.delete.impossible", null, localeResolver.resolveLocale(request)));
            bindingResult.addError(null);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/creditType/deleteType");
            modelAndView.addObject("type", type);
            return modelAndView;
        }

        typeService.remove(type);

        modelAndView.setViewName("admin/creditType/listTypes");
        modelAndView.addObject("listTypes", this.typeService.getAll());
        modelAndView.addObject("type", new CreditType());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editType/{id}", method = RequestMethod.GET)
    public ModelAndView editTypeGet(@PathVariable long id) {

        CreditType type = typeService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditType/editType");
        modelAndView.addObject("type", type);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editType/{id}", method = RequestMethod.POST)
    public ModelAndView editTypePost(@ModelAttribute(name = "type") CreditType type, BindingResult bindingResult,
                                     @PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();

        Role roleFromDataBase = roleService.getById(id);

        if (!type.equals(roleFromDataBase)) {

            typeValidator.validate(type, bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("admin/creditType/editType");
                modelAndView.addObject("type", type);
                return modelAndView;
            }

            typeService.save(type);
        }

        modelAndView.setViewName("admin/creditType/listTypes");
        modelAndView.addObject("listTypes", this.typeService.getAll());
        modelAndView.addObject("type", new CreditType());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/listStatuses", method = RequestMethod.GET)
    public String listStatuses(Model model) {
        model.addAttribute("status", new CreditStatus());
        model.addAttribute("listStatuses", this.statusService.getAll());

        return "admin/creditStatus/listStatuses";
    }


    @RequestMapping(value = "/admin/addStatus", method = RequestMethod.GET)
    public String addStatusGet(Model model) {
        CreditStatus status = new CreditStatus();
        model.addAttribute("status", status);

        return "admin/creditStatus/addStatus";
    }

    @RequestMapping(value = "/admin/addStatus", method = RequestMethod.POST)
    public ModelAndView addStatusPost(@ModelAttribute(name = "status") CreditStatus status, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        statusValidator.validate(status, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/creditStatus/addStatus");
            modelAndView.addObject("status", status);
            return modelAndView;
        }

        statusService.save(status);

        modelAndView.setViewName("admin/creditStatus/listStatuses");
        modelAndView.addObject("listStatuses", this.statusService.getAll());
        modelAndView.addObject("status", new CreditStatus());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteStatus/{id}", method = RequestMethod.GET)
    public ModelAndView deleteStatusGet(@PathVariable long id) {

        CreditStatus status = statusService.getById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditStatus/deleteStatus");
        modelAndView.addObject("status", status);

        return modelAndView;
    }


    @RequestMapping(value = "/admin/deleteStatus/{id}", method = RequestMethod.POST)
    public ModelAndView deleteTypePost(@ModelAttribute(name = "status") CreditStatus status, BindingResult bindingResult,
                                       @PathVariable long id, HttpServletRequest request, CookieLocaleResolver localeResolver) {

        ModelAndView modelAndView = new ModelAndView();
        CreditStatus statusFromBase = statusService.getById(id);

        if (statusFromBase.getCredits().size() != 0) {
            modelAndView.addObject("errorDelete",
                    messageSource.getMessage("key.delete.impossible", null, localeResolver.resolveLocale(request)));
            bindingResult.addError(null);
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/creditStatus/deleteStatus");
            modelAndView.addObject("status", status);
            return modelAndView;
        }

        statusService.remove(status);

        modelAndView.setViewName("admin/creditStatus/listStatuses");
        modelAndView.addObject("listStatuses", this.statusService.getAll());
        modelAndView.addObject("status", new CreditStatus());

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editStatus/{id}", method = RequestMethod.GET)
    public ModelAndView editStatusesGet(@PathVariable long id) {

        CreditStatus status = statusService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/creditStatus/editStatus");
        modelAndView.addObject("status", status);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/editStatus/{id}", method = RequestMethod.POST)
    public ModelAndView editStatusPost(@ModelAttribute(name = "status") CreditStatus status, BindingResult bindingResult,
                                       @PathVariable long id) {

        ModelAndView modelAndView = new ModelAndView();

        CreditStatus statusFromDataBase = statusService.getById(id);

        if (!status.equals(statusFromDataBase)) {

            statusValidator.validate(status, bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName("admin/creditStatus/editStatus");
                modelAndView.addObject("status", status);
                return modelAndView;
            }

            statusService.save(status);
        }

        modelAndView.setViewName("admin/creditStatus/listStatuses");
        modelAndView.addObject("listStatuses", this.statusService.getAll());
        modelAndView.addObject("status", new CreditStatus());

        return modelAndView;
    }


}
