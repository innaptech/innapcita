export interface IEstadoCivil {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class EstadoCivil implements IEstadoCivil {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
