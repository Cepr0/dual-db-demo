package io.github.cepr0.dualdb.first.model;

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
@Table(name = "users")
public class User implements Serializable {
	@Id @GeneratedValue private UUID id;
	private String name;

	public User(String name) {
		this.name = name;
	}

	@PrePersist
	public void prePersist() {
		if (id != null) id = UUID.randomUUID();
	}
}
