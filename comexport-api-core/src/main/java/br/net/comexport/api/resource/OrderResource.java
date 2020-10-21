package br.net.comexport.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.net.comexport.api.model.Order;
import br.net.comexport.api.repository.filter.OrderFilter;
import br.net.comexport.api.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Pedidos / Core")
@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "Traz a listagem de pedidos, pode-se usar filtros para busca por qualquer campo da tabela \"orders\". ")
	@GetMapping
	public Page<Order> search(OrderFilter orderFilter, Pageable pageable) {
		return orderService.search(orderFilter, pageable);
	}

	@ApiOperation(value = "Faz a inclusão de um novo pedido")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order create(@Valid @RequestBody Order order) {
		return orderService.save(order);
	}
}
