package io.github.cepr0.dualdb.second.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Cepr0, 2017-12-21
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	@Id @GeneratedValue private UUID id;
	private Integer number;

	public Order(Integer number) {
		this.number = number;
	}

	@PrePersist
	public void prePersist() {
		if (id != null) id = UUID.randomUUID();
	}
}
