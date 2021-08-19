package examples.mapstruct.springboot.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import examples.mapstruct.springboot.dto.CarDto;
import examples.mapstruct.springboot.dto.DriverDto;
import examples.mapstruct.springboot.vo.CarVo;
import examples.mapstruct.springboot.vo.DriverVo;

@Mapper
public abstract class CarMapper {
	public static final CarMapper instance = Mappers.getMapper(CarMapper.class);

	@Mappings(value= {
			@Mapping(source = "totalPrice",target = "totalPrice",numberFormat = "#.00"),
			@Mapping(source="publishDate",target="publishDate",dateFormat = "yyyy--MM-dd HH:mm:ss",ignore = true),
			@Mapping(source="driver",target="driver_1"),
			@Mapping(source="partDtos",target="partVos")
	})
	public abstract CarVo toVo(CarDto dto);

	protected abstract DriverVo map(DriverDto dto);
	@AfterMapping
	protected void dto2After(CarDto dto, @MappingTarget CarVo vo) {
		System.out.println();
	}

}
