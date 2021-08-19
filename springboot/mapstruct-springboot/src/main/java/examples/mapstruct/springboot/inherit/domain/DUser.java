package examples.mapstruct.springboot.inherit.domain;

import examples.mapstruct.springboot.inherit.AbstractDomain;
import lombok.Data;

@Data
public class DUser extends AbstractDomain {
	private String username ;
}
