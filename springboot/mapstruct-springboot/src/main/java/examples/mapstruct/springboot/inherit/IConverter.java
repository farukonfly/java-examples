package examples.mapstruct.springboot.inherit;

import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

public interface IConverter<E extends AbstractEntity,D extends AbstractDomain> {
	@Named("d2t")
	void d2t(D domain,@MappingTarget E entity);
	@Named("toDomain")
	D toDomain(E e);
	@Named("toEntity")
	E toEntity(D d);
}
