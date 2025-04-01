package com.IsilEP2SpringDAE2.controller;

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

import com.IsilEP2SpringDAE2.entity.Compra;
import com.IsilEP2SpringDAE2.entity.Proveedor;
import com.IsilEP2SpringDAE2.repository.CompraRepository;
import com.IsilEP2SpringDAE2.repository.ProveedorRepository;


@Controller
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@RequestMapping(value="/buscarCompra", method=RequestMethod.GET)
	public String buscarCompra(HttpServletRequest request, @RequestParam(value="FK_codigoProveedor") int FK_codigoProveedor, Model model) {
		Proveedor objProveedor = proveedorRepository.findById(FK_codigoProveedor);
		List<Compra> listaCompras = compraRepository.findByProveedor(objProveedor);
		model.addAttribute("listaCompras", listaCompras);
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaProveedores",listaProveedores);
		model.addAttribute("codigoProveedorSeleccionado", FK_codigoProveedor);
		return "gestionCompras";
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(HttpServletRequest request, @PathVariable("id") int id) {
		compraRepository.deleteById(id);
		return "redirect:/home/mostrarGestionCompras";
	}

	@GetMapping("/mostrarEditar/{id}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
		Compra objCompra = compraRepository.findById(id);
		model.addAttribute("objCompra", objCompra);
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaProveedores",listaProveedores);
		return "editarCompra";
	}
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Compra objCompra = new Compra();
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaProveedores",listaProveedores);
		model.addAttribute("objCompra",objCompra);
		return "nuevoCompra";
	}
	
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objCompra")Compra objCompra, Model model) {
		Compra objCompraBD = compraRepository.findByDetalle(objCompra.getDetalle());
		if(objCompraBD!=null) {
			model.addAttribute("Mensaje", "La Compra ya se encuentra registrada");
			model.addAttribute("objCompra",objCompra);
			List<Proveedor> listaProveedores = proveedorRepository.findAll();
			model.addAttribute("listaProveedores",listaProveedores);
			return "nuevoCompra";
		}
		else {
			compraRepository.save(objCompra);
			return "redirect:/home/mostrarGestionCompras";
		}
		
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objCompra")Compra objCompra, Model model) {
		Compra objCompraBD = compraRepository.findById(objCompra.getId());
		objCompraBD.setDetalle(objCompra.getDetalle());
		objCompraBD.setCantidad(objCompra.getCantidad());
		objCompraBD.setPrecioTotal(objCompra.getPrecioTotal());
		objCompraBD.setEstado(objCompra.getEstado());
		objCompraBD.setFecha(objCompra.getFecha());
		objCompraBD.setProveedor(objCompra.getProveedor());
		compraRepository.save(objCompraBD);
		return "redirect:/home/mostrarGestionCompras";
	}
	
}
