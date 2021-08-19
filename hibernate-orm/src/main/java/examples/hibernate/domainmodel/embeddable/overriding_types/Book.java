package examples.hibernate.domainmodel.embeddable.overriding_types;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Book")@Getter@Setter
@AttributeOverrides({
	@AttributeOverride(
			name = "ebookPublisher.name",
			column = @Column(name = "ebook_publisher_name")
			),
	@AttributeOverride(
			name = "paperBackPublisher.name",
			column = @Column(name = "paper_back_publisher_name")
			)
})
@AssociationOverrides({
	@AssociationOverride(
			name = "ebookPublisher.country",
			joinColumns = @JoinColumn(name = "ebook_publisher_country_id")
			),
	@AssociationOverride(
			name = "paperBackPublisher.country",
			joinColumns = @JoinColumn(name = "paper_back_publisher_country_id")
			)
})

class Book {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String author;
	private Publisher ebookPublisher;
	private Publisher paperBackPublisher;

}
/**
 * 
 * create table Book (
    id bigint not null,
    author varchar(255),
    ebook_publisher_name varchar(255),
    paper_back_publisher_name varchar(255),
    title varchar(255),
    ebook_publisher_country_id bigint,
    paper_back_publisher_country_id bigint,
    primary key (id)
)

alter table Book
    add constraint FKm39ibh5jstybnslaoojkbac2g
    foreign key (ebook_publisher_country_id)
    references Country

alter table Book
    add constraint FK7kqy9da323p7jw7wvqgs6aek7
    foreign key (paper_back_publisher_country_id)
    references Country
 * 
 * 
 * 
 */
