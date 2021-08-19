package examples.mapstruct.springboot.inherit.entity;

import examples.mapstruct.springboot.inherit.AbstractEntity;
import lombok.Data;

@Data
public class EUser extends AbstractEntity {
	private String username ;
}
