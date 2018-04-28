package com.mqobi.proyecto.persona;

import com.mqobi.proyecto.exception.ResourceNotFoundException;
import com.mqobi.proyecto.model.persona;
import com.mqobi.proyecto.repository.personaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class personaController {

	@Autowired
	personaRepository personaRepository;

	// Get All person
	@GetMapping("/persona")
	public List<persona> getAllpersona() {
		return personaRepository.findAll();
	}

	// Create a new person
	@PostMapping("/persona")
	public persona createpersona(@Valid @RequestBody persona persona) {
		return personaRepository.save(persona);
	}

	// Get a Single Note
	@GetMapping("/persona/{id}")
	public persona getNoteById(@PathVariable(value = "id") Long personaId) {
		return personaRepository.findById(personaId)
				.orElseThrow(() -> new ResourceNotFoundException("persona", "id", personaId));
	}

	// Update a Note
	@PutMapping("/notes/{id}")
	public persona updatepersona(@PathVariable(value = "id") Long personaId,
			@Valid @RequestBody persona personaDetails) {

		persona persona = personaRepository.findById(personaId)
				.orElseThrow(() -> new ResourceNotFoundException("persona", "id", personaId));

		persona.setNombre(personaDetails.getNombre());
		persona.setApellidos(personaDetails.getApellidos());
		persona.setDomicilio(personaDetails.getDomicilio());
		persona.setCuidad(personaDetails.getCuidad());

		persona updatedpersona = personaRepository.save(persona);
		return updatedpersona;
	}

	// Delete a Note
	@DeleteMapping("/persona/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long personaId) {
		persona persona = personaRepository.findById(personaId)
				.orElseThrow(() -> new ResourceNotFoundException("persona", "id", personaId));

		personaRepository.delete(persona);

		return ResponseEntity.ok().build();
	}
}