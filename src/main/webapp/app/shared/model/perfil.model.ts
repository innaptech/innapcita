import { IUser } from 'app/core/user/user.model';

export interface IPerfil {
    id?: number;
    fotoContentType?: string;
    foto?: any;
    descripcion?: string;
    selloContentType?: string;
    sello?: any;
    profesion?: string;
    email?: string;
    telefono?: string;
    direccion?: string;
    experiencia?: string;
    reconocimientos?: string;
    actividadProfesionalActual?: string;
    colegiaturasAsociaciones?: string;
    areasDeInteres?: string;
    rif?: string;
    institucion?: string;
    logoInstitucionContentType?: string;
    logoInstitucion?: any;
    otrasInstituciones?: string;
    titulo?: string;
    titulo_abreviado?: string;
    nombre?: string;
    apellido?: string;
    user?: IUser;
}

export class Perfil implements IPerfil {
    constructor(
        public id?: number,
        public fotoContentType?: string,
        public foto?: any,
        public descripcion?: string,
        public selloContentType?: string,
        public sello?: any,
        public profesion?: string,
        public email?: string,
        public telefono?: string,
        public direccion?: string,
        public experiencia?: string,
        public reconocimientos?: string,
        public actividadProfesionalActual?: string,
        public colegiaturasAsociaciones?: string,
        public areasDeInteres?: string,
        public rif?: string,
        public institucion?: string,
        public logoInstitucionContentType?: string,
        public logoInstitucion?: any,
        public otrasInstituciones?: string,
        public titulo?: string,
        public titulo_abrevidado?: string,
        public nombre?: string,
        public apellido?: string,
        public user?: IUser
    ) {}
}
