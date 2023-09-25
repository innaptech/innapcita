import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { IRangoHoras } from 'app/shared/model/rango-horas.model';

export interface IHorarioDisponibilidad {
    id?: number;
    diaSemana?: IDiaSemana;
    especialidadMedico?: IEspecialidadMedico;
    horas?: Array<IRangoHoras>;
}

export class HorarioDisponibilidad implements IHorarioDisponibilidad {
    constructor(
        public id?: number,
        public diaSemana?: IDiaSemana,
        public especialidadMedico?: IEspecialidadMedico,
        public horas?: Array<IRangoHoras>
    ) {}
}
