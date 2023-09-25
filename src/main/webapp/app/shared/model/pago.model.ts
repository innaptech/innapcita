import { Moment } from 'moment';
import { ICita } from 'app/shared/model/cita.model';
import { IEstatusPago } from 'app/shared/model/estatus-pago.model';
import { ITipoPago } from 'app/shared/model/tipo-pago.model';

export interface IPago {
    id?: number;
    monto?: number;
    fechaEmision?: Moment;
    fechaEstatus?: Moment;
    cita?: ICita;
    estatusPago?: IEstatusPago;
    tipoPago?: ITipoPago;
}

export class Pago implements IPago {
    constructor(
        public id?: number,
        public monto?: number,
        public fechaEmision?: Moment,
        public fechaEstatus?: Moment,
        public cita?: ICita,
        public estatusPago?: IEstatusPago,
        public tipoPago?: ITipoPago
    ) {}
}
