export interface ISexo {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class Sexo implements ISexo {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
