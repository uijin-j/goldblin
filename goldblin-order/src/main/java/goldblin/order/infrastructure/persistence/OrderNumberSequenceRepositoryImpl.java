package goldblin.order.infrastructure.persistence;

import static goldblin.order.domain.QOrder.*;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderNumberSequenceRepositoryImpl implements OrderNumberSequenceRepository {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Long getNextSequence(LocalDate orderedAt) {
		return countOrdersPlacedOn(orderedAt) + 1L;
	}

	private Long countOrdersPlacedOn(LocalDate orderedAt) {
		return jpaQueryFactory
			.select(order.id.count())
			.from(order)
			.where(orderedAtEq(orderedAt))
			.fetchOne();
	}

	private static BooleanExpression orderedAtEq(LocalDate orderedAt) {
		return order.orderedAt.eq(orderedAt);
	}
}
