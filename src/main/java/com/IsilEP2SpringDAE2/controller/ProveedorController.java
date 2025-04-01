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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilEP2SpringDAE2.entity.Proveedor;
import com.IsilEP2SpringDAE2.repository.CiudadRepository;
import com.IsilEP2SpringDAE2.repository.ProveedorRepository;
import com.IsilEP2SpringDAE2.entity.Ciudad;


@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	CiudadRepository ciudadRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@RequestMapping(value="/buscarProveedor", method=RequestMethod.GET)
	public String buscarProveedor(HttpServletRequest request, @RequestParam(value="idCiudad") int idCiudad, Model model) {
		Ciudad objCiudad = ciudadRepository.findById(idCiudad);
		List<Proveedor> listaProveedores = proveedorRepository.findByCiudad(objCiudad);
		model.addAttribute("listaProveedores",listaProveedores);
		List<Ciudad> listaCiudades = ciudadRepository.findAll();
		//List <Entidad> listaEntidades = entidadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades",listaCiudades);
		model.addAttribute("codigoCiudadSeleccionada", idCiudad);
		return "gestionProveedores";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		proveedorRepository.deleteById(codigo);
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaProveedores", listaProveedores);
		return "redirect:/home/mostrarGestionProveedores";
	}
	
	@GetMapping("/mostrarEditar/{id}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		Proveedor objProveedor = proveedorRepository.findById(id);
		model.addAttribute("objProveedor",objProveedor);
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades", listaCiudades);
		return "editarProveedor";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Proveedor objProveedor = new Proveedor();
		model.addAttribute("objProveedor",objProveedor);
		List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
		model.addAttribute("listaCiudades", listaCiudades);
		return "nuevoProveedor";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objProveedor") Proveedor objProveedor, Model model) {
	    Optional<Proveedor> proveedorExistente = proveedorRepository.findByNombre(objProveedor.getNombre());
	    if (proveedorExistente.isPresent()) {
	        model.addAttribute("error", "El proveedor ya está registrado, ingrese otro");
	        model.addAttribute("objProveedor", objProveedor);
	        List<Ciudad> listaCiudades = ciudadRepository.findByEstado("Activo");
			model.addAttribute("listaCiudades", listaCiudades);
	        return "nuevoProveedor"; 
	    }else{
		    proveedorRepository.save(objProveedor);
		    return "redirect:/home/mostrarGestionProveedores";
	    }
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objProveedor") Proveedor objProveedor, Model model) {
	    Proveedor objProveedorBD = proveedorRepository.findById(objProveedor.getCodigo());
	    
	    if (!objProveedorBD.getNombre().equals(objProveedor.getNombre())) {
	        Optional<Proveedor> proveedorExistente = proveedorRepository.findByNombre(objProveedor.getNombre());
	        if (proveedorExistente.isPresent()) {
	            model.addAttribute("error", "El proveedor ya está registrado, ingrese otro");
	            model.addAttribute("objProveedor", objProveedor);
	            return "editarProveedor";
	        }
	    }
	    objProveedorBD.setNombre(objProveedor.getNombre());
	    objProveedorBD.setDireccion(objProveedor.getDireccion());
	    objProveedorBD.setTelefono(objProveedor.getTelefono());
	    objProveedorBD.setCorreo(objProveedor.getCorreo());
	    objProveedorBD.setCiudad(objProveedor.getCiudad());
	    proveedorRepository.save(objProveedorBD);
	    
	    List<Proveedor> listaProveedores = proveedorRepository.findAll();
	    model.addAttribute("listaProveedores", listaProveedores);
	    return "redirect:/home/mostrarGestionProveedores";
	}
}




