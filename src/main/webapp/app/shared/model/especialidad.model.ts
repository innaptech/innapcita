export interface IEspecialidad {
    id?: number;
    especialidad?: string;
    descripcion?: string;
    email?: string;
    administrador?: string;
}

export class Especialidad implements IEspecialidad {
    constructor(
        public id?: number,
        public especialidad?: string,
        public descripcion?: string,
        public email?: string,
        public administrador?: string
    ) {}
}
