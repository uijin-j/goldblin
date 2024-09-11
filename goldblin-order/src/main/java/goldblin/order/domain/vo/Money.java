package goldblin.order.domain.vo;

import static goldblin.order.constants.OrderMessages.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public final class Money {
	public static final int ZERO = 0;
	@Column(name = "price", nullable = false)
	private int money;

	public static Money of(int money) {
		validate(money);
		return new Money(money);
	}

	private static void validate(int money) {
		if (money < ZERO) {
			throw new IllegalArgumentException(NEGATIVE_MONEY);
		}
	}

	public Money multiply(Quantity quantity) {
		return Money.of(quantity.multiply(money));
	}
}
