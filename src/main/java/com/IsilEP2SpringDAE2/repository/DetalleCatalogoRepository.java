package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Catalogo;
import com.IsilEP2SpringDAE2.entity.DetalleCatalogo;

@Repository
public interface DetalleCatalogoRepository extends JpaRepository<DetalleCatalogo,Integer>{
	List<DetalleCatalogo> findByCatalogo(Catalogo catalogo);
}
