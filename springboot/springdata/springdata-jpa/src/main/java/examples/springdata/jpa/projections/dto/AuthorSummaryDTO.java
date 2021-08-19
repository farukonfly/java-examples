package examples.springdata.jpa.projections.dto;

import lombok.AllArgsConstructor;

import lombok.Setter;

import lombok.Getter;

@Getter@Setter
@AllArgsConstructor
public class AuthorSummaryDTO {
	private String firstName;
	private String lastName;
}
