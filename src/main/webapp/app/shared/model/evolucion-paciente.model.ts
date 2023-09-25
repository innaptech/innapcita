import { Moment } from 'moment';
import { ICita } from 'app/shared/model/cita.model';
import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';

export interface IEvolucionPaciente {
    id?: number;
    fecha?: Moment;
    peso?: string;
    tAAcostada?: string;
    tASentada?: string;
    tAParada?: string;
    talla?: string;
    imc?: string;
    firma?: string;
    nuevosSintomas?: string;
    lateralidadMano?: string;
    lateralidadPierna?: string;
    lateralidadOjo?: string;
    lateralidadOido?: string;
    examenPiel?: string;
    examenCabeza?: string;
    examenOjos?: string;
    examenOrl?: string;
    examenCuello?: string;
    examenCardiovascular?: string;
    examenPulmonar?: string;
    examenAbdomen?: string;
    examenGenitourinario?: string;
    examenNeurologico?: string;
    examenArticular?: string;
    examenNeuromuscular?: string;
    impresionesDiagnosticas?: string;
    tratamiento?: string;
    recomendaciones?: string;
    proximaConsulta?: Moment;
    frecuenciaCardiaca?: string;
    frecuenciaRespiratoria?: string;
    examenMental?: string;
    informacionRelevante?: string;
    recomendacionesGenerales?: string;
    recipe?: string;
    indicaciones?: string;
    cita?: ICita;
    examenesComplementarios?: IExamenComplementario[];
    informeArchivo?: string;
    recipeArchivo?: string;
    indicacionesArchivo?: string;
    examenesIndicados?: string;
    medicamentos?: any;
    examenesIndicadosList?: any;
}

export class EvolucionPaciente implements IEvolucionPaciente {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public peso?: string,
        public tAAcostada?: string,
        public tASentada?: string,
        public tAParada?: string,
        public talla?: string,
        public imc?: string,
        public firma?: string,
        public nuevosSintomas?: string,
        public lateralidadMano?: string,
        public lateralidadPierna?: string,
        public lateralidadOjo?: string,
        public lateralidadOido?: string,
        public examenPiel?: string,
        public examenCabeza?: string,
        public examenOjos?: string,
        public examenOrl?: string,
        public examenCuello?: string,
        public examenCardiovascular?: string,
        public examenPulmonar?: string,
        public examenAbdomen?: string,
        public examenGenitourinario?: string,
        public examenNeurologico?: string,
        public examenArticular?: string,
        public examenNeuromuscular?: string,
        public impresionesDiagnosticas?: string,
        public tratamiento?: string,
        public recomendaciones?: string,
        public proximaConsulta?: Moment,
        public frecuenciaCardiaca?: string,
        public frecuenciaRespiratoria?: string,
        public examenMental?: string,
        public informacionRelevante?: string,
        public recomendacionesGenerales?: string,
        public recipe?: string,
        public indicaciones?: string,
        public cita?: ICita,
        public examenesComplementarios?: IExamenComplementario[],
        public informeArchivo?: string,
        public recipeArchivo?: string,
        public indicacionesArchivo?: string,
        public examenesIndicados?: string,
        public medicamentos?: any,
        public examenesIndicadosList?: any
    ) {}
}
