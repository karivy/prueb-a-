package com.karla.vargas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.karla.vargas.model.Categoria;
import com.karla.vargas.service.IntCategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("id") int idCategoria, Model model) {
		Categoria categoria = categoriasService.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
	}
	
	//Aplicar data binding (vincular datos entre la clase modelo y el formulario)
	@PostMapping("/guardar")
	public String guardar(Categoria categoria, RedirectAttributes attribute) {
		System.out.println(categoria);
		//categoria.setId(categoriasService.obtenerTodos().size()+1);
		//System.out.println(categoria);
		System.out.println(categoria);
		categoriasService.guardar(categoria);
		attribute.addFlashAttribute("msg", "Se guardó la categoría!");
		return "redirect:/categorias/index";
	}
	
	/*
	@PostMapping("guardar")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, RedirectAttributes attribute) {
		//System.out.println("Nombre: "+nombre);
		//System.out.println("Descripcion: "+descripcion);
		//Formar un objeto de categoría
		Categoria categoria = new Categoria();
		categoria.setId(categoriasService.obtenerTodos().size()+1);
		categoria.setDescripcion(descripcion);
		categoria.setNombre(nombre);
		categoriasService.guardar(categoria);
		//redireccionamiento a una petición 
		attribute.addFlashAttribute("msg", "Se guardó la categoría!");
		return "redirect:/categorias/index";
	}
	*/
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Categoria> lista = categoriasService.buscarTodas(page);
		model.addAttribute("categorias", lista);
		return "categorias/listaCategorias";
	}

	
	@GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("categorias",categoriasService.obtenerTodos());
        return "categorias/listaCategorias";
    }
    

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes atributo) {
		System.out.println("idCategoria: "+idCategoria);
		atributo.addFlashAttribute("msg", "Registro eliminado con éxito!");
		categoriasService.eliminar(idCategoria);
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/crear")
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}
}
