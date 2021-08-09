package com.karla.vargas.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.karla.vargas.model.Vacante;
import com.karla.vargas.service.IntCategoriasService;
import com.karla.vargas.service.IntVacantesService;
import com.karla.vargas.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Value("${app.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IntVacantesService vacantesService;
	
	@Autowired
	private IntCategoriasService categoriasService;
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("id") int idVacante, Model model) {
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> lista = vacantesService.buscarTodas(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listaVacantes";
	}

	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idVacante, RedirectAttributes model) {
		model.addFlashAttribute("msg", "Vacante eliminada con éxito!");
		vacantesService.eliminar(idVacante);
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/detalles")
	public String detalle(@RequestParam("id") int idVacante, Model model) {
		//System.out.println("idVacante " + idVacante);
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/detalle";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Vacante vacante, BindingResult resultado,RedirectAttributes attribute, @RequestParam("archivoImagen") MultipartFile multiPart) {
		System.out.println(vacante);
		if(resultado.hasErrors()) {
			for(ObjectError error: resultado.getAllErrors()) {
				System.out.println("Error: "+error.getDefaultMessage());
			}
			
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen); 
			}
		}
		
		
		System.out.println(vacante);
		vacante.setId(vacantesService.obtenerTodas().size()+1);
		vacante.setCategoria(categoriasService.buscarPorId(vacante.getCategoria().getId()));
		vacantesService.guardar(vacante);
		attribute.addFlashAttribute("msg", "Vacante guardada con éxito!");
		return "redirect:/vacantes/indexPaginado";
	}
	
	@GetMapping("/crear")
	public String crear(Vacante vacante) {
		
		return "vacantes/formVacante";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		for(Vacante v: vacantesService.obtenerTodas()) {
			System.out.println(v);
		}
		return "vacantes/listaVacantes";
	}

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
        @Override
        public void setAsText(String text) throws IllegalArgumentException{
          setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
          return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
        }
        
    
      });
    }
	
	@ModelAttribute
	public void setGenerico(Model model) {
		model.addAttribute("vacantes",vacantesService.obtenerTodas());
		model.addAttribute("categorias", categoriasService.obtenerTodos());
	}
}