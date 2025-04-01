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

import com.IsilEP2SpringDAE2.entity.ProductoFijo;
import com.IsilEP2SpringDAE2.repository.ProductoFijoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoRepository;
import com.IsilEP2SpringDAE2.repository.CatalogoRepository;

@Controller
@RequestMapping("/productofijo")
public class ProductoFijoController {
	
	@Autowired
    ProductoFijoRepository productoFijoRepository;

    @Autowired
    ProductoRepository productoRepository;
    
    @Autowired
    CatalogoRepository catalogoRepository;
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        productoFijoRepository.deleteById(id);
        List<ProductoFijo> listaProductoFijo = productoFijoRepository.findAll();
        model.addAttribute("listaProductoFijo", listaProductoFijo);
        return "gestionProductoFijo";
    }
    
    @GetMapping("/mostrarEditar/{id}")
    public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        ProductoFijo objProductoFijo = productoFijoRepository.findById(id).orElse(null);
        model.addAttribute("objProductoFijo", objProductoFijo);
        model.addAttribute("listaProductos", productoRepository.findAll());
        model.addAttribute("listaCatalogos", catalogoRepository.findAll());
        return "editarProductoFijo";
    }
    
    @PostMapping("/mostrarNuevo")
    public String mostrarNuevo(HttpServletRequest request, Model model) {
        ProductoFijo objProductoFijo = new ProductoFijo();
        model.addAttribute("objProductoFijo", objProductoFijo);
        model.addAttribute("listaProductos", productoRepository.findAll());
        model.addAttribute("listaCatalogos", catalogoRepository.findAll());
        return "nuevoProductoFijo";
    }
    
    @PostMapping("/registrar")
    public String registrar(HttpServletRequest request, Model model, @ModelAttribute("objProductoFijo") ProductoFijo objProductoFijo) {
        productoFijoRepository.save(objProductoFijo);
        List<ProductoFijo> listaProductoFijo = productoFijoRepository.findAll();
        model.addAttribute("listaProductoFijo", listaProductoFijo);
        return "redirect:/home/mostrarGestionProductoFijo";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(HttpServletRequest request, Model model, @ModelAttribute("objProductoFijo") ProductoFijo objProductoFijo) {
        productoFijoRepository.save(objProductoFijo);
        List<ProductoFijo> listaProductoFijo = productoFijoRepository.findAll();
        model.addAttribute("listaProductoFijo", listaProductoFijo);
        return "gestionProductoFijo";
    }
}
