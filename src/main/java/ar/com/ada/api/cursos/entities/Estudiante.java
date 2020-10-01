package ar.com.ada.api.cursos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona {
    // Solo se mapean objetos. No enumerados ni ints o demás.
    @Id
    @Column(name = "estudiante_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estudianteId;
    @ManyToMany
    @JoinTable(name = "estudiante_x_curso", joinColumns = @JoinColumn(name = "estudiante_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @JsonIgnore
    private List<Curso> cursosQueAsiste = new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL) // nombre del atributo en el obj usuario
    private Usuario usuario;
    @Column(name = "pagada_deudor_id")
    private Integer PagADA_deudorId; 

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public List<Curso> getCursosQueAsiste() {
        return cursosQueAsiste;
    }

    public void setCursosQueAsiste(List<Curso> cursosQueAsiste) {
        this.cursosQueAsiste = cursosQueAsiste;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setEstudiante(this);

    }

    public Estudiante() {

    }

    public Estudiante(String nombre, Integer paisId, Integer tipoDocumentoId, String documento, Date fechaNacimiento,
            Integer estudianteId, List<Curso> cursosQueAsiste, Usuario usuario) {
        super(nombre, paisId, tipoDocumentoId, documento, fechaNacimiento);
        this.estudianteId = estudianteId;
        this.cursosQueAsiste = cursosQueAsiste;
        this.usuario = usuario;
    }

    public Integer getPagADA_deudorId() {
        return PagADA_deudorId;
    }

    public void setPagADA_deudorId(Integer pagADA_deudorId) {
        PagADA_deudorId = pagADA_deudorId;
    }

  
}