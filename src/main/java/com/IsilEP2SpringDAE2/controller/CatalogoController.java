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

import com.IsilEP2SpringDAE2.entity.Catalogo;
import com.IsilEP2SpringDAE2.entity.ProductoFijo;
import com.IsilEP2SpringDAE2.repository.CatalogoRepository;
import com.IsilEP2SpringDAE2.repository.DetalleCatalogoRepository;
import com.IsilEP2SpringDAE2.repository.ProductoFijoRepository;
import com.IsilEP2SpringDAE2.entity.DetalleCatalogo;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    CatalogoRepository catalogoRepository;
    
    @Autowired
    ProductoFijoRepository productoFijoRepository;
    
    @Autowired
    DetalleCatalogoRepository detalleCatalogoRepository;
    
    @GetMapping("/ver/{codigo}")
    public String ver(@PathVariable("codigo") int codigo, Model model) {
        Catalogo objCatalogoBD = catalogoRepository.findByCodigo(codigo);
        System.out.println("Código del catálogo: " + codigo);
       List<DetalleCatalogo> listaDetalleCatalogo = detalleCatalogoRepository.findByCatalogo(objCatalogoBD);
       System.out.println("Código del catálogo: " + objCatalogoBD);
       model.addAttribute("objCatalogo",objCatalogoBD);
       model.addAttribute("listaDetalleCatalogo",listaDetalleCatalogo);
        return "verDetalleCatalogo";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        catalogoRepository.deleteById(id);
        List<Catalogo> listaCatalogo = catalogoRepository.findAll();
        model.addAttribute("listaCatalogo", listaCatalogo);
        return "gestionCatalogo";
    }

    @GetMapping("/mostrarEditar/{id}")
    public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        Catalogo objCatalogo = catalogoRepository.findById(id).orElse(null);
        model.addAttribute("objCatalogo", objCatalogo);
        return "editarCatalogo";
    }

    @PostMapping("/mostrarNuevo")
    public String mostrarNuevo(HttpServletRequest request, Model model) {
        Catalogo objCatalogo = new Catalogo();
        model.addAttribute("objCatalogo", objCatalogo);
        return "nuevoCatalogo";
    }

    @PostMapping("/registrar")
    public String registrar(HttpServletRequest request, Model model, @ModelAttribute("objCatalogo") Catalogo objCatalogo) {
        catalogoRepository.save(objCatalogo);
        List<Catalogo> listaCatalogo = catalogoRepository.findAll();
        model.addAttribute("listaCatalogo", listaCatalogo);
        return "redirect:/home/mostrarGestionCatalogo";
    }

    @PostMapping("/actualizar")
    public String actualizar(HttpServletRequest request, Model model, @ModelAttribute("objCatalogo") Catalogo objCatalogo) {
        catalogoRepository.save(objCatalogo);
        List<Catalogo> listaCatalogo = catalogoRepository.findAll();
        model.addAttribute("listaCatalogo", listaCatalogo);
        return "gestionCatalogo";
    }
    
    @RequestMapping(value="/guardar", method=RequestMethod.GET, params="grabarCabecera")
	public String guardarCabecera(HttpServletRequest request, @ModelAttribute("objCatalogo")Catalogo objCatalogo, Model model) {
    	objCatalogo.setEstado("activo");
		catalogoRepository.save(objCatalogo);
		model.addAttribute("objCatalogo", objCatalogo);
		List<ProductoFijo> listaProductosFijos = productoFijoRepository.findAll();
		model.addAttribute("listaProductosFijos", listaProductosFijos);
		return "nuevoCatalogo";
	}
    
    @RequestMapping(value="/guardar", method=RequestMethod.GET, params="grabarDetalle")
	public String guardarDetalle(HttpServletRequest request, @ModelAttribute("objCatalogo")Catalogo objCatalogo, @RequestParam("codProductoFijo")int idproductoFijo,@RequestParam("descripcionProductoFijo") String desc, Model model) {
    	Catalogo catalogoBD = catalogoRepository.findByCodigo(objCatalogo.getCodigo());
		ProductoFijo productoFijoBD = productoFijoRepository.findByCodigo(idproductoFijo);
		DetalleCatalogo detalleCatalogo = new DetalleCatalogo();
		detalleCatalogo.setDescripcion(desc);
		detalleCatalogo.setProductoFijo(productoFijoBD);
		detalleCatalogo.setCatalogo(catalogoBD);
		detalleCatalogoRepository.save(detalleCatalogo);
		System.out.println("Código del catálogo: " + objCatalogo.getCodigo());

		System.out.printf("catalogo encontrado en BD: %s",catalogoBD);
		List<DetalleCatalogo> listaDetallesCatalogo = detalleCatalogoRepository.findByCatalogo(catalogoBD);
		model.addAttribute("listaDetallesCatalogo", listaDetallesCatalogo);
		model.addAttribute("objCatalogo", catalogoBD);
		List<ProductoFijo> listaProductosFijos = productoFijoRepository.findAll();
		model.addAttribute("listaProductosFijos", listaProductosFijos);		
		return "nuevoCatalogo";
	}
}
