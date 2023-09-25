import { IUser } from 'app/core/user/user.model';

export interface IHistoriaMedicaNino {
    id?: number;
    primerNombre?: string;
    primerApellido?: string;
    email?: string;
    cedula?: string;
    abuelosViven?: boolean;
    edadAbuelos?: string;
    numeroAbuelos?: string;
    enfermedadAbuelos?: string;
    tiosViven?: boolean;
    edadTios?: string;
    numeroTios?: string;
    enfermedadTios?: string;
    padreVive?: boolean;
    edadPadre?: string;
    numeroPadre?: string;
    enfermedadPadre?: string;
    madreVive?: boolean;
    edadMadre?: string;
    numeroMadre?: string;
    enfermedadMadre?: string;
    hermanosViven?: boolean;
    edadHermanos?: string;
    numeroHermanos?: string;
    enfermedadHermanos?: string;
    esposoVive?: boolean;
    edadEsposo?: string;
    numeroEsposo?: string;
    enfermedadEsposo?: string;
    sarampion?: boolean;
    renales?: boolean;
    vertigos?: boolean;
    cardiacos?: boolean;
    dolorDeCabeza?: boolean;
    lechina?: boolean;
    alergias?: boolean;
    diarreas?: boolean;
    varices?: boolean;
    hipertensionArterial?: boolean;
    rubeola?: boolean;
    neurologicos?: boolean;
    otorrinolaringologos?: boolean;
    catarros?: boolean;
    enfermedadMental?: boolean;
    parotiditis?: boolean;
    sobrepeso?: boolean;
    reumaticos?: boolean;
    infecciones?: boolean;
    intervencionQuirurgica?: boolean;
    parasitosis?: boolean;
    epilepsia?: boolean;
    dermatologicos?: boolean;
    pulmonares?: boolean;
    trastornosDelAprendizaje?: boolean;
    tiroides?: boolean;
    asma?: boolean;
    dolorDeColumna?: boolean;
    tuberculosis?: boolean;
    traumatismosCraneoencefalicos?: boolean;
    mareos?: boolean;
    desmayos?: boolean;
    muscularOFractura?: boolean;
    intestinales?: boolean;
    enfermedadDeTransmisionSexual?: boolean;
    estomacales?: boolean;
    higado?: boolean;
    colesterolTrigliceridos?: boolean;
    enfermedadYAnoDelDiagnostico1?: string;
    tratamientoIndicadoOIntervencionQuirurgica1?: string;
    enfermedadYAnoDelDiagnostico2?: string;
    tratamientoIndicadoOIntervencionQuirurgica2?: string;
    enfermedadYAnoDelDiagnostico3?: string;
    tratamientoIndicadoOIntervencionQuirurgica3?: string;
    medicamento1?: string;
    dosisDiaria1?: string;
    medicamento2?: string;
    dosisDiaria2?: string;
    medicamento3?: string;
    dosisDiaria3?: string;
    notas?: string;
    paciente?: IUser;
    archivo?: any;
    evolucionId?: any;
}

