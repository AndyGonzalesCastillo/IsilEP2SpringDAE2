package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.IsilEP2SpringDAE2.entity.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo,Integer>{
	List<Catalogo> findAll();
	List<Catalogo> findByEstado(String estado);
	Catalogo findByCodigo(int codigo);
	Catalogo findByNombre(String nombre);
	void deleteByCodigo(int codigo);
}
