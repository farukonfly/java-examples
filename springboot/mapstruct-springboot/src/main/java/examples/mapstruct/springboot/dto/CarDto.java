package examples.mapstruct.springboot.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
	private Long id ;
	private String vin;
	private double price;
	private double totalPrice;
	private Date publishDate;
	private String brand;
	private List<PartDto> partDtos ;
	private DriverDto driver ;

}