export class HistoriaMedicaNino implements IHistoriaMedicaNino {
    constructor(
        public id?: number,
        public primerNombre?: string,
        public primerApellido?: string,
        public email?: string,
        public cedula?: string,
        public abuelosViven?: boolean,
        public edadAbuelos?: string,
        public numeroAbuelos?: string,
        public enfermedadAbuelos?: string,
        public tiosViven?: boolean,
        public edadTios?: string,
        public numeroTios?: string,
        public enfermedadTios?: string,
        public padreVive?: boolean,
        public edadPadre?: string,
        public numeroPadre?: string,
        public enfermedadPadre?: string,
        public madreVive?: boolean,
        public edadMadre?: string,
        public numeroMadre?: string,
        public enfermedadMadre?: string,
        public hermanosViven?: boolean,
        public edadHermanos?: string,
        public numeroHermanos?: string,
        public enfermedadHermanos?: string,
        public esposoVive?: boolean,
        public edadEsposo?: string,
        public numeroEsposo?: string,
        public enfermedadEsposo?: string,
        public sarampion?: boolean,
        public renales?: boolean,
        public vertigos?: boolean,
        public cardiacos?: boolean,
        public dolorDeCabeza?: boolean,
        public lechina?: boolean,
        public alergias?: boolean,
        public diarreas?: boolean,
        public varices?: boolean,
        public hipertensionArterial?: boolean,
        public rubeola?: boolean,
        public neurologicos?: boolean,
        public otorrinolaringologos?: boolean,
        public catarros?: boolean,
        public enfermedadMental?: boolean,
        public parotiditis?: boolean,
        public sobrepeso?: boolean,
        public reumaticos?: boolean,
        public infecciones?: boolean,
        public intervencionQuirurgica?: boolean,
        public parasitosis?: boolean,
        public epilepsia?: boolean,
        public dermatologicos?: boolean,
        public pulmonares?: boolean,
        public trastornosDelAprendizaje?: boolean,
        public tiroides?: boolean,
        public asma?: boolean,
        public dolorDeColumna?: boolean,
        public tuberculosis?: boolean,
        public traumatismosCraneoencefalicos?: boolean,
        public mareos?: boolean,
        public desmayos?: boolean,
        public muscularOFractura?: boolean,
        public intestinales?: boolean,
        public enfermedadDeTransmisionSexual?: boolean,
        public estomacales?: boolean,
        public higado?: boolean,
        public colesterolTrigliceridos?: boolean,
        public enfermedadYAnoDelDiagnostico1?: string,
        public tratamientoIndicadoOIntervencionQuirurgica1?: string,
        public enfermedadYAnoDelDiagnostico2?: string,
        public tratamientoIndicadoOIntervencionQuirurgica2?: string,
        public enfermedadYAnoDelDiagnostico3?: string,
        public tratamientoIndicadoOIntervencionQuirurgica3?: string,
        public medicamento1?: string,
        public dosisDiaria1?: string,
        public medicamento2?: string,
        public dosisDiaria2?: string,
        public medicamento3?: string,
        public dosisDiaria3?: string,
        public notas?: string,
        public paciente?: IUser,
        public archivo?: any,
        public evolucionId?: any
    ) {
        this.abuelosViven = this.abuelosViven || false;
        this.tiosViven = this.tiosViven || false;
        this.padreVive = this.padreVive || false;
        this.madreVive = this.madreVive || false;
        this.hermanosViven = this.hermanosViven || false;
        this.esposoVive = this.esposoVive || false;
        this.sarampion = this.sarampion || false;
        this.renales = this.renales || false;
        this.vertigos = this.vertigos || false;
        this.cardiacos = this.cardiacos || false;
        this.dolorDeCabeza = this.dolorDeCabeza || false;
        this.lechina = this.lechina || false;
        this.alergias = this.alergias || false;
        this.diarreas = this.diarreas || false;
        this.varices = this.varices || false;
        this.hipertensionArterial = this.hipertensionArterial || false;
        this.rubeola = this.rubeola || false;
        this.neurologicos = this.neurologicos || false;
        this.otorrinolaringologos = this.otorrinolaringologos || false;
        this.catarros = this.catarros || false;
        this.enfermedadMental = this.enfermedadMental || false;
        this.parotiditis = this.parotiditis || false;
        this.sobrepeso = this.sobrepeso || false;
        this.reumaticos = this.reumaticos || false;
        this.infecciones = this.infecciones || false;
        this.intervencionQuirurgica = this.intervencionQuirurgica || false;
        this.parasitosis = this.parasitosis || false;
        this.epilepsia = this.epilepsia || false;
        this.dermatologicos = this.dermatologicos || false;
        this.pulmonares = this.pulmonares || false;
        this.trastornosDelAprendizaje = this.trastornosDelAprendizaje || false;
        this.tiroides = this.tiroides || false;
        this.asma = this.asma || false;
        this.dolorDeColumna = this.dolorDeColumna || false;
        this.tuberculosis = this.tuberculosis || false;
        this.traumatismosCraneoencefalicos = this.traumatismosCraneoencefalicos || false;
        this.mareos = this.mareos || false;
        this.desmayos = this.desmayos || false;
        this.muscularOFractura = this.muscularOFractura || false;
        this.intestinales = this.intestinales || false;
        this.enfermedadDeTransmisionSexual = this.enfermedadDeTransmisionSexual || false;
        this.estomacales = this.estomacales || false;
        this.higado = this.higado || false;
        this.colesterolTrigliceridos = this.colesterolTrigliceridos || false;
        this.archivo = this.archivo || false;
    }
}
