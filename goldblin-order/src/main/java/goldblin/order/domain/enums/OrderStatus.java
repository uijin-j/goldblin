package goldblin.order.domain.enums;

public enum OrderStatus {
	COMPLETED("주문 완료"), // 주문(판매/구매) 완료
	PAYMENT_SENT("송금 완료"), // 판매자에게 송금 완료
	PAYMENT_COMPLETED("입금 완료"), // 구매자가 입금 완료
	RECEIVED("수령 완료"), // 기업에서 상품 수령 완료
	DELIVERED("발송 완료"); // 구매자에게 발송 완료

	OrderStatus(String description) {
	}
}
