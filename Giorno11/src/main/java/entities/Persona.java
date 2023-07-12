package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "persone")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Persona {

	@Id
	@GeneratedValue
	private UUID id;

	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataNascita;

	@Enumerated(EnumType.STRING)
	private Sesso sesso;

	@OneToMany(mappedBy = "persona")
	@OrderBy("dataEvento ASC")
	private List<Partecipazione> listaPartecipazioni;

	public Persona(String nome, String cognome, String email, LocalDate dataNascita, Sesso sesso) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.listaPartecipazioni = new ArrayList<>();
	}

	public void addPartecipazione(Partecipazione partecipazione) {
		listaPartecipazioni.add(partecipazione);
		partecipazione.setPersona(this);
	}

	public void removePartecipazione(Partecipazione partecipazione) {
		listaPartecipazioni.remove(partecipazione);
		partecipazione.setPersona(null);
	}

}
