package com.graduationProject.graduationProject.entities.concretes;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.graduationProject.graduationProject.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Ad boş olamaz.")
	private String name;

	@NotBlank(message = "Soyad boş olamaz.")
	private String surname;

	@NotBlank(message = "Email boş olamaz.")
	@Email(message = "Geçersiz email formatı.")
	@Column(unique = true,length = 100, nullable = false)
	private String email;

	@NotBlank(message = "Şifre boş olamaz.")
	@Size(min = 6, message = "Şifre en az 6 karakter olmalıdır.")
	private String password;

	@NotBlank(message = "TC Kimlik numarası boş olamaz.")
	@Size(min = 11, max = 11, message = "TC Kimlik numarası 11 haneli olmalıdır.")
	private String tcNumber;

	@NotNull(message = "Doğum yılı boş olamaz.")
	private int birthYear;

	@NotBlank(message = "Telefon numarası boş olamaz.")
	private String phoneNumber;

	private String address;
	
	 @Enumerated(EnumType.STRING)
	 private Role role = Role.USER;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getUsername() {
		return email;
	}

}
