package com.IsilEP2SpringDAE2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Proveedor;
import com.IsilEP2SpringDAE2.entity.Ciudad;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {
	Optional<Proveedor> findByNombre(String nombre);
	List<Proveedor> findAll();
	List<Proveedor> findByCiudad(Ciudad ciudad);
	void deleteById(int codigo);
	Proveedor findById(int codigo);
}
