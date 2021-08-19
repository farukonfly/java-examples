package examples.hibernate.domainmodel.attributeconverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

class AttributeConverter_1 {
	@Entity(name = "Person")@Getter@Setter
	public static class Person {
		@Id
		private Long id;
		private String name;
		@Convert( converter = GenderConverter.class )
		public Gender gender;
	}


	@Converter
	public static class GenderConverter
	implements AttributeConverter<Gender, Character> {

		@Override
		public Character convertToDatabaseColumn( Gender value ) {
			if ( value == null ) {
				return null;
			}

			return value.getCode();
		}

		@Override
		public Gender convertToEntityAttribute( Character value ) {
			if ( value == null ) {
				return null;
			}

			return Gender.fromCode( value );
		}
	}


	public enum Gender {

		MALE( 'M' ),
		FEMALE( 'F' );

		private final char code;

		Gender(char code) {
			this.code = code;
		}

		public static Gender fromCode(char code) {
			if ( code == 'M' || code == 'm' ) {
				return MALE;
			}
			if ( code == 'F' || code == 'f' ) {
				return FEMALE;
			}
			throw new UnsupportedOperationException(
					"The code " + code + " is not supported!"
					);
		}

		public char getCode() {
			return this.code;
		}
	}
}
