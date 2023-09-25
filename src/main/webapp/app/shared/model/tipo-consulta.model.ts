export interface ITipoConsulta {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class TipoConsulta implements ITipoConsulta {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
