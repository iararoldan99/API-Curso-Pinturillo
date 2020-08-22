package ar.com.ada.api.cursos.models.request;

import java.util.Date;

import ar.com.ada.api.cursos.entities.Pais.TipoDocEnum;

public class PersonaRequest {
    public String nombre;
    public Integer paisId;
    public TipoDocEnum tipoDocumentoId;
    public String documento;
    public Date fechaNacimiento;
    
}