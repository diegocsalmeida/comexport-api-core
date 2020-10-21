package br.net.comexport.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.comexport.api.model.Product;
import br.net.comexport.api.repository.ProductsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Pedidos / Core")
@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	private ProductsRepository productsRepository;

	@ApiOperation(value = "Traz a listagem de produtos disponíveis")
	@GetMapping
	public List<Product> listAllProducts() {
		return productsRepository.findAll();
	}
	
}
