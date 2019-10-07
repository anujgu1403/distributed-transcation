package com.spring.batch.demo.publication;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "publication")
@Data
public class Publication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String publication;

	private String title;
	private String year;
	private String type;
	private String hero;
	private String genre;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "author_publication", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "publication_id", referencedColumnName = "id"))
	private Set<Author> authors = new HashSet<Author>();
}
