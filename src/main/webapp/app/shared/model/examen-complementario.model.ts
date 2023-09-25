import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';

export interface IExamenComplementario {
    id?: number;
    descripcion?: string;
    archivoContentType?: string;
    archivo?: any;
    evolucionPaciente?: IEvolucionPaciente;
}

export class ExamenComplementario implements IExamenComplementario {
    constructor(
        public id?: number,
        public descripcion?: string,
        public archivoContentType?: string,
        public archivo?: any,
        public evolucionPaciente?: IEvolucionPaciente
    ) {}
}
