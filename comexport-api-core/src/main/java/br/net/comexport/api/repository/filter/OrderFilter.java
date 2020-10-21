package br.net.comexport.api.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import br.net.comexport.api.model.enumeration.SalesChannel;
import br.net.comexport.api.model.enumeration.Status;

public class OrderFilter {

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAtFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAtTo;

	private Long id;
	private Long idProduct;
	private Long idUser;

	private BigDecimal priceFrom;
	private BigDecimal priceTo;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedAtFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedAtTo;

	private SalesChannel salesChannel;
	private Status status;

	public LocalDateTime getCreatedAtFrom() {
		return createdAtFrom;
	}

	public void setCreatedAtFrom(LocalDateTime createdAtFrom) {
		this.createdAtFrom = createdAtFrom;
	}

	public LocalDateTime getCreatedAtTo() {
		return createdAtTo;
	}

	public void setCreatedAtTo(LocalDateTime createdAtTo) {
		this.createdAtTo = createdAtTo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	public LocalDateTime getUpdatedAtFrom() {
		return updatedAtFrom;
	}

	public void setUpdatedAtFrom(LocalDateTime updatedAtFrom) {
		this.updatedAtFrom = updatedAtFrom;
	}

	public LocalDateTime getUpdatedAtTo() {
		return updatedAtTo;
	}

	public void setUpdatedAtTo(LocalDateTime updatedAtTo) {
		this.updatedAtTo = updatedAtTo;
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
}
