import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';
import { PlantillaHistoriaPersonalNinoService } from './plantilla-historia-personal-nino.service';

@Component({
    selector: 'jhi-plantilla-historia-personal-nino-update',
    templateUrl: './plantilla-historia-personal-nino-update.component.html'
})
export class PlantillaHistoriaPersonalNinoUpdateComponent implements OnInit {
    plantillaHistoriaPersonalNino: IPlantillaHistoriaPersonalNino;
    isSaving: boolean;
    valores: any[];
    historia: any = {
        id:  1,
        archivo: "a",
        primerNombre: "a",
        segundoNombre: "a",
        primerApellido: "a",
        segundoApellido: "a",
        edad:  1,
        lugarNacimiento: "a",
        fechaNacimiento: "a",
        cedula: "a",
        nacionalidad: "a",
        profesion: "a",
        ocupacionActual: "a",
        religion: "a",
        direccionHabitacion: "a",
        ciudad: "a",
        estado: "a",
        pais: "a",
        telefonoFijo: "a",
        telefonoCelular: "a",
        email: "a",
        familiarMedico: "a",
        carnetFamiliarMedico: "a",
        especialidadFamiliarMedico: "a",
        lugarTrabajoFamiliarMedico: "a",
        appellidosNombresRepresentante: "a",
        lugarFechaNacimientoRepresentante: "a",
        cedulaRepresentante: "a",
        edadRepresentante:  1,
        telefonoCelularRepresentante: "a",
        telefonoHabitacionRepresentante: "a",
        quienTraeNinoConsulta: "a",
        cantidadHermanos:  1,
        posicionHermano:  1,
        relacionMadre: "a",
        relacionPadre: "a",
        relacionHermanos: "a",
        relacionOtros: "a",
        gradoAfectividadNino: "a",
        caracterNino: "a",
        quienCuidaNinoPadresTrabajan: "a",
        sexo: "a",
        referidoPor: "a",
        estadoCivil: "a",
        tipoConsulta: "a",
        especialidad: "a",
        gradoInstruccionMadre: "a",
        gradoInstruccionPadre: "a",
        gradoInstruccionOtro: "a",
        cita: "a"
    }
    constructor(
        protected plantillaHistoriaPersonalNinoService: PlantillaHistoriaPersonalNinoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalNino }) => {
            this.plantillaHistoriaPersonalNino = plantillaHistoriaPersonalNino;
        });
        this.valores = Object.keys(this.historia);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaHistoriaPersonalNino.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaHistoriaPersonalNinoService.update(this.plantillaHistoriaPersonalNino));
        } else {
            this.subscribeToSaveResponse(this.plantillaHistoriaPersonalNinoService.create(this.plantillaHistoriaPersonalNino));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaHistoriaPersonalNino>>) {
        result.subscribe(
            (res: HttpResponse<IPlantillaHistoriaPersonalNino>) => this.onSaveSuccess(),
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
