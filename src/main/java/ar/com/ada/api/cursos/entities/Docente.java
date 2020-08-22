package ar.com.ada.api.cursos.entities;

import java.util.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Docente extends Persona {
    @Id
    @Column(name = "docente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docenteId;
    @ManyToMany
    @JoinTable(name = "docente_x_curso", joinColumns = @JoinColumn(name = "docente_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @JsonIgnore
    private List<Curso> cursosQueDicta = new ArrayList<>();
    @OneToOne(mappedBy = "docente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Usuario usuario;

    public Integer getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Integer docenteId) {
        this.docenteId = docenteId;
    }

    public List<Curso> getCursosQueDicta() {
        return cursosQueDicta;
    }

    public void setCursosQueDicta(List<Curso> cursosQueDicta) {
        this.cursosQueDicta = cursosQueDicta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.usuario.setDocente(this);

    }

    public Docente(String nombre, Integer paisId, Integer tipoDocumentoId, String documento, Date fechaNacimiento,
            Integer docenteId, List<Curso> cursosQueDicta, Usuario usuario) {
        super(nombre, paisId, tipoDocumentoId, documento, fechaNacimiento);
        this.docenteId = docenteId;
        this.cursosQueDicta = cursosQueDicta;
        this.usuario = usuario;
    }

    public Docente(Integer docenteId, List<Curso> cursosQueDicta, Usuario usuario) {
        this.docenteId = docenteId;
        this.cursosQueDicta = cursosQueDicta;
        this.usuario = usuario;
    }

    public Docente(){
        
    }

    
   
}