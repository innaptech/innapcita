import { Moment } from 'moment';
import { ISexo } from 'app/shared/model/sexo.model';
import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';
import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { IUser } from 'app/core/user/user.model';

export interface IHistoriaPersonalAdulto {
    id?: number;
    primerNombre?: string;
    segundoNombre?: string;
    primerApellido?: string;
    segundoApellido?: string;
    edad?: number;
    lugarNacimiento?: string;
    fechaNacimiento?: Moment;
    cedula?: string;
    nacionalidad?: string;
    profesion?: string;
    ocupacionActual?: string;
    religion?: string;
    direccionHabitacion?: string;
    ciudad?: string;
    estado?: string;
    pais?: string;
    telefonoFijo?: string;
    telefonoCelular?: string;
    email?: string;
    direccionTrabajo?: string;
    telefonoTrabajo?: string;
    faxTrabajo?: string;
    familiarMedico?: boolean;
    carnetFamiliarMedico?: string;
    especialidadFamiliarMedico?: string;
    lugarTrabajoFamiliarMedico?: string;
    notas?: string;
    sexo?: ISexo;
    referidoPor?: IReferenciaMedica;
    estadoCivil?: IEstadoCivil;
    tipoConsulta?: ITipoConsulta;
    especialidad?: IEspecialidad;
    paciente?: IUser;
    archivo?: any;
    historiaMedicaId?: any;
}

export class HistoriaPersonalAdulto implements IHistoriaPersonalAdulto {
    constructor(
        public id?: number,
        public primerNombre?: string,
        public segundoNombre?: string,
        public primerApellido?: string,
        public segundoApellido?: string,
        public edad?: number,
        public lugarNacimiento?: string,
        public fechaNacimiento?: Moment,
        public cedula?: string,
        public nacionalidad?: string,
        public profesion?: string,
        public ocupacionActual?: string,
        public religion?: string,
        public direccionHabitacion?: string,
        public ciudad?: string,
        public estado?: string,
        public pais?: string,
        public telefonoFijo?: string,
        public telefonoCelular?: string,
        public email?: string,
        public direccionTrabajo?: string,
        public telefonoTrabajo?: string,
        public faxTrabajo?: string,
        public familiarMedico?: boolean,
        public carnetFamiliarMedico?: string,
        public especialidadFamiliarMedico?: string,
        public lugarTrabajoFamiliarMedico?: string,
        public notas?: string,
        public sexo?: ISexo,
        public referidoPor?: IReferenciaMedica,
        public estadoCivil?: IEstadoCivil,
        public tipoConsulta?: ITipoConsulta,
        public especialidad?: IEspecialidad,
        public paciente?: IUser,
        public archivo?: any,
        public historiaMedicaId?: any
    ) {
        this.familiarMedico = this.familiarMedico || false;
        this.archivo = this.archivo || false;
    }
}
