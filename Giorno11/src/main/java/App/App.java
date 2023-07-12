package App;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DAO.EventoDAO;
import DAO.LocationDAO;
import DAO.PartecipazioneDAO;
import DAO.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import entities.Sesso;
import entities.StatoPartecipazione;
import entities.TipoEvento;
import util.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		// Creo location
		Location location1 = new Location("Mergellina", "Napoli");
		Location location2 = new Location("Duomo", "Milano");

		// Creo persona
		Persona persona1 = new Persona("Benedetto", "De Stefano", "bds@gmail.com", LocalDate.of(1997, 2, 20),
				Sesso.MASCHIO);
		Persona persona2 = new Persona("Giuseppe", "Petrucci", "gsp@gmail.com", LocalDate.of(1995, 2, 17),
				Sesso.MASCHIO);

		// Creo evento
		Evento Evento1 = new Evento("Mobile World Congress", LocalDate.now(), "Fiera internazionale",
				TipoEvento.PUBBLICO, 10000, location1);
		Evento Evento2 = new Evento("Concerto Queen", LocalDate.now(), "Concerto musicale", TipoEvento.PRIVATO, 4000,
				location2);

		// Creo partecipazione
		Partecipazione participazione1 = new Partecipazione(persona1, Evento1, StatoPartecipazione.CONFERMATA);
		Partecipazione participazione2 = new Partecipazione(persona2, Evento2, StatoPartecipazione.DA_CONFERMARE);

		// Creo location DAO
		LocationDAO locationDAO = new LocationDAO(em);
		locationDAO.save(location1);
		locationDAO.save(location2);

		// Creo persona DAO
		PersonaDAO personaDAO = new PersonaDAO(em);
		personaDAO.save(persona1);
		personaDAO.save(persona2);

		// Creo evento DAO
		EventoDAO eventoDAO = new EventoDAO(em);
		eventoDAO.save(Evento1);
		eventoDAO.save(Evento2);

		PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);
		partecipazioneDAO.save(participazione1);
		partecipazioneDAO.save(participazione2);

		// Cerca persona
		Persona findPersona = personaDAO.findById(persona1.getId());
		System.out.println("Persona trovata by ID: " + findPersona);

		// Cerca evento
		Evento findEvento = eventoDAO.findById(Evento1.getId());
		System.out.println("Event trovato by ID: " + findEvento);

		persona1.addPartecipazione(participazione1);
		persona2.addPartecipazione(participazione2);

		em.close();
		emf.close();

	}

}
