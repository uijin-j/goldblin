package goldblin.order.domain.enums;

import static goldblin.order.constants.OrderMessages.*;
import static java.util.Objects.*;

import java.util.Arrays;

public enum OrderType {
	SELL("판매"),
	BUY("구매");

	OrderType(String description) {
	}

	public static OrderType of(String value) {
		if (isNull(value)) {
			throw new IllegalArgumentException(BLANK_ORDER_TYPE);
		}

		return Arrays.stream(OrderType.values())
			.filter(status -> value.toUpperCase().equals(status.name()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_TYPE));
	}
}
