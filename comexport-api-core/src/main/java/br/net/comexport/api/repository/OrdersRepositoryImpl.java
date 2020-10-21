package br.net.comexport.api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.net.comexport.api.model.Order;
import br.net.comexport.api.repository.filter.OrderFilter;

public class OrdersRepositoryImpl implements OrdersRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Order> filter(OrderFilter orderFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		
		Root<Order> root = criteria.from(Order.class);
		
		Predicate[] predicates = createRestrictions(orderFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Order> query = manager.createQuery(criteria);
		addRestrictionPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(orderFilter));
	}

	private Long total(OrderFilter orderFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Order> root = query.from(Order.class);
		
		Predicate[] predicates = createRestrictions(orderFilter, builder, root);
		query.where(predicates);
		
		query.select(builder.count(root));
		return manager.createQuery(query).getSingleResult();
	}

	private void addRestrictionPagination(TypedQuery<Order> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalItensPerPage = pageable.getPageSize();
		int firstItemPage = currentPage * totalItensPerPage;
		
		query.setFirstResult(firstItemPage);
		query.setMaxResults(totalItensPerPage);
		
	}
	
	private Predicate[] createRestrictions(OrderFilter orderFilter, CriteriaBuilder builder, Root<Order> root) {
		
		List<Predicate> predicates = new ArrayList<>();

		//Filter by SALES_CHANNEL
		if(!StringUtils.isEmpty(orderFilter.getSalesChannel())) {
			predicates.add(builder.equal(builder.upper(root.get("salesChannel")), orderFilter.getSalesChannel()));
		}

		//Filter by STATUS
		if(!StringUtils.isEmpty(orderFilter.getStatus())) {
			predicates.add(builder.equal(builder.upper(root.get("status")), orderFilter.getStatus()));
		}
		//Filter by ID
		if(orderFilter.getId() != null) {
			predicates.add(builder.equal(root.get("id"), orderFilter.getId()));
		}
		
		//Filter by ID_USER
		if(orderFilter.getIdUser() != null) {
			predicates.add(builder.equal(root.get("id_user"), orderFilter.getIdUser()));
		}
		
		
		//Filter by ID_PRODUCT
		if(orderFilter.getIdProduct() != null) {
			predicates.add(builder.equal(root.get("id_product"), orderFilter.getIdProduct()));
		}
		
		//Filter by CREATE_AT_FROM
		if(orderFilter.getCreatedAtFrom() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), orderFilter.getCreatedAtFrom()));
		}
		
		//Filter by CREATE_AT_TO
		if(orderFilter.getCreatedAtTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), orderFilter.getCreatedAtTo()));
		}
		
		//Filter by UPDATE_AT_FROM
		if(orderFilter.getUpdatedAtFrom() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("updatedAt"), orderFilter.getUpdatedAtFrom()));
		}
		
		//Filter by UPDATE_AT_TO
		if(orderFilter.getUpdatedAtTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("updatedAt"), orderFilter.getUpdatedAtTo()));
		}
		
		//Filter by PRICE >=
		if(orderFilter.getPriceFrom() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("price"), orderFilter.getPriceFrom()));
		}
		
		//Filter by PRICE <=
		if(orderFilter.getPriceTo() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("price"), orderFilter.getPriceTo()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
