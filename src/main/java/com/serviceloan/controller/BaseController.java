package com.serviceloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for basic pages (About, Terms of Use, etc.)
 *
 * @author Eugene Artemenko
 */

@Controller
public class BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

}
