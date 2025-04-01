package com.IsilEP2SpringDAE2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsilEP2SpringDAE2.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Integer>{
	List<Delivery> findByEstado(String Estado);
	Delivery findById(int id);
}
