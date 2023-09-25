import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';

export interface IRangoHoras {
    id?: number;
    horaInicio?: number;
    minutoInicio?: number;
    horaFin?: number;
    minutoFin?: number;
    horarioDisponibilidad?: IHorarioDisponibilidad;
}

export class RangoHoras implements IRangoHoras {
    constructor(
        public id?: number,
        public horaInicio?: number,
        public minutoInicio?: number,
        public horaFin?: number,
        public minutoFin?: number,
        public horarioDisponibilidad?: IHorarioDisponibilidad
    ) {}
}
