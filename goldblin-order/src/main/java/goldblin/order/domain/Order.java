package goldblin.order.domain;

import java.math.BigDecimal;

import goldblin.order.domain.enums.OrderStatus;
import goldblin.order.domain.enums.OrderType;
import goldblin.order.domain.vo.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "order")
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

	@Enumerated
	@Column(name = "type", nullable = false, columnDefinition = "VARCHAR(15)")
	private OrderType type;

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Enumerated
	@Column(name = "status", nullable = false, columnDefinition = "VARCHAR(15)")
	private OrderStatus status;

	@Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10, 2)")
	private BigDecimal price;

	@Embedded
	private Address address;

}
