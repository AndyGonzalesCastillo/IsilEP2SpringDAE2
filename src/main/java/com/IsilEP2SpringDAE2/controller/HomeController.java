package com.IsilEP2SpringDAE2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.IsilEP2SpringDAE2.entity.Producto;
import com.IsilEP2SpringDAE2.entity.ProductoFijo;
import com.IsilEP2SpringDAE2.entity.ProductoPersonalizado;
import com.IsilEP2SpringDAE2.entity.Usuario;
import com.IsilEP2SpringDAE2.repository.CatalogoRepository;
import com.IsilEP2SpringDAE2.repository.CiudadRepository;
import com.IsilEP2SpringDAE2.repository.ClienteRepository;
import com.IsilEP2SpringDAE2.repository.ProductoRepository;
import com.IsilEP2SpringDAE2.repository.UsuarioRepository;
import com.IsilEP2SpringDAE2.entity.Compra;
import com.IsilEP2SpringDAE2.entity.Delivery;
import com.IsilEP2SpringDAE2.entity.OrdenCompra;
import com.IsilEP2SpringDAE2.entity.Pedido;
import com.IsilEP2SpringDAE2.entity.Proveedor;
import com.IsilEP2SpringDAE2.repository.CompraRepository;
import com.IsilEP2SpringDAE2.repository.DeliveryRepository;
import com.IsilEP2SpringDAE2.repository.OrdenCompraRepository;
import com.IsilEP2SpringDAE2.repository.PedidoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoFijoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoPersonalizadoRepository;
import com.IsilEP2SpringDAE2.repository.ProveedorRepository;
import com.IsilEP2SpringDAE2.entity.Catalogo;
import com.IsilEP2SpringDAE2.entity.Ciudad;
import com.IsilEP2SpringDAE2.entity.Cliente;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	CompraRepository compraRepository;
	
	@Autowired
	ProductoFijoRepository productoFijoRepository;
	
	@Autowired 
	CiudadRepository ciudadRepository;
	
	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@Autowired
	DeliveryRepository deliveryRepository;
	
	@Autowired
	ProductoPersonalizadoRepository productoPersonalizadoRepository;
	
	@Autowired
	CatalogoRepository catalogoRepository;
	
	@RequestMapping(value="/validarUsuario", method=RequestMethod.POST)
	public String validarUsuario(HttpServletRequest request, @RequestParam(value="email") String email, @RequestParam(value="password") String password) {
		Usuario objUsuario = usuarioRepository.findByCorreoAndPassword(email, password);
		if(objUsuario !=null) {
			return "principal";
		}
		else {
			return "index";
		}
	}
	
	@GetMapping("/mostrarGestionProductos")
	public String mostrarGestionProductos(HttpServletRequest request, Model model) {
		List<Producto> listaProductos = productoRepository.findAll();
		model.addAttribute("listaProductos", listaProductos);
		return "gestionProductos";
	}
	
	
	@GetMapping("/mostrarGestionUsuarios")
	public String mostrarGestionUsuarios(HttpServletRequest request, Model model) {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "gestionUsuarios";
	}

	@GetMapping("/mostrarGestionClientes")
	public String mostrarGestionClientes(HttpServletRequest request, Model model) {
		List<Cliente> listaClientes = clienteRepository.findAll();
		model.addAttribute("listaClientes", listaClientes);
		return "gestionClientes";
	}
	
	@GetMapping("/mostrarGestionProveedores")
	public String mostrarGestionProveedores(HttpServletRequest request, Model model) {
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		List <Ciudad> listaCiudades = ciudadRepository.findAll();
		model.addAttribute("listaProveedores", listaProveedores);
		model.addAttribute("listaCiudades", listaCiudades);
		return "gestionProveedores";
	}
	
	@GetMapping("/mostrarGestionPedidos")
	public String mostrarGestionPedidos(HttpServletRequest request, Model model) {
		List<Pedido> listaPedidos = pedidoRepository.findAll();
		List<Cliente> ListaClientes = clienteRepository.findAll();
		model.addAttribute("listaPedidos", listaPedidos);
		model.addAttribute("listaClientes",ListaClientes);
		return "gestionPedidos";
	}
	
	@GetMapping("/mostrarGestionCompras")
	public String mostrarGestionCompras(HttpServletRequest request, Model model) {
		List<Compra> listaCompras = compraRepository.findAll();
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaCompras", listaCompras);
		model.addAttribute("listaProveedores",listaProveedores);
		return "gestionCompras";
	}

	@GetMapping("/mostrarGestionProductoFijo")
	public String mostrarGestionProductoFijo(HttpServletRequest request, Model model) {
		List<ProductoFijo> listaProductoFijo = productoFijoRepository.findAll();
		model.addAttribute("listaProductoFijo", listaProductoFijo);
		return "gestionProductoFijo";
	}
	
	@GetMapping("/mostrarGestionDelivery")
	public String mostrarGestionDelivery(HttpServletRequest request, Model model) {
		List<Delivery> listaDelivery = deliveryRepository.findAll();
		model.addAttribute("listaDelivery", listaDelivery);
		return "gestionDelivery";
	}
	
	@GetMapping("/mostrarGestionProductoPersonalizado")
	public String mostrarGestionProductoPersonalizado(HttpServletRequest request, Model model) {
		List<ProductoPersonalizado> listaProductoPersonalizado = productoPersonalizadoRepository.findAll();
		model.addAttribute("listaProductoPersonalizado", listaProductoPersonalizado);
		return "gestionProductoPersonalizado";
	}
	
	@GetMapping("/mostrarGestionOrdenesCompra")
	public String mostrarGestionOrdenesCompra(HttpServletRequest request, Model model) {
		List<OrdenCompra> listaOrdenesCompra = ordenCompraRepository.findAll();
		model.addAttribute("listaOrdenesCompra", listaOrdenesCompra);
		return "gestionOrdenCompra";
	}
	
	@GetMapping("/mostrarGestionCatalogo")
	public String mostrarGestionCatalogo(HttpServletRequest request, Model model) {
		List<Catalogo> listaCatalogo = catalogoRepository.findAll();
		model.addAttribute("listaCatalogo", listaCatalogo);
		return "gestionCatalogo";
	}
}
