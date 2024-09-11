package goldblin.order.presentation.docs;

import goldblin.order.dto.req.OrderPlaceReq;
import goldblin.order.presentation.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;

public interface OrderApiControllerDoc {
	@Operation(summary = "주문하기")
	ApiResponse<Void> place(OrderPlaceReq request);
}
