import { IUser } from 'app/core/user/user.model';

export interface IPlantillaRecipe {
    id?: number;
    nombre?: string;
    texto?: string;
    medico?: IUser;
}

export class PlantillaRecipe implements IPlantillaRecipe {
    constructor(public id?: number, public nombre?: string, public texto?: string, public medico?: IUser) {}
}
