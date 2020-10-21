package br.net.comexport.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.comexport.api.model.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>, OrdersRepositoryQuery {
	
}
