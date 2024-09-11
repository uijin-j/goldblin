package goldblin.order.business;

import static goldblin.order.constants.OrderMessages.*;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goldblin.order.domain.Order;
import goldblin.order.domain.Product;
import goldblin.order.domain.service.OrderNumberGenerator;
import goldblin.order.dto.req.OrderPlaceReq;
import goldblin.order.infrastructure.persistence.OrderRepository;
import goldblin.order.infrastructure.persistence.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OrderNumberGenerator orderNumberGenerator;

	@Transactional
	public void place(OrderPlaceReq request) {
		// TODO: gRPC 통신으로 유저 정보 받아오기
		Long memberId = 1L;

		// 주문일자 생성 (현재시간)
		LocalDate orderedAt = LocalDate.now();

		// 주문번호 생성
		String orderNumber = orderNumberGenerator.generate(request.orderType(), orderedAt);

		// 상품 조회
		Product product = getProduct(request);

		// 주문 생성
		Order order = Order.create(
			orderNumber,
			request.orderType(),
			memberId,
			product,
			request.quantity(),
			request.city(),
			request.street(),
			request.zipcode(),
			orderedAt
		);

		orderRepository.save(order);
	}

	private Product getProduct(OrderPlaceReq request) {
		return productRepository.findById(request.productId())
			.orElseThrow(() -> new EntityNotFoundException(INVALID_PRODUCT));
	}
}
