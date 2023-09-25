export interface IEstatusPago {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class EstatusPago implements IEstatusPago {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
