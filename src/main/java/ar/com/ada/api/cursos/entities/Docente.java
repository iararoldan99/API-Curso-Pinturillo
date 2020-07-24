package ar.com.ada.api.cursos.entities;

import java.util.List;
import java.util.*;

import javax.persistence.*;

import javax.persistence.GeneratedValue; 
@Entity
public class Docente extends Persona{
    @Id
    @Column(name="docente_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer docenteId;
    @ManyToMany
    @JoinTable(name = "docente_x_curso", joinColumns = @JoinColumn(name = "docente_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursosQueDicta = new ArrayList<>();
    @OneToOne(mappedBy = "docente") // el nombre del atributo en el objeto usuario
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
    }
   
}