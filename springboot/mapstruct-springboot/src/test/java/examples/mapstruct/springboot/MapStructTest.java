package examples.mapstruct.springboot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import examples.mapstruct.springboot.dto.CarDto;
import examples.mapstruct.springboot.dto.DriverDto;
import examples.mapstruct.springboot.dto.PartDto;
import examples.mapstruct.springboot.mapper.CarMapper;

@SpringBootTest
public class MapStructTest {

	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}

	@Test
	public void test() {
		PartDto part1 = new PartDto();
		PartDto part2 = new PartDto();
		part1.setPartId(1L);
		part1.setPartName("方向盘");
		part2.setPartId(2L);
		part2.setPartName("轮子");
		List<PartDto> list = Arrays.asList(part1,part2);

		DriverDto d = new DriverDto() ;
		d.setId(1L);
		d.setName("司机");

		CarDto carDto = CarDto.builder().id(33L).vin("vin13234").price(32432.23d)
				.totalPrice(32423.33d).publishDate(new Date()).brand("本田")
				.partDtos(list).driver(d).build();

		System.out.println(CarMapper.instance.toVo(carDto));
		System.out.println(CarMapper.instance);
		System.out.println(CarMapper.instance);
		System.out.println(CarMapper.instance);
		System.out.println(CarMapper.instance);
		System.out.println(CarMapper.instance);
		System.out.println(CarMapper.instance);
	}

}
