import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';
import { PlantillaHistoriaMedicaNinoService } from './plantilla-historia-medica-nino.service';

@Component({
    selector: 'jhi-plantilla-historia-medica-nino-update',
    templateUrl: './plantilla-historia-medica-nino-update.component.html'
})
export class PlantillaHistoriaMedicaNinoUpdateComponent implements OnInit {
    plantillaHistoriaMedicaNino: IPlantillaHistoriaMedicaNino;
    isSaving: boolean;
    valores: any[];
    historia: any = {
        id: 1,
        archivo:  "a",
        primerNombre:  "a",
        primerApellido:  "a",
        email:  "a",
        cedula:  "a",
        abuelosViven:  true,
        edadAbuelos:  "a",
        numeroAbuelos:  "a",
        enfermedadAbuelos:  "a",
        tiosViven:  true,
        edadTios:  "a",
        numeroTios:  "a",
        enfermedadTios:  "a",
        padreVive:  true,
        edadPadre:  "a",
        numeroPadre:  "a",
        enfermedadPadre:  "a",
        madreVive:  true,
        edadMadre:  "a",
        numeroMadre:  "a",
        enfermedadMadre:  "a",
        hermanosViven:  true,
        edadHermanos:  "a",
        numeroHermanos:  "a",
        enfermedadHermanos:  "a",
        esposoVive:  true,
        edadEsposo:  "a",
        numeroEsposo:  "a",
        enfermedadEsposo:  "a",
        sarampion:  true,
        renales:  true,
        vertigos:  true,
        cardiacos:  true,
        dolorDeCabeza:  true,
        lechina:  true,
        alergias:  true,
        diarreas:  true,
        varices:  true,
        hipertensionArterial:  true,
        rubeola:  true,
        neurologicos:  true,
        otorrinolaringologos:  true,
        catarros:  true,
        enfermedadMental:  true,
        parotiditis:  true,
        sobrepeso:  true,
        reumaticos:  true,
        infecciones:  true,
        intervencionQuirurgica:  true,
        parasitosis:  true,
        epilepsia:  true,
        dermatologicos:  true,
        pulmonares:  true,
        trastornosDelAprendizaje:  true,
        tiroides:  true,
        asma:  true,
        dolorDeColumna:  true,
        tuberculosis:  true,
        traumatismosCraneoencefalicos:  true,
        mareos:  true,
        desmayos:  true,
        muscularOFractura:  true,
        intestinales:  true,
        enfermedadDeTransmisionSexual:  true,
        estomacales:  true,
        higado:  true,
        colesterolTrigliceridos:  true,
        enfermedadYAnoDelDiagnostico1:  "a",
        tratamientoIndicadoOIntervencionQuirurgica1:  "a",
        enfermedadYAnoDelDiagnostico2:  "a",
        tratamientoIndicadoOIntervencionQuirurgica2:  "a",
        enfermedadYAnoDelDiagnostico3:  "a",
        tratamientoIndicadoOIntervencionQuirurgica3:  "a",
        medicamento1:  "a",
        dosisDiaria1:  "a",
        medicamento2:  "a",
        dosisDiaria2:  "a",
        medicamento3:  "a",
        dosisDiaria3:  "a",
        notas:  "a",
    }
    constructor(
        protected plantillaHistoriaMedicaNinoService: PlantillaHistoriaMedicaNinoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaNino }) => {
            this.plantillaHistoriaMedicaNino = plantillaHistoriaMedicaNino;
        });
        this.valores = Object.keys(this.historia);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaHistoriaMedicaNino.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaHistoriaMedicaNinoService.update(this.plantillaHistoriaMedicaNino));
        } else {
            this.subscribeToSaveResponse(this.plantillaHistoriaMedicaNinoService.create(this.plantillaHistoriaMedicaNino));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaHistoriaMedicaNino>>) {
        result.subscribe(
            (res: HttpResponse<IPlantillaHistoriaMedicaNino>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
