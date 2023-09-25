export interface ICabeceraInforme {
    id?: number;
    nombre?: string;
    cabecera?: string;
}

export class CabeceraInforme implements ICabeceraInforme {
    constructor(public id?: number, public nombre?: string, public cabecera?: string) {}
}
