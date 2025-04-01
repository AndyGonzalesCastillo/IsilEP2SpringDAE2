package com.IsilEP2SpringDAE2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilEP2SpringDAE2.entity.Catalogo;
import com.IsilEP2SpringDAE2.entity.ProductoPersonalizado;
import com.IsilEP2SpringDAE2.repository.CatalogoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoPersonalizadoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoRepository;

@Controller
@RequestMapping("/productoPersonalizado")
public class ProductoPersonalizadoController {

	@Autowired
	ProductoPersonalizadoRepository productoPersonalizadoRepository;
	
	@Autowired
	CatalogoRepository catalogoRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@RequestMapping(value="/buscarProductoPersonalizado", method=RequestMethod.GET)
	public String buscarProveedor(HttpServletRequest request, @RequestParam(value="nombreCatalogo") String nombreCatalogo, Model model) {
		Catalogo objCatalogo = catalogoRepository.findByNombre(nombreCatalogo);
		
		List<ProductoPersonalizado> listaProductoPersonalizado = productoPersonalizadoRepository.findByCatalogo(objCatalogo);
		model.addAttribute("listaProductoPersonalizado", listaProductoPersonalizado);
		return "gestionProductoPersonalizado";
	}
	
	
	@Transactional
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		
		
		productoPersonalizadoRepository.deleteByCodigo(codigo);
        List<ProductoPersonalizado> listaProductoFijo = productoPersonalizadoRepository.findAll();
        model.addAttribute("listaProductoFijo", listaProductoFijo);
        return "redirect:/home/mostrarGestionProductoPersonalizado";
	}

	@PostMapping("/registrar")
	public String registar(HttpServletRequest request, @ModelAttribute("objProductoPersonalizado")ProductoPersonalizado objProductoPersonalizado, Model model) {
		productoPersonalizadoRepository.save(objProductoPersonalizado);
        List<ProductoPersonalizado> listaProductoPersonalizado = productoPersonalizadoRepository.findAll();
        model.addAttribute("listaProductoPersonalizado", listaProductoPersonalizado);
        return "redirect:/home/mostrarGestionProductoPersonalizado";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objProductoPersonalizado") ProductoPersonalizado objProductoPersonalizado, Model model) {
		productoPersonalizadoRepository.save(objProductoPersonalizado);
        List<ProductoPersonalizado> listaProductoPersonalizado = productoPersonalizadoRepository.findAll();
        model.addAttribute("listaProductoPersonalizado", listaProductoPersonalizado);
        return "redirect:/home/mostrarGestionProductoPersonalizado";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		ProductoPersonalizado objproductoPersonalizado = new ProductoPersonalizado();
		model.addAttribute("objProductoPersonalizado", objproductoPersonalizado);
		model.addAttribute("listaProductos", productoRepository.findAll());
		model.addAttribute("listaCatalogo", catalogoRepository.findByEstado("Activo"));
		return "nuevoProductoPersonalizado";
	}
	
	@GetMapping("/mostrarEditar/{codigo}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		ProductoPersonalizado objProductoPersonalizado = productoPersonalizadoRepository.findByCodigo(codigo);
		model.addAttribute("objProductoPersonalizado", objProductoPersonalizado);
		model.addAttribute("listaProductos", productoRepository.findAll());
		model.addAttribute("listaCatalogo", catalogoRepository.findByEstado("Activo"));
        return "editarProductoPersonalizado";
	
	}
}
