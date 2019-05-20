package br.com.devmedia.curso.web.controller;

import br.com.devmedia.curso.dao.UsuarioDao;
import br.com.devmedia.curso.domain.Usuario;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @GetMapping("/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model){
        Usuario usuario = dao.getId(id);
        model.addAttribute("usuario", usuario);
        return new ModelAndView("/user/add", model);
    }

    @PostMapping("/update")
    public ModelAndView preUpdate(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attr){
        dao.editar(usuario);
        attr.addFlashAttribute("message", "Usuário alterado com sucesso.");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr){
        dao.excluir(id);
        attr.addFlashAttribute("message", "Usuário excluído com sucesso.");
        return "redirect:/usuario/todos";
    }
}
