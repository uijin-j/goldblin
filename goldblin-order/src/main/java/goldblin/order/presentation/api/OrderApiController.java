package goldblin.order.presentation.api;

import static goldblin.order.constants.OrderMessages.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import goldblin.order.business.OrderService;
import goldblin.order.dto.req.OrderPlaceReq;
import goldblin.order.presentation.docs.OrderApiControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApiController implements OrderApiControllerDoc {
	private final OrderService orderService;

	@PostMapping
	@ResponseStatus(CREATED)
	public ApiResponse<Void> place(@RequestBody @Valid OrderPlaceReq request) {
		orderService.place(request);
		return ApiResponse.of(CREATED, ORDER_SUCCESS, null);
	}
}
