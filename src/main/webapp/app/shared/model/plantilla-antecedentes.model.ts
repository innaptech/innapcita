export interface IPlantillaAntecedentes {
    id?: number;
    nombre?: string;
    antecedentes?: any;
}

export class PlantillaAntecedentes implements IPlantillaAntecedentes {
    constructor(public id?: number, public nombre?: string, public antecedentes?: any) {}
}
