package goldblin.order.domain.vo;

import static goldblin.order.constants.OrderMessages.*;
import static java.util.Objects.*;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public final class Quantity {
	public static final int MAX_DECIMAL_PLACE = 2;
	@Column(name = "quantity", nullable = false, columnDefinition = "DECIMAL(10, 2)")
	private BigDecimal quantity;

	public static Quantity of(BigDecimal quantity) {
		validate(quantity);
		return new Quantity(quantity);
	}

	private static void validate(BigDecimal quantity) {
		// 1. null 체크
		if (isNull(quantity)) {
			throw new IllegalArgumentException(NULL_QUANTITY);
		}

		// 2. 양수 확인 (음수 또는 0일 수 없음)
		if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException(NON_POSITIVE_QUANTITY);
		}

		// 3. 소수점 자리 수가 2자리 초과인지 확인
		if (quantity.scale() > MAX_DECIMAL_PLACE) {
			throw new IllegalArgumentException(TOO_MANY_DECIMAL_PLACES);
		}
	}

	public int multiply(int money) {
		BigDecimal decimalMoney = BigDecimal.valueOf(money);
		return quantity.multiply(decimalMoney).intValue();
	}
}
