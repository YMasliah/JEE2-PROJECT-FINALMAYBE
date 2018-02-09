/**
 * 
 */
package database.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author masliah yann
 *
 */
@Entity(name = "Activities")
@Table(name = "activities")
public class Activity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@NotNull
	@Column(name = "year", nullable = false)
	private Integer year;
	@NotNull
	@Column(name = "kind", nullable = false)
	@Enumerated(EnumType.STRING)
	private Kind kind;
	@NotNull
	@Column(name = "title", nullable = false)
	private String title;
	@NotNull
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "webSite")
	private String webSite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CV")
	private CV cv;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public CV getCv() {
		return cv;
	}

	public void setCv(CV cv) {
		this.cv = cv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Number)
			if (this.getId().equals(obj))
				return true;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", year=" + year + ", kind=" + kind + ", title=" + title + ", description="
				+ description + ", webSite=" + webSite + ", cv=" + cv + "]";
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

}
