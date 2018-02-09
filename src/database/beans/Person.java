/**
 * 
 */
package database.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author masliah yann
 *
 * rajout d'une id pour la recherche, sachant que la cl� primaire est l'adresse mail.
 * elle ne peut pas etre vu par tout les utilisateur donc elle ne peut pas etre utiliser pour la recherche.
 */
//@Named("person")
@Entity(name = "Persons")
@Table(name = "persons")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "eMail", unique = true)
	private String eMail;
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	@NotNull
	@Column(name = "surname", nullable = false)
	private String surname;
	@Column(name = "webAdress")
	private String webAdress;
	@Column(name = "birthDate")
	private Date birthDate;
	@NotNull
	@Column(name = "password", nullable = false)
	private String password;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CV")
	private CV cv = new CV();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		return true;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getWebAdress() {
		return webAdress;
	}
	public void setWebAdress(String webAdress) {
		this.webAdress = webAdress;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CV getCv() {
		return cv;
	}
	public void setCv(CV cv) {
		this.cv = cv;
	}
	
	@Override
	public String toString() {
		return "Person [eMail=" + eMail + ", name=" + name + ", surname=" + surname + ", webAdress="
				+ webAdress + ", birthDate=" + birthDate + ", password=" + password + ", cv=" + cv + "]";
	}
}