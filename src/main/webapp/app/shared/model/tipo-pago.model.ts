export interface ITipoPago {
    id?: number;
    nombre?: string;
    descripcion?: string;
    instantaneo?: boolean;
}

export class TipoPago implements ITipoPago {
    constructor(public id?: number, public nombre?: string, public descripcion?: string, public instantaneo?: boolean) {
        this.instantaneo = this.instantaneo || false;
    }
}
