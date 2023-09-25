export interface IGradoInstruccion {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class GradoInstruccion implements IGradoInstruccion {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
