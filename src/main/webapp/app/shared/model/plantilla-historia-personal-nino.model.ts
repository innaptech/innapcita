export interface IPlantillaHistoriaPersonalNino {
    id?: number;
    nombre?: string;
    contenido?: string;
    activo?: boolean;
}

export class PlantillaHistoriaPersonalNino implements IPlantillaHistoriaPersonalNino {
    constructor(public id?: number, public nombre?: string, public contenido?: string, public activo?: boolean) {
        this.activo = this.activo || false;
    }
}
