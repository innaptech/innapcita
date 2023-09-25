import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'especialidad',
                loadChildren: './especialidad/especialidad.module#InnapcitasEspecialidadModule'
            },
            {
                path: 'especialidad-medico',
                loadChildren: './especialidad-medico/especialidad-medico.module#InnapcitasEspecialidadMedicoModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'dia-semana',
                loadChildren: './dia-semana/dia-semana.module#InnapcitasDiaSemanaModule'
            },
            {
                path: 'estatus-pago',
                loadChildren: './estatus-pago/estatus-pago.module#InnapcitasEstatusPagoModule'
            },
            {
                path: 'horario-disponibilidad',
                loadChildren: './horario-disponibilidad/horario-disponibilidad.module#InnapcitasHorarioDisponibilidadModule'
            },
            {
                path: 'rango-horas',
                loadChildren: './rango-horas/rango-horas.module#InnapcitasRangoHorasModule'
            },
            {
                path: 'tipo-pago',
                loadChildren: './tipo-pago/tipo-pago.module#InnapcitasTipoPagoModule'
            },
            {
                path: 'pago',
                loadChildren: './pago/pago.module#InnapcitasPagoModule'
            },
            {
                path: 'tipo-consulta',
                loadChildren: './tipo-consulta/tipo-consulta.module#InnapcitasTipoConsultaModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'especialidad-medico',
                loadChildren: './especialidad-medico/especialidad-medico.module#InnapcitasEspecialidadMedicoModule'
            },
            {
                path: 'moneda-cambio',
                loadChildren: './moneda-cambio/moneda-cambio.module#InnapcitasMonedaCambioModule'
            },
            {
                path: 'rango-horas',
                loadChildren: './rango-horas/rango-horas.module#InnapcitasRangoHorasModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'especialidad',
                loadChildren: './especialidad/especialidad.module#InnapcitasEspecialidadModule'
            },
            {
                path: 'especialidad',
                loadChildren: './especialidad/especialidad.module#InnapcitasEspecialidadModule'
            },
            {
                path: 'sexo',
                loadChildren: './sexo/sexo.module#InnapcitasSexoModule'
            },
            {
                path: 'referencia-medica',
                loadChildren: './referencia-medica/referencia-medica.module#InnapcitasReferenciaMedicaModule'
            },
            {
                path: 'estado-civil',
                loadChildren: './estado-civil/estado-civil.module#InnapcitasEstadoCivilModule'
            },
            {
                path: 'historia-personal-adulto',
                loadChildren: './historia-personal-adulto/historia-personal-adulto.module#InnapcitasHistoriaPersonalAdultoModule'
            },
            {
                path: 'historia-personal-adulto',
                loadChildren: './historia-personal-adulto/historia-personal-adulto.module#InnapcitasHistoriaPersonalAdultoModule'
            },
            {
                path: 'grado-instruccion',
                loadChildren: './grado-instruccion/grado-instruccion.module#InnapcitasGradoInstruccionModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'historia-personal-adulto',
                loadChildren: './historia-personal-adulto/historia-personal-adulto.module#InnapcitasHistoriaPersonalAdultoModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'plantilla-historia-personal-adulto',
                loadChildren:
                    './plantilla-historia-personal-adulto/plantilla-historia-personal-adulto.module#InnapcitasPlantillaHistoriaPersonalAdultoModule'
            },
            {
                path: 'plantilla-historia-personal-nino',
                loadChildren:
                    './plantilla-historia-personal-nino/plantilla-historia-personal-nino.module#InnapcitasPlantillaHistoriaPersonalNinoModule'
            },
            {
                path: 'plantilla-historia-medica-adulto',
                loadChildren:
                    './plantilla-historia-medica-adulto/plantilla-historia-medica-adulto.module#InnapcitasPlantillaHistoriaMedicaAdultoModule'
            },
            {
                path: 'plantilla-historia-medica-nino',
                loadChildren:
                    './plantilla-historia-medica-nino/plantilla-historia-medica-nino.module#InnapcitasPlantillaHistoriaMedicaNinoModule'
            },
            {
                path: 'estatus-cita',
                loadChildren: './estatus-cita/estatus-cita.module#InnapcitasEstatusCitaModule'
            },
            {
                path: 'email-cita',
                loadChildren: './email-cita/email-cita.module#InnapcitasEmailCitaModule'
            },
            {
                path: 'especialidad',
                loadChildren: './especialidad/especialidad.module#InnapcitasEspecialidadModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-medica-adulto',
                loadChildren: './historia-medica-adulto/historia-medica-adulto.module#InnapcitasHistoriaMedicaAdultoModule'
            },
            {
                path: 'historia-personal-adulto',
                loadChildren: './historia-personal-adulto/historia-personal-adulto.module#InnapcitasHistoriaPersonalAdultoModule'
            },
            {
                path: 'historia-personal-adulto',
                loadChildren: './historia-personal-adulto/historia-personal-adulto.module#InnapcitasHistoriaPersonalAdultoModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-personal-nino',
                loadChildren: './historia-personal-nino/historia-personal-nino.module#InnapcitasHistoriaPersonalNinoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'historia-medica-nino',
                loadChildren: './historia-medica-nino/historia-medica-nino.module#InnapcitasHistoriaMedicaNinoModule'
            },
            {
                path: 'cita',
                loadChildren: './cita/cita.module#InnapcitasCitaModule'
            },
            {
                path: 'evolucion-paciente',
                loadChildren: './evolucion-paciente/evolucion-paciente.module#InnapcitasEvolucionPacienteModule'
            },
            {
                path: 'examen-complementario',
                loadChildren: './examen-complementario/examen-complementario.module#InnapcitasExamenComplementarioModule'
            },
            {
                path: 'evolucion-paciente',
                loadChildren: './evolucion-paciente/evolucion-paciente.module#InnapcitasEvolucionPacienteModule'
            },
            {
                path: 'evolucion-paciente',
                loadChildren: './evolucion-paciente/evolucion-paciente.module#InnapcitasEvolucionPacienteModule'
            },
            {
                path: 'plantilla-informe',
                loadChildren: './plantilla-informe/plantilla-informe.module#InnapcitasPlantillaInformeModule'
            },
            {
                path: 'plantilla-recipe',
                loadChildren: './plantilla-recipe/plantilla-recipe.module#InnapcitasPlantillaRecipeModule'
            },
            {
                path: 'bitacora',
                loadChildren: './bitacora/bitacora.module#InnapcitasBitacoraModule'
            },
            {
                path: 'bitacora',
                loadChildren: './bitacora/bitacora.module#InnapcitasBitacoraModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#InnapcitasPerfilModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#InnapcitasPerfilModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#InnapcitasPerfilModule'
            },
            {
                path: 'perfil',
                loadChildren: './perfil/perfil.module#InnapcitasPerfilModule'
            },
            {
                path: 'cabecera-informe',
                loadChildren: './cabecera-informe/cabecera-informe.module#InnapcitasCabeceraInformeModule'
            },
            {
                path: 'pie-de-pagina-informe',
                loadChildren: './pie-de-pagina-informe/pie-de-pagina-informe.module#InnapcitasPieDePaginaInformeModule'
            },
            {
                path: 'plantilla-antecedentes',
                loadChildren: './plantilla-antecedentes/plantilla-antecedentes.module#InnapcitasPlantillaAntecedentesModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEntityModule {}
