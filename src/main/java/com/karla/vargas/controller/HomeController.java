package com.karla.vargas.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.karla.vargas.model.Perfil;
import com.karla.vargas.model.Usuario;
import com.karla.vargas.model.Vacante;
import com.karla.vargas.service.IntUsuariosService;
import com.karla.vargas.service.IntVacantesService;

@Controller
public class HomeController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IntUsuariosService usuariosService;
	
	@Autowired
	private IntVacantesService vacantesService;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
	SecurityContextLogoutHandler logoutHandler =
	new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession session) {
		String username = auth.getName();
//		Obtener el usuario actual
		
		System.out.println("Usuario " + username);
		for(GrantedAuthority rol:auth.getAuthorities()) {
			System.out.println("Rol " + rol.getAuthority());
		}
		if(session.getAttribute("usuario") == null) { 		
		Usuario usuario = usuariosService.buscarPorUsername(username);
		session.setAttribute("usuario", usuario);
		usuario.setPassword(null);
		}
		return "redirect:/";
		
	}

	@PostMapping("/guardar")
	public String guardarUsuario(Usuario usuario) {
//		indicamos que la contrasena esta encriptada
//		usuario.setPassword("{noop}" + usuario.getPassword());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEstatus(1);
		usuario.setFechaRegistro(LocalDate.now());
		
//		Por defecto es Usuario
		
		Perfil per = new Perfil();
		per.setId(3);
		usuario.agregar(per);
		usuariosService.guardar(usuario);
		return "formLogin";
			
	}
	
	@GetMapping("/registro")
	public String registroUsuario(Usuario usuario) {
		return "formRegistro";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> vacantes = vacantesService.obtenerDestacadas();
		model.addAttribute("vacantes",vacantes);		
		return "home";
	}
	
	@GetMapping("/bcript/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto")String texto) {
		return "Contraseña Encriptada" + passwordEncoder.encode(texto);
	}
}

