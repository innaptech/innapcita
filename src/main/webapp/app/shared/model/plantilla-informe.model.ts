import { IEspecialidad } from 'app/shared/model/especialidad.model';

export interface IPlantillaInforme {
    id?: number;
    nombre?: string;
    texto?: string;
    especialidad?: IEspecialidad;
}

export class PlantillaInforme implements IPlantillaInforme {
    constructor(public id?: number, public nombre?: string, public texto?: string, public especialidad?: IEspecialidad) {}
}
