export interface IMonedaCambio {
    id?: number;
    nombreMoneda?: string;
    simbolo?: string;
    montoCambio?: number;
}

export class MonedaCambio implements IMonedaCambio {
    constructor(public id?: number, public nombreMoneda?: string, public simbolo?: string, public montoCambio?: number) {}
}
