export interface IPlantillaHistoriaPersonalAdulto {
    id?: number;
    nombre?: string;
    contenido?: string;
    activo?: boolean;
}

export class PlantillaHistoriaPersonalAdulto implements IPlantillaHistoriaPersonalAdulto {
    constructor(public id?: number, public nombre?: string, public contenido?: string, public activo?: boolean) {
        this.activo = this.activo || false;
    }
}
