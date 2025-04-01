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

import com.IsilEP2SpringDAE2.entity.Cliente;
import com.IsilEP2SpringDAE2.entity.Pedido;
import com.IsilEP2SpringDAE2.repository.ClienteRepository;
import com.IsilEP2SpringDAE2.repository.PedidoRepository;


@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@RequestMapping(value="/buscarPedido", method=RequestMethod.GET)
	public String buscarPedido(HttpServletRequest request, @RequestParam(value="FK_codigoCliente") int FK_codigoCliente, Model model) {
		Cliente objCliente = clienteRepository.findById(FK_codigoCliente);
		List<Pedido> listaPedidos = pedidoRepository.findByCliente(objCliente);
		model.addAttribute("listaPedidos",listaPedidos);
		List<Cliente> listaClientes = clienteRepository.findAll();
		//List <Entidad> listaEntidades = entidadRepository.findByEstado("Activo");
		model.addAttribute("listaClientes",listaClientes);
		model.addAttribute("codigoClienteSeleccionado", FK_codigoCliente);
		return "gestionPedidos";
	}
	
	/*@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		pedidoRepository.deleteById(codigo);
		List<Pedido> listaPedidos = pedidoRepository.findAll();
		model.addAttribute("listaPedidos", listaPedidos);
		return "gestionPedidos";
	}*/
	@GetMapping("/eliminar/{codigo}")
	public String eliminar(HttpServletRequest request, @PathVariable("codigo") int codigo) {
		pedidoRepository.deleteById(codigo);
		return "redirect:/home/mostrarGestionPedidos";
	}
	
	@GetMapping("/mostrarEditar/{codigo}")
	public String mostrarEditar(HttpServletRequest request, @PathVariable("codigo") int codigo, Model model) {
		Pedido objPedido = pedidoRepository.findById(codigo);
		model.addAttribute("objPedido", objPedido);
		List<Cliente> listaClientes = clienteRepository.findAll();
		model.addAttribute("listaClientes",listaClientes);
		return "editarPedido";
	}
	
	
	@PostMapping("/mostrarNuevo")
	public String mostrarNuevo(HttpServletRequest request, Model model) {
		Pedido objPedido = new Pedido();
		List<Cliente> listaClientes = clienteRepository.findAll();
		model.addAttribute("listaClientes",listaClientes);
		model.addAttribute("objPedido",objPedido);
		return "nuevoPedido";
	}
	/*
	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objPedido")Pedido objPedido, Model model) {
		pedidoRepository.save(objPedido);
		List<Pedido> listaPedidos = pedidoRepository.findAll();
		model.addAttribute("listaPedidos", listaPedidos);
		return "gestionPedidos";
	}*/

	@PostMapping("/registrar")
	public String registrar(HttpServletRequest request, @ModelAttribute("objPedido")Pedido objPedido, Model model) {
		Pedido objPedidoBD = pedidoRepository.findByNroPedido(objPedido.getNroPedido());
		if(objPedidoBD!=null) 
		{
			model.addAttribute("Mensaje", "El Pedido ya se encuentra registrado");
			model.addAttribute("objPedido",objPedido);
			List<Cliente> listaClientes = clienteRepository.findAll();
			model.addAttribute("ListaClientes",listaClientes);
			return "nuevoPedido";
		}
		else 
		{
			pedidoRepository.save(objPedido);
			return "redirect:/home/mostrarGestionPedidos";
		}
	}
	
	@PostMapping("/actualizar")
	public String actualizar(HttpServletRequest request, @ModelAttribute("objPedido")Pedido objPedido, Model model) {
		Pedido objPedidoBD = pedidoRepository.findById(objPedido.getCodigo());
		objPedidoBD.setFechaPedido(objPedido.getFechaPedido());
		objPedidoBD.setNroPedido(objPedido.getNroPedido());
		objPedidoBD.setDetalles(objPedido.getDetalles());
		objPedidoBD.setCostoTotal(objPedido.getCostoTotal());
		objPedidoBD.setEstado(objPedido.getEstado());
		objPedidoBD.setFechaPago(objPedido.getFechaPago());
		objPedidoBD.setTipoEnvio(objPedido.getTipoEnvio());
		objPedidoBD.setCliente(objPedido.getCliente());
		pedidoRepository.save(objPedidoBD);	
		return "redirect:/home/mostrarGestionPedidos";
	}
}
