export interface IReferenciaMedica {
    id?: number;
    nombre?: string;
    descripcion?: string;
}

export class ReferenciaMedica implements IReferenciaMedica {
    constructor(public id?: number, public nombre?: string, public descripcion?: string) {}
}
