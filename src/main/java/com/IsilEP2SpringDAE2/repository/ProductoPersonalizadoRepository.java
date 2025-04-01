package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Catalogo;
import com.IsilEP2SpringDAE2.entity.ProductoPersonalizado;

@Repository
public interface ProductoPersonalizadoRepository extends JpaRepository<ProductoPersonalizado,Integer> {
	List<ProductoPersonalizado> findAll();
	List<ProductoPersonalizado> findByCatalogo(Catalogo catalogo);
	void deleteByCodigo (int codigo);
	ProductoPersonalizado findByNombre(String nombre);
	ProductoPersonalizado findByCodigo(int codigo);
}
