export interface IDiaSemana {
    id?: number;
    nombre?: string;
}

export class DiaSemana implements IDiaSemana {
    constructor(public id?: number, public nombre?: string) {}
}
