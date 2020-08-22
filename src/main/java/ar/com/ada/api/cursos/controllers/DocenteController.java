package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.DocenteRequest;
import ar.com.ada.api.cursos.models.request.PersonaRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class DocenteController {

    // Declarar un service

    @Autowired
    DocenteService docenteService;

    // Post: que recibimos algo, que nos permite instanciar una Categoria y ponerle
    // datos.

    // Queda obsoleto porque ya se crea en la registracion
    @PostMapping("/api/docentes")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF')")
    public ResponseEntity<GenericResponse> crearDocente(@RequestBody Docente docente) {

        docenteService.crearDocente(docente);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Docente creada con exito";
        r.id = docente.getDocenteId();

        // Aca vamos a usar Ok
        // Cuando se crea, generalmetnte para los puristas, se usa el
        // CreatedAtAction(201)
        return ResponseEntity.ok(r);

    }

    // FUNCIONA tira forbidden como estudiante y accede bien como staff
    @GetMapping("/api/docentes/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF') or (hasAuthority('CLAIM_userType_DOCENTE')) and hasAuthority('CLAIM_entityId_'+#id)")
    ResponseEntity<Docente> buscarPorIdDocente(@PathVariable Integer id) {
        Docente docente = docenteService.buscarPorId(id);
        if (docente == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(docente);
    }

    // En este caso se llama al metodo buscarPorId en el DocenteService 2 veces
    // @GetMapping("/api/docentes/{id}")
    // ResponseEntity<Docente> buscarPorIdDocente(@PathVariable Integer id) {
    // if (docenteService.buscarPorId(id) == null)
    // return ResponseEntity.notFound().build();
    // return ResponseEntity.ok(docenteService.buscarPorId(id));
    // }

    // FUNCIONA tira forbidden cuando accedo como estudiante y accede bien como staff
    @GetMapping("/api/docentes")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF') or (hasAuthority('CLAIM_userType_DOCENTE'))")
    ResponseEntity<List<Docente>> listarDocentes() {
        List<Docente> listaDocentes = docenteService.listaDocentes();
        return ResponseEntity.ok(listaDocentes);
    }

    @PutMapping(("/api/docentes/{id}"))
  @PreAuthorize("@usuarioService.buscarPorUsername(principal.getUsername()).getTipoUsuarioId().getValue() == 1") //En este caso quiero que sea STAFF(3)
  ResponseEntity<GenericResponse> actualizarDocentePorId(@PathVariable Integer id,
          @RequestBody PersonaRequest dR) {
      Docente docente = docenteService.buscarPorId(id);
      if (docente == null) {
          return ResponseEntity.notFound().build();
      }

      docente.setNombre(dR.nombre);
      docente.setPaisId(dR.paisId);
      docente.setTipoDocumentoId(dR.tipoDocumentoId);
      docente.setDocumento(dR.documento);
      docente.setFechaNacimiento(dR.fechaNacimiento);
      Docente docenteActualizado = docenteService.actualizarDocente(docente);

      GenericResponse r = new GenericResponse();
      r.isOk = true;
      r.message = "Docente actualizado con éxito";
      r.id = docenteActualizado.getDocenteId();


      return ResponseEntity.ok(r);
  }
} 