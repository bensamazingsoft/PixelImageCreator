
package com.ben.pixcreator.server.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.ben.pixcreator.server.PasswordUtils;

@Entity
@Table(name = "USERINFO")
@NamedQueries(
      {
		  @NamedQuery(
			      name = "findUserByEmail",
			      query = "select u "
					  + "from User u "
					  + "where u.email = :email",
			      readOnly = true,
			      timeout = 1
		  ),
		  @NamedQuery(
			      name = "emailExists",
			      query = "select email "
					  + "from User u "
					  + "where u.email = :email",
			      readOnly = true,
			      timeout = 1
		  )
      }
)
public class User
{

      @Id
      @Column(name = "ID")
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      private int    id;

      @Column(name = "EMAIL", nullable = false, unique = true)
      private String email;
      @Column(name = "PASSWORD", nullable = false)
      private String password;
      @Column(name = "SALT", nullable = false)
      private String salt;


      public User()
      {

	    email = "";
	    password = "";
	    salt = "";
      }


      public User(String email, String password)
      {

	    super();
	    this.email = email;
	    this.password = password;
	    this.salt = PasswordUtils.getSalt(128);
      }


      public String getEmail()
      {

	    return email;
      }


      public void setEmail(String email)
      {

	    this.email = email;
      }


      public String getPassword()
      {

	    return password;
      }


      public void setPassword(String password)
      {

	    this.password = password;
      }


      @Override
      public String toString()
      {

	    return "User [email=" + email + ", password=" + password + "]";
      }


      public String getSalt()
      {

	    return salt;
      }


      public void setSalt(String salt)
      {

	    this.salt = salt;
      }


      public int getId()
      {

	    return id;
      }


      public void setId(int id)
      {

	    this.id = id;
      }

}
