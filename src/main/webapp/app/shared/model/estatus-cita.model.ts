export interface IEstatusCita {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class EstatusCita implements IEstatusCita {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
