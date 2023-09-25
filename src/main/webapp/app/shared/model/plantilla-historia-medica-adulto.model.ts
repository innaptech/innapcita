export interface IPlantillaHistoriaMedicaAdulto {
    id?: number;
    nombre?: string;
    contenido?: string;
    activo?: boolean;
}

export class PlantillaHistoriaMedicaAdulto implements IPlantillaHistoriaMedicaAdulto {
    constructor(public id?: number, public nombre?: string, public contenido?: string, public activo?: boolean) {
        this.activo = this.activo || false;
    }
}
