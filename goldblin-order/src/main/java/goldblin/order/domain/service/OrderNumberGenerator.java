package goldblin.order.domain.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import goldblin.order.domain.enums.OrderType;
import goldblin.order.infrastructure.persistence.OrderNumberSequenceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderNumberGenerator {
	public static final String ORDER_NUMBER_FORMAT = "%s-%s-%06d";
	private static final String DATE_FORMAT = "yyyyMMdd";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private final OrderNumberSequenceRepository orderNumberSequenceRepository;

	/**
	 * 주문 번호 생성 메서드
	 * DATE-TYPE-NUMBER 형식으로 주문번호 생성 (ex. 20240911-SALE-00001)
	 */
	public String generate(String orderType, LocalDate orderedAt) {
		Long nextSequence = orderNumberSequenceRepository.getNextSequence(orderedAt);
		return String.format(ORDER_NUMBER_FORMAT,
			orderedAt.format(DATE_TIME_FORMATTER),
			OrderType.of(orderType),
			nextSequence
		);
	}
}
