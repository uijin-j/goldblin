package goldblin.order.dto.req;

import static goldblin.order.constants.OrderMessages.*;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderPlaceReq(
	@NotBlank(message = BLANK_ORDER_TYPE)
	String orderType,
	@NotNull(message = NULL_PRODUCT)
	Long productId,
	@NotNull(message = NULL_QUANTITY)
	BigDecimal quantity,
	@NotBlank(message = BLANK_ADDRESS)
	String city,
	@NotBlank(message = BLANK_ADDRESS)
	String street,
	@NotBlank(message = BLANK_ADDRESS)
	String zipcode
) {
}
