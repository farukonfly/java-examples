package examples.mapstruct.springboot.inherit;

import org.mapstruct.Mapper;

import examples.mapstruct.springboot.inherit.domain.DUser;
import examples.mapstruct.springboot.inherit.entity.EUser;

@Mapper(componentModel = "spring")
public abstract class UserConverter implements IConverter<EUser, DUser>  {

}
