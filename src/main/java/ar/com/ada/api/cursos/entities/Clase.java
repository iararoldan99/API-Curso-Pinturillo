package ar.com.ada.api.cursos.entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "clase")
public class Clase {
    @Id
    @Column(name = "clase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // en JAVA, int es un tipo primitivo, no un objeto, mientras que Integer es un
    // objeto o una Clase.
    private Integer claseId;
    private Integer numero;
    @ManyToOne
    // @ManyToOne , la cual nos permite mapear una entidad con otra. Como única
    // regla, es necesario la clase que sea una entidad, es decir, que también esté
    // anotada con @Entity
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    // Mediante la anotación @JoinColumn es posible personalizar las columnas que
    // será utilizadas como uniones con otras tablas. Cuando trabajamos con
    // relaciones como @ManyToOne o @OneToOne, es necesario indicarle a JPA como es
    // que tendrá que realizar la unión (JOIN) con la otra Entidad.
    // * name: Indica el nombre con el que se deberá de crear la columna dentro de
    // la
    // tabla. 
    // curso_id es la columna de la tabla Clase
    // referencedColumnName: Se utiliza para indicar sobre que columna se realizará
    // el Join de la otra tabla. 
    // curso_id es la columna de la tabla Curso
    private Curso curso;
    private String titulo;
    @Column(name = "duracion_horas")
    private Integer duracionHoras;
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Contenido> contenidos;

    // La anotación @ManyToOne cuenta con los siguientes atributos:
    // * Optional: indica si la relación es opcional, es decir, si el objeto puede
    // ser
    // null. Esta propiedad se utiliza optimizar las consultas. Si JPA sabe que una
    // relación es opcional, entonces puede realizar un RIGHT JOIN o realizar la
    // consulta por separado, mientras que, si no es opcional, puede realizar un
    // INNER JOIN para realizar una solo consulta.
    // * Cascade: Esta propiedad le indica que operaciones en cascada puede realizar
    // con la Entidad relacionada, los valores posibles son ALL , PERSIST , MERGE ,
    // REMOVE , REFRESH , DETACH y están definidos en la enumeración
    // javax.persistence.CascadeType .
    // * Fetch: Esta propiedad se utiliza para determinar cómo debe ser cargada la
    // entidad, los valores están definidos en la enumeración
    // javax.persistence.FetchType y los valores posibles son:
    // - EAGER (ansioso): Indica que la relación debe de ser cargada al momento de
    // cargar la entidad.
    // - LAZY (perezoso): Indica que la relación solo se cargará cuando la propiedad
    // sea leída por primera vez.
    // * targetEntity: Esta propiedad recibe una clase (Class ) la cual corresponde
    // a
    // la clase de la relación. No suele ser utilizada, pues JPA puede inferir la
    // clase por el tipo de la propiedad.

    public Integer getClaseId() {
        return claseId;
    }

    public void setClaseId(Integer claseId) {
        this.claseId = claseId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

}