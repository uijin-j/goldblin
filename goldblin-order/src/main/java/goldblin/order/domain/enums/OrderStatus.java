package goldblin.order.domain.enums;

public enum OrderStatus {
	ORDER_PLACED("주문 접수"), // 고객이 주문을 완료하고 시스템에서 주문이 접수된 상태
	PAYMENT_PENDING("결제 대기"), // 결제가 아직 완료되지 않은 상태
	PAID("결제 완료"), // 결제가 정상적으로 완료된 상태
	READY("배송 준비 중"), // 주문이 준비 중인 상태
	SHIPPED("배송 중"), // 상품이 발송되어 배송이 진행 중인 상태
	DELIVERED("배송 완료"), // 상품이 성공적으로 배송된 상태
	CANCELED("주문 취소"), // 고객 또는 시스템에 의해 주문이 취소된 상태
	RETURN_REQUESTED("반품 요청"), // 고객이 반품을 요청한 상태
	RETURNED("반품 완료"), // 반품 절차가 완료된 상태
	REFUNDED("환불 완료"); // 고객에게 환불이 완료된 상태

	OrderStatus(String description) {
	}
}
