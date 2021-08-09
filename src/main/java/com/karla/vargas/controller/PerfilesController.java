package com.karla.vargas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.karla.vargas.model.Perfil;
import com.karla.vargas.service.IntPerfilesService;

@Controller
@RequestMapping("/perfiles")
public class PerfilesController {

	@Autowired
	private IntPerfilesService perfilesService;
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("id") int idPerfil,
			Model model) {
		Perfil perfil =  perfilesService.buscarPorId(idPerfil);
		model.addAttribute("perfil", perfil);
		return "perfiles/formPerfil";
	}

	
	
	@PostMapping("/guardar")
	public String guardar(@RequestParam("perfil")String perfil,
		RedirectAttributes atributo) {
//		perfil.setId(perfilesService.obtenerTodas().size() + 1);
		System.out.println(perfil);
		Perfil per = new Perfil();
//		per.setId(perfilesService.obtenerTodas().size() + 1);
		per.setPerfil(perfil);		
		perfilesService.guardar(per);
		atributo.addFlashAttribute("msg", "El perfil se agrego con exito");
		return "redirect:/perfiles/listaPerfiles";
	}
	
	@GetMapping("/crear")
	public String crear(Perfil perfil) {
		
		return "perfiles/formPerfil";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idPerfil,
			RedirectAttributes atributo) {		
		atributo.addFlashAttribute("msg", "Perfil eliminado");
		perfilesService.eliminar(idPerfil);
		return "redirect:/perfiles/listaPerfiles";
		
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Perfil> perfiles = perfilesService.obtenerTodas();
		model.addAttribute("perfiles", perfiles);
		return "perfiles/listaPerfiles"; 
	}
}
