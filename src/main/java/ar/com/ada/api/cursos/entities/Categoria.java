package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @Column(name = "categoria_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // utilizar la anotación @GeneratedValue para obligar a que el campo sea
    // autoincremental
    // generationtype identity Se basa en una columna de base de datos con
    // incremento automático y permite que la base de datos genere un nuevo valor
    // con cada operación de inserción
    private Integer categoriaId;
    private String nombre;
    private String descripcion;
    @JsonIgnore
    @ManyToMany
    // @ManyToMany se caracteríza por Entidades que están relacionadas con
    // muchos elementos de un tipo determinado, pero al mismo tiempo, estos últimos
    // registros no son exclusivos de un registro en particular, si no que pueden
    // ser parte de varios, por lo tanto, tenemos una Entidad A, la cual puede estar
    // relacionada como muchos registros de la Entidad B, pero al mismo tiempo, la
    // Entidad B puede pertenecer a varias instancias de la Entidad A.
    // este tipo de relaciones no existen físicamente en la base de datos, y en su
    // lugar, es necesario crear una tabla intermedia que relacione las dos
    // entidades
    //
    // la anotación @JoinTable, la cual nos sirve para definir la estructura de la
    // tabla intermedia que contendrá la relación entre los cursos y las categorias.
    // Hemos definidos las siguientes propiedades de la anotación @JoinTable:
    // * name: Nombre de la tabla que será creada físicamente en la base de datos.
    // * joinColumns: Corresponde al nombre para el ID de la Entidad Categoria.
    // * inverseJoinColumns: Corresponde al nombre para el ID de la Entidad Curso
    @JoinTable(name = "curso_x_categoria", joinColumns = @JoinColumn(name = "categoria_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos = new ArrayList<>();
    // La tabla intermedia (curso_x_categoria) es generada por la anotación
    // @JoinTable y sus dos columnas son llaves foraneas a las tablas categoria y
    // curso.

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Categoria() {
    }

    public Categoria(Integer categoriaId, String nombre, String descripcion, List<Curso> cursos) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cursos = cursos;
    }

    

    

}