package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ar.com.ada.api.cursos.entities.Pais.*;

// anotación de la clase padre 
@MappedSuperclass
public class Persona {
    private String nombre;
    @Column(name = "pais_id")
    private Integer paisId;
    @Column(name = "tipo_documento_id")
    private Integer tipoDocumentoId;
    private String documento;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPaisId() {
        return this.paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public TipoDocEnum getTipoDocumentoId() {
        return TipoDocEnum.parse(this.tipoDocumentoId);
    }

    public void setTipoDocumentoId(TipoDocEnum tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId.getValue();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipoDocumentoId(Integer tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public Persona(String nombre, Integer paisId, Integer tipoDocumentoId, String documento, Date fechaNacimiento) {
        this.nombre = nombre;
        this.paisId = paisId;
        this.tipoDocumentoId = tipoDocumentoId;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona() {
    }

    

}