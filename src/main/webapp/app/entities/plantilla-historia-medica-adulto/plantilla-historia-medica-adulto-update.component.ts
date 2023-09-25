import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';
import { PlantillaHistoriaMedicaAdultoService } from './plantilla-historia-medica-adulto.service';

@Component({
    selector: 'jhi-plantilla-historia-medica-adulto-update',
    templateUrl: './plantilla-historia-medica-adulto-update.component.html'
})
export class PlantillaHistoriaMedicaAdultoUpdateComponent implements OnInit {
    plantillaHistoriaMedicaAdulto: IPlantillaHistoriaMedicaAdulto;
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
        hijosViven:  true,
        edadHijos:  "a",
        numeroHijos:  "a",
        enfermedadHijos:  "a",
        hijasViven:  true,
        edadHijas:  "a",
        numeroHijas:  "a",
        enfermedadHijas:  "a",
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
        desordenMenstrual:  true,
        usoDeMetodoAnticonceptivo:  true,
        cuantosEmbarazos:  "a",
        cuantosAbortos:  "a",
        cuantosHijosViven:  "a",
        problemasEnLosSenos:  true,
        ultimaMenstruacion:  "a",
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
        protected plantillaHistoriaMedicaAdultoService: PlantillaHistoriaMedicaAdultoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaAdulto }) => {
            this.plantillaHistoriaMedicaAdulto = plantillaHistoriaMedicaAdulto;
        });
        this.valores = Object.keys(this.historia);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaHistoriaMedicaAdulto.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaHistoriaMedicaAdultoService.update(this.plantillaHistoriaMedicaAdulto));
        } else {
            this.subscribeToSaveResponse(this.plantillaHistoriaMedicaAdultoService.create(this.plantillaHistoriaMedicaAdulto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaHistoriaMedicaAdulto>>) {
        result.subscribe(
            (res: HttpResponse<IPlantillaHistoriaMedicaAdulto>) => this.onSaveSuccess(),
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
