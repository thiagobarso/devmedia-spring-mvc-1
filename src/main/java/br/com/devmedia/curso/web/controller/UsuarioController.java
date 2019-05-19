package br.com.devmedia.curso.web.controller;

import br.com.devmedia.curso.dao.UsuarioDao;
import br.com.devmedia.curso.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao dao;

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ModelAndView listaTodos(ModelMap model) {
        model.addAttribute("usuarios", dao.getTodos());
        return new ModelAndView("/user/list", model);
    }

    @GetMapping("/cadastro")
    public String cadastro(@ModelAttribute("usuario")Usuario usuario, ModelMap model){
        return "/user/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("usuario")Usuario usuario, RedirectAttributes attr){
        dao.salvar(usuario);
        attr.addFlashAttribute("message", "Usuário salvo com sucesso.");
        return "redirect:/usuario/todos";
    }
}
