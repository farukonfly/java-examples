package examples.hibernate.domainmodel.entity_proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

@Entity( name = "Book" )
@Proxy(proxyClass = Identifiable.class)
public final class  Book implements Identifiable {

	@Id
	private Long id;

	private String title;

	private String author;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	//Other getters and setters omitted for brevity

}