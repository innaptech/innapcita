export interface IEmailCita {
    id?: number;
    titulo?: string;
    mensaje?: string;
}

export class EmailCita implements IEmailCita {
    constructor(public id?: number, public titulo?: string, public mensaje?: string) {}
}
