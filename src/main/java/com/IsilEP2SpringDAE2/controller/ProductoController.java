package com.IsilEP2SpringDAE2.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.IsilEP2SpringDAE2.entity.Producto;
import com.IsilEP2SpringDAE2.repository.ProductoRepository;


@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		productoRepository.deleteById(codigo);
		List<Producto> listaProductos = productoRepository.findAll();
		model.addAttribute("listaProductos", listaProductos);
		return "gestionProductos";
	}
	
	@GetMapping("/mostrarEditar/{id}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		
		Producto objProducto = productoRepository.findById(id);
		model.addAttribute("objProducto",objProducto);
		return "editarProducto";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Producto objProducto = new Producto();
		model.addAttribute("objProducto",objProducto);
		return "nuevoProducto";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request,Model model,@ModelAttribute("objProducto")Producto objProducto ) {
		Optional<Producto> productoExistente = productoRepository.findByNombre(objProducto.getNombre());
		if(productoExistente.isPresent()) {
			model.addAttribute("error", "El producto ya está registrado, ingrese otro");
	        model.addAttribute("objProducto", objProducto);
	        return "nuevoProducto"; 
		}
		productoRepository.save(objProducto);
		List<Producto> listaProductos = productoRepository.findAll();
		model.addAttribute("listaProductos",listaProductos);
		return "gestionProductos";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request,Model model,@ModelAttribute("objProducto")Producto objProducto ) {
		Producto objProductoBD = productoRepository.findById(objProducto.getId());
		
		if(!objProductoBD.getNombre().equals(objProducto.getNombre())) {
			Optional<Producto> productoExistente = productoRepository.findByNombre(objProducto.getNombre());
			if (productoExistente.isPresent()) {
			model.addAttribute("error", "El producto ya está registrado, ingrese otro");
	        model.addAttribute("objProducto", objProducto);
	        return "editarProducto"; 
			}
		}
		objProductoBD.setPrecio(objProducto.getPrecio());
		objProductoBD.setEstado(objProducto.getEstado());
		objProductoBD.setNombre(objProducto.getNombre());
		productoRepository.save(objProductoBD);
		
		List<Producto> listaProductos = productoRepository.findAll();
		model.addAttribute("listaProductos",listaProductos);
		return "gestionProductos";
	}

}
