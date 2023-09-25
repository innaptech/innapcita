import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPlantillaInforme } from 'app/shared/model/plantilla-informe.model';
import { PlantillaInformeService } from './plantilla-informe.service';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad';

@Component({
    selector: 'jhi-plantilla-informe-update',
    templateUrl: './plantilla-informe-update.component.html'
})
export class PlantillaInformeUpdateComponent implements OnInit {
    plantillaInforme: IPlantillaInforme;
    isSaving: boolean;

    especialidads: IEspecialidad[];

    valores: any[];

    informe: any = {
        pacienteNombre: 'a',
        pacienteFechaNacimiento: 'a',
        pacienteEdad: 'a',
        pacienteCedula: 'a',
        pacienteSexo: 'a',
        pacienteNumeroHistoria: 'a',
        pacienteTelefono: 'a',
        pacienteCorreo: 'a',
        fechaActual: 'a',
        medicoTelefono: '2',
        medicoCorreo: 'a',
        medicoNombre: 'a',
        selloMedico: 'a',
        hora: 'a',
        id: 1,
        fecha: 1,
        peso: 'a',
        tAAcostada: 'a',
        tASentada: 'a',
        tAParada: 'a',
        talla: 'a',
        imc: 'a',
        firma: 'a',
        nuevosSintomas: 'a',
        lateralidadMano: 'a',
        lateralidadPierna: 'a',
        lateralidadOjo: 'a',
        lateralidadOido: 'a',
        examenPiel: 'a',
        examenCabeza: 'a',
        examenOjos: 'a',
        examenOrl: 'a',
        examenCuello: 'a',
        examenCardiovascular: 'a',
        examenPulmonar: 'a',
        examenAbdomen: 'a',
        examenGenitourinario: 'a',
        examenNeurologico: 'a',
        examenArticular: 'a',
        examenNeuromuscular: 'a',
        impresionesDiagnosticas: 'a',
        tratamiento: 'a',
        recomendaciones: 'a',
        proximaConsulta: 1,
        frecuenciaCardiaca: 'a',
        frecuenciaRespiratoria: 'a',
        examenMental: 'a',
        informacionRelevante: 'a',
        recomendacionesGenerales: 'a',
        recipe: 'a',
        indicaciones: 'a',
        cita: 'a',
        examenesComplementarios: 'a',
        examenesIndicados: 'a',
        tipoConsulta: 'q',
        consultaOnline: 'q',
        informeArchivo: 'a',
        recipeArchivo: 'a',
        indicacionesArchivo: 'a'
    };

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected plantillaInformeService: PlantillaInformeService,
        protected especialidadService: EspecialidadService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaInforme }) => {
            this.plantillaInforme = plantillaInforme;
        });
        this.especialidadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidad[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidad[]>) => response.body)
            )
            .subscribe((res: IEspecialidad[]) => (this.especialidads = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.valores = Object.keys(this.informe);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaInforme.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaInformeService.update(this.plantillaInforme));
        } else {
            this.subscribeToSaveResponse(this.plantillaInformeService.create(this.plantillaInforme));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaInforme>>) {
        result.subscribe((res: HttpResponse<IPlantillaInforme>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEspecialidadById(index: number, item: IEspecialidad) {
        return item.id;
    }
}
