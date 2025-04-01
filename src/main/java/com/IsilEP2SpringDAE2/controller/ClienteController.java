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

import com.IsilEP2SpringDAE2.entity.Cliente;
import com.IsilEP2SpringDAE2.repository.ClienteRepository;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		clienteRepository.deleteById(codigo);
		List<Cliente> listaClientes = clienteRepository.findAll();
		model.addAttribute("listaClientes", listaClientes);
		return "gestionClientes";
	}
	
	
	@GetMapping("/mostrarEditar/{codigo}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		Cliente objCliente = clienteRepository.findById(codigo);
		model.addAttribute("objCliente", objCliente);
		return "editarCliente";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Cliente objCliente = new Cliente();
		model.addAttribute("objCliente",objCliente);
		return "nuevoCliente";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objCliente") Cliente objCliente, Model model) {
	    Optional<Cliente> clienteExistente = clienteRepository.findByCorreo(objCliente.getCorreo());
	    if (clienteExistente.isPresent()) {
	        model.addAttribute("error", "El correo ya está registrado, ingrese otro");
	        model.addAttribute("objCliente", objCliente);
	        return "nuevoCliente"; 
	    }
	    clienteRepository.save(objCliente);
	    List<Cliente> listaClientes = clienteRepository.findAll();
	    model.addAttribute("listaClientes", listaClientes);
	    return "gestionClientes";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objCliente") Cliente objCliente, Model model) {
	    Cliente objClienteBD = clienteRepository.findById(objCliente.getCodigo());
	    
	    if (!objClienteBD.getCorreo().equals(objCliente.getCorreo())) {
	        Optional<Cliente> clienteExistente = clienteRepository.findByCorreo(objCliente.getCorreo());
	        if (clienteExistente.isPresent()) {
	            model.addAttribute("error", "El correo ya está registrado, ingrese otro");
	            model.addAttribute("objCliente", objCliente);
	            return "editarCliente";
	        }
	    }
	    objClienteBD.setNombre(objCliente.getNombre());
	    objClienteBD.setApellidoPaterno(objCliente.getApellidoPaterno());
	    objClienteBD.setApellidoMaterno(objCliente.getApellidoMaterno());
	    objClienteBD.setTelefono(objCliente.getTelefono());
	    objClienteBD.setDireccion(objCliente.getDireccion());
	    objClienteBD.setCorreo(objCliente.getCorreo());
	    clienteRepository.save(objClienteBD);
	    
	    List<Cliente> listaClientes = clienteRepository.findAll();
	    model.addAttribute("listaClientes", listaClientes);
	    return "gestionClientes";
	}
	
}
