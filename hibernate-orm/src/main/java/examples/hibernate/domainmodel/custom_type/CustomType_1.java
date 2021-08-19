package examples.hibernate.domainmodel.custom_type;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.CharacterTypeDescriptor;
import org.hibernate.type.descriptor.sql.CharTypeDescriptor;

import lombok.Getter;
import lombok.Setter;

class CustomType_1 {
	@Entity(name = "Person")@Getter@Setter
	public static class Person {

		@Id
		private Long id;
		private String name;

		@Type(type = "examples.hibernate.domainmodel.custom_type.CustomType_1.GenderType")
		public Gender gender;

	}

	static class GenderType extends AbstractSingleColumnStandardBasicType<Gender> {

		public static final GenderType INSTANCE = new GenderType();

		public GenderType() {
			super(CharTypeDescriptor.INSTANCE, GenderJavaTypeDescriptor.INSTANCE);
		}

		@Override
		public String getName() {
			return "gender";
		}

		@Override
		protected boolean registerUnderJavaType() {
			return true;
		}
	}

	static class GenderJavaTypeDescriptor extends AbstractTypeDescriptor<Gender> {

		public static final GenderJavaTypeDescriptor INSTANCE = new GenderJavaTypeDescriptor();

		protected GenderJavaTypeDescriptor() {
			super(Gender.class);
		}

		@Override
		public String toString(Gender value) {
			return value == null ? null : value.name();
		}

		@Override
		public Gender fromString(String string) {
			return string == null ? null : Gender.valueOf(string);
		}

		@Override
		public <X> X unwrap(Gender value, Class<X> type, WrapperOptions options) {
			return CharacterTypeDescriptor.INSTANCE.unwrap(value == null ? null : value.getCode(), type, options);
		}

		@Override
		public <X> Gender wrap(X value, WrapperOptions options) {
			return Gender.fromCode(CharacterTypeDescriptor.INSTANCE.wrap(value, options));
		}
	}

	public enum Gender {

		MALE('M'), FEMALE('F');

		private final char code;

		Gender(char code) {
			this.code = code;
		}

		public static Gender fromCode(char code) {
			if (code == 'M' || code == 'm') {
				return MALE;
			}
			if (code == 'F' || code == 'f') {
				return FEMALE;
			}
			throw new UnsupportedOperationException("The code " + code + " is not supported!");
		}

		public char getCode() {
			return this.code;
		}
	}
}
