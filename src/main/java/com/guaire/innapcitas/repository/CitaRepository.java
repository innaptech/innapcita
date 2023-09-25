package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Spring Data  repository for the Cita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("select cita from Cita cita where cita.paciente.login = ?#{principal.username}")
    List<Cita> findByPacienteIsCurrentUser();

    Page<Cita> findAllByPaciente_Login(String name, Pageable pageable);

    Page<Cita> findAllByEspecialidadMedico_Medico_Login(String login, Pageable pageable);

    Page<Cita> findAllByIdOrPaciente_LoginOrPaciente_FirstNameOrPaciente_LastName(Long id, String login, String nombre, String apellido, Pageable pageable);

    Page<Cita> findAllByIdOrPaciente_LoginOrPaciente_FirstNameContainsIgnoreCaseOrPaciente_LastNameContainsIgnoreCase(Long id, String criteria, String criteria1, String criteria2, Pageable pageable);

    List<Cita> findAllByIdOrPaciente_LoginOrPaciente_FirstNameContainsIgnoreCaseOrPaciente_LastNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrEspecialidadMedico_DescripcionContainsIgnoreCaseOrEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(Long id, String criteria, String criteria1, String criteria2, String criteria3, String criteria4, String criteria5, String criteria6, String criteria7);

    List<Cita> findAllByPaciente_LoginAndEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_DescripcionContainsIgnoreCaseOrPaciente_LoginAndEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(String login, String criteria, String login1, String criteria1, String login2, String criteria2, String login3, String criteria3, String login4, String criteria4);

    Page<Cita> findAllByIdOrPaciente_LoginOrPaciente_FirstNameLikeIgnoreCaseOrPaciente_LastNameLikeIgnoreCaseOrEspecialidadMedico_Medico_LoginContainsIgnoreCaseOrEspecialidadMedico_Medico_FirstNameContainsIgnoreCaseOrEspecialidadMedico_Medico_LastNameContainsIgnoreCaseOrEspecialidadMedico_DescripcionContainsIgnoreCaseOrEspecialidadMedico_Especialidad_EspecialidadContainsIgnoreCase(Long id, String criteria, String nombre, String apellido, String criteria1, String criteria2, String criteria3, String criteria4, String criteria5, Pageable pageable);

    @Query("select a from Cita a where a.id IN :citas and (a.fecha BETWEEN :fecha1 AND :fecha2)")
    List<Cita> findCitasFilteredByMonth(@Param("fecha1") ZonedDateTime fecha1, @Param("fecha2") ZonedDateTime fecha2, @Param("citas") List<Long> citas);

    @Query("select a from Cita a where a.id IN :citas and a.tipoConsulta.nombre LIKE :tipoConsulta")
    List<Cita> findCitasFilteredByType(@Param("tipoConsulta") String tipoConsulta,@Param("citas") List<Long> citas);

    @Query("select a from Cita a where a.id IN :citas and a.consultaOnline = :tipoConsulta")
    List<Cita> findCitasFilteredByOnline(@Param("tipoConsulta") Boolean tipoConsulta,@Param("citas") List<Long> citas);

    @Query("select a from Cita a where a.id IN :citas and (a.consultaOnline = false or a.consultaOnline = null)")
    List<Cita> findCitasFilteredByPresencial(@Param("citas") List<Long> citas);

    @Query("select cita from Cita cita where cita.id IN :citas and cita.estatusCita.nombre LIKE :status")
    List<Cita> findCitasFilteredByStatus(@Param("status") String status,@Param("citas") List<Long> citas);

    @Query("select a from Cita a where a.id IN :citas")
    Page<Cita> findCitasPaged(@Param("citas") List<Long> citas, Pageable pageable);
}
