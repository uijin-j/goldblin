package goldblin.order.infrastructure.persistence;

import java.time.LocalDate;

public interface OrderNumberSequenceRepository {
	Long getNextSequence(LocalDate orderedAt);
}
