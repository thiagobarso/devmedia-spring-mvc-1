package br.com.devmedia.curso.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ModelAndView teste(){
        ModelAndView view = new ModelAndView("welcome");
        view.addObject("teste","Olá, eu sou o Spring MVC.");
        return view;
    }
}
