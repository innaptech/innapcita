import { IUser } from 'app/core/user/user.model';
import { IEspecialidad } from 'app/shared/model/especialidad.model';

export interface IEspecialidadMedico {
    id?: number;
    montoHora?: number;
    descripcion?: string;
    montoHoraOnline?: number;
    medico?: IUser;
    especialidad?: IEspecialidad;
    descripcionConcat?: string;
}

export class EspecialidadMedico implements IEspecialidadMedico {
    constructor(
        public id?: number,
        public montoHora?: number,
        public descripcion?: string,
        public montoHoraOnline?: number,
        public medico?: IUser,
        public especialidad?: IEspecialidad,
        public descripcionConcat?: string
    ) {}
}
