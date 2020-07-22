package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController

public class CategoriaController {

    // Declarar un service

    @Autowired
    CategoriaService categoriaService;

    // Post: que recibios algo, que nos permite instanciar una Categoria y ponerle
    // datos.
    @PostMapping("/categorias")
    ResponseEntity<GenericResponse> crearCategoria(@RequestBody Categoria categoria) {

        categoriaService.crearCategoria(categoria);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria Creada con exito";
        r.id = categoria.getCategoriaId();

        // Aca vamos a usar Ok
        // Cuando se crea, generalmetnte para los puristas, se usa el
        // CreatedAtAction(201)
        return ResponseEntity.ok(r);

    }

    // Hacer GET para que traiga todas las categorias

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerCategorias());
    }

    // Hacer GET para que traiga una sola categoria con solo colocar su id

    @GetMapping("/categorias/{id}")
    // pathvariable es una variable de ruta, la variable id de tipo int
    // va a estar en la ruta, se tiene que llavar igual a como esta declarado arriba
    public ResponseEntity<Categoria> obtenerCategoria(@PathVariable int id){
        // siempre queremos que devuelva ok
        Categoria categoria = categoriaService.buscarPorId(id);
        if(categoria == null) {
            return ResponseEntity.notFound().build();
            // not found devuleve un objeto a construir
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
         return ResponseEntity.ok(categoria);
    }

}
