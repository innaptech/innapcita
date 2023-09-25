import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';
import { IHistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';
import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';
import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';
import { IEstatusCita } from 'app/shared/model/estatus-cita.model';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';

export interface ICita {
    id?: number;
    fecha?: Moment;
    notas?: string;
    pacienteNino?: boolean;
    consultaOnline?: boolean;
    paciente?: IUser;
    especialidadMedico?: IEspecialidadMedico;
    especialidad?: IEspecialidad;
    tipoConsulta?: ITipoConsulta;
    historiaPersonalAdultos?: IHistoriaPersonalAdulto[];
    historiaMedicaAdultos?: IHistoriaMedicaAdulto[];
    historiaPersonalNinos?: IHistoriaPersonalNino[];
    historiaMedicaNinos?: IHistoriaMedicaNino[];
    estatusCita?: IEstatusCita;
    evolucionPaciente?: IEvolucionPaciente;
}

export class Cita implements ICita {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public notas?: string,
        public pacienteNino?: boolean,
        public consultaOnline?: boolean,
        public paciente?: IUser,
        public especialidadMedico?: IEspecialidadMedico,
        public especialidad?: IEspecialidad,
        public tipoConsulta?: ITipoConsulta,
        public historiaPersonalAdultos?: IHistoriaPersonalAdulto[],
        public historiaMedicaAdultos?: IHistoriaMedicaAdulto[],
        public historiaPersonalNinos?: IHistoriaPersonalNino[],
        public historiaMedicaNinos?: IHistoriaMedicaNino[],
        public estatusCita?: IEstatusCita,
        public evolucionPaciente?: IEvolucionPaciente
    ) {
        this.pacienteNino = this.pacienteNino || false;
    }
}
