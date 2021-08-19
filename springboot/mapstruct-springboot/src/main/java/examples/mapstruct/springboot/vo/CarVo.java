package examples.mapstruct.springboot.vo;

import java.util.List;

import lombok.Data;

@Data
public class CarVo {
	private Long id ;
	private String vin;
	private Double price;
	private String totalPrice;
	private String publishDate;
	private String brandName;
	private Boolean hasPart ;
	private DriverVo driver_1 ;
	private List<PartVo> partVos ;

}
