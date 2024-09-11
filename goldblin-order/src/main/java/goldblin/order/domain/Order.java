package goldblin.order.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import goldblin.order.domain.enums.OrderStatus;
import goldblin.order.domain.enums.OrderType;
import goldblin.order.domain.vo.Address;
import goldblin.order.domain.vo.Money;
import goldblin.order.domain.vo.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_number", nullable = false, unique = true)
	private String orderNumber;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, columnDefinition = "VARCHAR(15)")
	private OrderType type;

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, columnDefinition = "VARCHAR(15)")
	private OrderStatus status;

	@Embedded
	private Quantity quantity;

	@Embedded
	private Money price;

	@Embedded
	private Address address;

	@Column(name = "orderedAt", nullable = false, columnDefinition = "DATE")
	private LocalDate orderedAt;

	public static Order create(String orderNumber, String type, Long memberId, Product product, BigDecimal quantity,
		String city, String street, String zipcode, LocalDate orderedAt) {
		return Order.builder()
			.orderNumber(orderNumber)
			.type(OrderType.of(type))
			.memberId(memberId)
			.product(product)
			.status(OrderStatus.COMPLETED)
			.quantity(Quantity.of(quantity))
			.price(product.calculatePrice(Quantity.of(quantity)))
			.address(Address.of(city, street, zipcode))
			.orderedAt(orderedAt)
			.build();
	}

}
