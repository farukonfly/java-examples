package examples.hibernate.locking.optimistic.optimistic_lock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class OptimisticLockEntities {
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Phone")
	static class Phone {

		@Id
		private Long id;

		@Column(name = "`number`")
		private String number;

		@OptimisticLock( excluded = true )
		private long callCount;

		@Version
		private Long version;

		//Getters and setters are omitted for brevity

		public void incrementCallCount() {
			this.callCount++;
		}
	}
}
