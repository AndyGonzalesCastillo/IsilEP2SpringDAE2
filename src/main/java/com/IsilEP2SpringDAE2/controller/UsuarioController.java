package com.IsilEP2SpringDAE2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.IsilEP2SpringDAE2.entity.Usuario;
import com.IsilEP2SpringDAE2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		usuarioRepository.deleteById(codigo);
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "gestionUsuarios";
	}
	
	@GetMapping("/mostrarEditar/{codigo}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		Usuario objUsuario = usuarioRepository.findById(codigo);
		model.addAttribute("objUsuario", objUsuario);
		return "editarUsuario";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Usuario objUsuario = new Usuario();
		model.addAttribute("objUsuario",objUsuario);
		return "nuevoUsuario";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objUsuario") Usuario objUsuario, Model model) {
	    Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(objUsuario.getCorreo());
	    if (usuarioExistente.isPresent()) {
	        model.addAttribute("error", "El correo ya está registrado, ingrese otro");
	        model.addAttribute("objUsuario", objUsuario);
	        return "nuevoUsuario"; 
	    }
	    usuarioRepository.save(objUsuario);
	    List<Usuario> listaUsuarios = usuarioRepository.findAll();
	    model.addAttribute("listaUsuarios", listaUsuarios);
	    return "gestionUsuarios";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objUsuario") Usuario objUsuario, Model model) {
	    Usuario objUsuarioBD = usuarioRepository.findById(objUsuario.getCodigo());
	    
	    if (!objUsuarioBD.getCorreo().equals(objUsuario.getCorreo())) {
	        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(objUsuario.getCorreo());
	        if (usuarioExistente.isPresent()) {
	            model.addAttribute("error", "El correo ya está registrado, ingrese otro");
	            model.addAttribute("objUsuario", objUsuario);
	            return "editarUsuario";
	        }
	    }
	    objUsuarioBD.setCorreo(objUsuario.getCorreo());
	    objUsuarioBD.setEstado(objUsuario.getEstado());
	    objUsuarioBD.setPassword(objUsuario.getPassword());
	    usuarioRepository.save(objUsuarioBD);
	    
	    List<Usuario> listaUsuarios = usuarioRepository.findAll();
	    model.addAttribute("listaUsuarios", listaUsuarios);
	    return "gestionUsuarios";
	}
}