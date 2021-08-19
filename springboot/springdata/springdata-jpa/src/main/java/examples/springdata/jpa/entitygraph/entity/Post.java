package examples.springdata.jpa.entitygraph.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * 帖子
 * @author farukon
 *
 */
@NamedEntityGraph(
		name = "post-entity-graph",
		attributeNodes = {
				@NamedAttributeNode("subject"),
				@NamedAttributeNode("user"),
				@NamedAttributeNode("comments"),
		}
		)
@NamedEntityGraph(
		name = "post-entity-graph-with-comment-users",
		attributeNodes = {
				@NamedAttributeNode("subject"),
				@NamedAttributeNode("user"),
				@NamedAttributeNode(value = "comments", subgraph = "comments-subgraph"),
		},
		subgraphs = {
				@NamedSubgraph(
						name = "comments-subgraph",
						attributeNodes = {
								@NamedAttributeNode("user")
						}
						)
		}
		)
@Entity
@Table
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY)
	private String subject;

	@OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	//@Basic(fetch = FetchType.LAZY)
	//@ManyToOne
	@JoinColumn
	private User user;







	public Post(Long id, String subject) {
		super();
		this.id = id;
		this.subject = subject;
	}

	public Post(String subject, User user) {
		super();
		this.subject = subject;
		this.user = user;
	}

	public Post() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}