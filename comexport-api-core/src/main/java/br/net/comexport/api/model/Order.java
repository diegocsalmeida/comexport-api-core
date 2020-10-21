package br.net.comexport.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.net.comexport.api.model.enumeration.SalesChannel;
import br.net.comexport.api.model.enumeration.Status;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Cannot be null.")
	private Long id_user;

	@NotNull(message = "Cannot be null.")
	private Long id_product;

	@JsonProperty(access = Access.READ_ONLY)
	private BigDecimal price;

	@NotNull(message = "Cannot be null.")
	@Enumerated(EnumType.STRING)
	private SalesChannel salesChannel;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updatedAt;

	public Order() {
	}
	
	public Order(Long idUser, Long idProduct, SalesChannel salesChannel) {
		this.id_user = idUser;
		this.id_product = idProduct;
		this.salesChannel = salesChannel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public Long getId_product() {
		return id_product;
	}

	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public SalesChannel getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(SalesChannel salesChannel) {
		this.salesChannel = salesChannel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
