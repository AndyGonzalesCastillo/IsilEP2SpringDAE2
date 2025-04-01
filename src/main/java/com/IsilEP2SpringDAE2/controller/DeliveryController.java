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

import com.IsilEP2SpringDAE2.repository.PedidoRepository;
import com.IsilEP2SpringDAE2.entity.Delivery;
import com.IsilEP2SpringDAE2.repository.DeliveryRepository;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    DeliveryRepository deliveryRepository;
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        deliveryRepository.deleteById(id);
        List<Delivery> listaDelivery = deliveryRepository.findAll();
        model.addAttribute("listaDelivery", listaDelivery);
        return "gestionDelivery";
    }
    
    @GetMapping("/mostrarEditar/{id}")
    public String mostrarEditar(HttpServletRequest request, @PathVariable("id") int id, Model model) {
        Delivery objDelivery = deliveryRepository.findById(id);
        model.addAttribute("objDelivery", objDelivery);
        model.addAttribute("listaDelivery", deliveryRepository.findAll());
        return "editarDelivery";
    }

    @PostMapping("/mostrarNuevo")
    public String mostrarNuevo(HttpServletRequest request, Model model) {
        Delivery objDelivery = new Delivery();
        model.addAttribute("objDelivery", objDelivery);
        model.addAttribute("listaDelivery", deliveryRepository.findAll());
        return "nuevoDelivery";
    }
    
    @PostMapping("/registrar")
    public String registrar(HttpServletRequest request, Model model, @ModelAttribute("objDelivery") Delivery objDelivery) {
        deliveryRepository.save(objDelivery);
        List<Delivery> listaDelivery = deliveryRepository.findAll();
        model.addAttribute("listaDelivery", listaDelivery);
        return "redirect:/home/mostrarGestionDelivery";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(HttpServletRequest request, Model model, @ModelAttribute("objDelivery") Delivery objDelivery) {
        deliveryRepository.save(objDelivery);
        List<Delivery> listaDelivery = deliveryRepository.findAll();
        model.addAttribute("listaDelivery", listaDelivery);
        return "gestionDelivery";
    }
    
}


