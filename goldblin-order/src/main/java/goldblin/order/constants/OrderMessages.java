package goldblin.order.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMessages {
	/**
	 * 200 번대 성공 메시지
	 */
	public static final String ORDER_SUCCESS = "주문이 완료되었습니다.";

	/**
	 * 400 번대 에러 메시지
	 */
	public static final String BLANK_ORDER_TYPE = "주문 유형은 필수값입니다.";
	public static final String NULL_PRODUCT = "상품 식별자는 필수값입니다.";
	public static final String NULL_QUANTITY = "수량은 필수값입니다.";
	public static final String BLANK_ADDRESS = "배송지 정보는 필수값입니다.";
	public static final String INVALID_ORDER_TYPE = "유효하지 않은 주문 유형입니다.";
	public static final String INVALID_PRODUCT = "해당 식별자를 가진 상품이 존재하지 않습니다.";
	public static final String NON_POSITIVE_QUANTITY = "수량은 0보다 커야합니다.";
	public static final String TOO_MANY_DECIMAL_PLACES = "수량은 최대 소수점 2자리까지 처리할 수 있습니다.";
	public static final String NEGATIVE_MONEY = "금액은 음수가 될 수 없습니다.";
}
