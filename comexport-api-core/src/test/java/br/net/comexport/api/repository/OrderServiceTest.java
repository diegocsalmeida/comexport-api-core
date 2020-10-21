package br.net.comexport.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.net.comexport.api.model.Order;
import br.net.comexport.api.model.enumeration.SalesChannel;
import br.net.comexport.api.model.enumeration.Status;
import br.net.comexport.api.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
    private OrderService orderService;
    
    @Test
    void injectedComponentsAreNotNull(){
      assertThat(orderService).isNotNull();
    }
    
    @Test
    public void whenCreate_thenPersistData () {
        Order order = new Order(1L, 1L, SalesChannel.LOJA_FISICA);
        order = this.orderService.save(order);
        
        assertThat(order.getId_user()).isEqualTo(1L);
        assertThat(order.getId_product()).isEqualTo(1L);
        assertThat(order.getSalesChannel()).isEqualTo(SalesChannel.LOJA_FISICA);
    }
    
    @Test
    public void checkStatusAndPricefor_PARCEIROS () {
        Order order = new Order(1L, 1L, SalesChannel.PARCEIROS);
        order = this.orderService.save(order);
        
        assertThat(order.getId_user()).isEqualTo(1L);
        assertThat(order.getId_product()).isEqualTo(1L);
        assertThat(order.getSalesChannel()).isEqualTo(SalesChannel.PARCEIROS);
        assertThat(order.getStatus()).isEqualTo(Status.AGUARDANDO_RETIRADA_PARCEIRO);
        assertThat(order.getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN)).isEqualByComparingTo(new BigDecimal(1098.14).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }
    
    @Test
    public void checkStatusAndPricefor_LOJA_FISICA () {
        Order order = new Order(1L, 1L, SalesChannel.LOJA_FISICA);
        order = this.orderService.save(order);
        
        assertThat(order.getId_user()).isEqualTo(1L);
        assertThat(order.getId_product()).isEqualTo(1L);
        assertThat(order.getSalesChannel()).isEqualTo(SalesChannel.LOJA_FISICA);
        assertThat(order.getStatus()).isEqualTo(Status.ENTREGUE);
        assertThat(order.getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN)).isEqualByComparingTo(new BigDecimal(1000).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }
    
    @Test
    public void checkStatusAndPricefor_ECOMMERCE () {
        Order order = new Order(1L, 1L, SalesChannel.ECOMMERCE);
        order = this.orderService.save(order);
        
        assertThat(order.getId_user()).isEqualTo(1L);
        assertThat(order.getId_product()).isEqualTo(1L);
        assertThat(order.getSalesChannel()).isEqualTo(SalesChannel.ECOMMERCE);
        assertThat(order.getStatus()).isEqualTo(Status.AGUARDANDO_ENTREGA);
        assertThat(order.getPrice().setScale(2, BigDecimal.ROUND_HALF_EVEN)).isEqualByComparingTo(new BigDecimal(1053.70).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }
}


