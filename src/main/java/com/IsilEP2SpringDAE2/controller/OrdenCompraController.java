package com.IsilEP2SpringDAE2.controller;

import java.sql.Date;
import java.util.List;

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

import com.IsilEP2SpringDAE2.entity.DetalleCompra;
import com.IsilEP2SpringDAE2.entity.Material;
import com.IsilEP2SpringDAE2.entity.OrdenCompra;
import com.IsilEP2SpringDAE2.repository.DetalleCompraRepository;
import com.IsilEP2SpringDAE2.repository.MaterialRepository;
import com.IsilEP2SpringDAE2.repository.OrdenCompraRepository;

@Controller
@RequestMapping("/ordenCompra")
public class OrdenCompraController {

	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@Autowired
	DetalleCompraRepository detalleCompraRepository;

	@Autowired
	MaterialRepository materialRepository;
	
	@RequestMapping(value = "/buscarOrdenCompra", method = RequestMethod.GET)
	public String buscarProveedor(HttpServletRequest request,
	                             @RequestParam(value = "fechaInicio", required = false) Date fechaInicio,
	                             @RequestParam(value = "fechaFin", required = false) Date fechaFin,
	                             Model model) {

	    if (fechaInicio == null || fechaFin == null) {
	        // Las fechas están vacías, muestra un mensaje de advertencia
	        model.addAttribute("mensaje", "Debes seleccionar las fechas para realizar la búsqueda.");
	        return "mensaje"; // Cambia "mensaje" por la vista que desees mostrar
	    }

	    List<OrdenCompra> listaOrdenesCompra = ordenCompraRepository.findByFechaRegistroBetween(fechaInicio, fechaFin);
	    model.addAttribute("listaOrdenesCompra", listaOrdenesCompra);
	    return "gestionOrdenCompra";
	}

	
	@GetMapping("/eliminar/{id}")
	public String eliminar(HttpServletRequest request, @PathVariable("id")int id) {
		OrdenCompra objOrdenCompraBD = ordenCompraRepository.findById(id);
		String mensaje;
		if (objOrdenCompraBD.getEstado().compareTo("Pendiente de Aprobación")==0) {
			ordenCompraRepository.deleteById(id);
			mensaje = "La orden de compra fue eliminada con éxito";
		}
		else {
			mensaje = "No se puede eliminar la Orden de Compra por tener un estado diferente a Pendiente de Aprobación";
		}
		return "redirect:/home/mostrarGestionOrdenesCompra";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(HttpServletRequest request, @PathVariable("id")int id, Model model) {
		OrdenCompra objOrdenCompraBD = ordenCompraRepository.findById(id);
		List<DetalleCompra> listaDetalleCompra = detalleCompraRepository.findByOrdenCompra(objOrdenCompraBD);
		model.addAttribute("ordenCompra", objOrdenCompraBD);
		model.addAttribute("listaDetallesCompra", listaDetalleCompra);
		return "verDetalleCompra";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		OrdenCompra ordenCompra = new OrdenCompra();
		model.addAttribute("objOrdenCompra", ordenCompra);
		return "nuevaOrdenCompra";
	}
	
	@RequestMapping(value="/guardar", method=RequestMethod.GET, params="grabarCabecera")
	public String guardarCabecera(HttpServletRequest request, @ModelAttribute("objOrdenCompra")OrdenCompra ordenCompra, Model model) {
		ordenCompra.setEstado("Pendiente de Aprobación");
		ordenCompraRepository.save(ordenCompra);
		model.addAttribute("objOrdenCompra", ordenCompra);
		List<Material> listaMateriales = materialRepository.findAll();
		model.addAttribute("listaMateriales", listaMateriales);
		return "nuevaOrdenCompra";
	}
	
	@RequestMapping(value="/guardar", method=RequestMethod.GET, params="grabarDetalle")
	public String guardarDetalle(HttpServletRequest request, @ModelAttribute("objOrdenCompra")OrdenCompra ordenCompra, @RequestParam("idMaterial")int idMaterial, @RequestParam("cantidad")int cantidad, @RequestParam("precioUnitario")double precioUnitario, @RequestParam("precioTotal")double precioTotal, Model model) {
		OrdenCompra ordenCompraBD = ordenCompraRepository.findById(ordenCompra.getId());
		Material objMaterialBD = materialRepository.findById(idMaterial);
		DetalleCompra detalleCompra = new DetalleCompra();
		detalleCompra.setCantidad(cantidad);
		detalleCompra.setPrecioTotal(precioTotal);
		detalleCompra.setPrecioUnitario(precioUnitario);
		detalleCompra.setMaterial(objMaterialBD);
		detalleCompra.setOrdenCompra(ordenCompraBD);
		detalleCompraRepository.save(detalleCompra);
		List<DetalleCompra> listaDetallesCompra = detalleCompraRepository.findByOrdenCompra(ordenCompraBD);
		model.addAttribute("listaDetallesCompra", listaDetallesCompra);
		model.addAttribute("objOrdenCompra", ordenCompraBD);
		List<Material> listaMateriales = materialRepository.findAll();
		model.addAttribute("listaMateriales", listaMateriales);		
		return "nuevaOrdenCompra";
	}
}
