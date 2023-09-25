import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IBitacora {
    id?: number;
    fecha?: Moment;
    titulo?: string;
    detalle?: string;
    tipo?: number;
    user?: IUser;
}

export class Bitacora implements IBitacora {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public titulo?: string,
        public detalle?: string,
        public tipo?: number,
        public user?: IUser
    ) {}
}
