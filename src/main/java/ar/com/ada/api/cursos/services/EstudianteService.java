package ar.com.ada.api.cursos.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Estudiante;
import ar.com.ada.api.cursos.entities.Inscripcion;
import ar.com.ada.api.cursos.entities.Inscripcion.EstadoInscripcionEnum;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocEnum;
import ar.com.ada.api.cursos.repos.EstudianteRepository;
import ar.com.ada.api.cursos.sistema.pagada.PagADAService;

@Service
public class EstudianteService {
    // ctrl F para reemplazar palabras case sensitive

    @Autowired
    EstudianteRepository estudianteRepo;
    @Autowired
    CursoService cursoService;
    @Autowired
    PagADAService pagADAService;

    // este metodo define "SI EXISTE" el estudiante

    public boolean crearEstudiante(Estudiante estudiante) {
        if (estudianteRepo.existsEstudiante(estudiante.getPaisId(), estudiante.getTipoDocumentoId().getValue(),
                estudiante.getDocumento()))
            return false;
        estudianteRepo.save(estudiante);
        return true;

    }

    public Estudiante crearEstudiante(String nombre, PaisEnum paisEnum, TipoDocEnum TipoDocEnum, String documento,
            Date fechaNacimiento) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setPaisId(paisEnum.getValue());
        estudiante.setTipoDocumentoId(TipoDocEnum);
        estudiante.setDocumento(documento);
        estudiante.setFechaNacimiento(fechaNacimiento);
        boolean estudianteCreado = crearEstudiante(estudiante);
        if (estudianteCreado)
            return estudiante;
        else
            return null;
    }

    public Estudiante buscarPorId(Integer id) {
        Optional<Estudiante> opEstudiante = estudianteRepo.findById(id);

        if (opEstudiante.isPresent())
            return opEstudiante.get();
        else
            return null;

    }

    public List<Estudiante> listaEstudiantes() {
        return estudianteRepo.findAll();
    }

    public boolean estudianteExiste(Estudiante estudiante) {

        if (estudianteRepo.existsEstudiante(estudiante.getPaisId(), estudiante.getTipoDocumentoId().getValue(),
                estudiante.getDocumento()))
            return true;
        else
            return false;
    }

    public Inscripcion inscribir(Integer estudianteId, Integer cursoId, String medioPago, String infoMedioPago) {
        // TODO:buscar el estudiante por Id
        // buscar el curso por Id;
        // Crear la inscripcion(aprobada por defecto)
        // Asignar la inscripcion al Usuario del Estudiante
        // Agregar el Estudiante a la Lista de Estudiantes que tiene Curso

        Estudiante estudiante = buscarPorId(estudianteId);
        Curso curso = cursoService.buscarPorId(cursoId);
        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setFecha(new Date());
        inscripcion.setEstadoInscripcion(EstadoInscripcionEnum.INACTIVO); // Pendiente de pago

        // inscripcion.setCurso(curso);
        inscripcion.setUsuario(estudiante.getUsuario());

        curso.agregarInscripcion(inscripcion);
        curso.asignarEstudiante(estudiante);

        estudianteRepo.save(estudiante);

        // Hasta aca, tenemos la inscripcion creada SIN Pagar
        if (estudiante.getPagADA_deudorId() == null) {
            Integer pagADADeudorId = pagADAService.crearDeudor(estudiante);
            estudiante.setPagADA_deudorId(pagADADeudorId);
        }

        return inscripcion;
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return estudianteRepo.save(estudiante);
    }

    public Inscripcion pagarInscripcion(Inscripcion inscripcion, String medioPago, String infoMedioPago) {

        // 1. Crear deuda
        Integer servicioId = pagADAService.crearDeuda(inscripcion);

        // 2. Pagar la deuda por Id
        Integer pagoId = pagADAService.pagarServicio(inscripcion, medioPago, infoMedioPago);

        // 3. Si todo eestuvo bien, cambiar la inscripcion a ACTIVA

        inscripcion.setEstadoInscripcion(EstadoInscripcionEnum.ACTIVO);

        Estudiante estudiante = inscripcion.getUsuario().getEstudiante();
        estudianteRepo.save(estudiante);

        return inscripcion;
    }

}