export interface IPieDePaginaInforme {
    id?: number;
    nombre?: string;
    pieDePagina?: string;
}

export class PieDePaginaInforme implements IPieDePaginaInforme {
    constructor(public id?: number, public nombre?: string, public pieDePagina?: string) {}
}
