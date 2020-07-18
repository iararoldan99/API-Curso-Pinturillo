package ar.com.ada.api.cursos;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import ar.com.ada.api.cursos.entities.Categoria;
import ar.com.ada.api.cursos.repos.CategoriaRepository;
import ar.com.ada.api.cursos.entities.Categoria;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
	CategoriaRepository repoCategoria;
    @Test
    void crearCategoriaSinCursoTest(){

        Categoria categoria = new Categoria();

        categoria.setNombre("Matematicas");
        categoria.setDescripcion("vemos algebra");

        repoCategoria.save(categoria);

        //assertTrue(categoria.getCategoriaId() > 0)
        //compareTo devuelve -1, si el lado izq es menor qeu el parametro
        // 0 si son iguales
        // 1 si es mayor el primer
        assertTrue(categoria.getCategoriaId().compareTo(0) == 1);

    }



}
