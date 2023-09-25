import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';
import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';
import { PlantillaHistoriaPersonalAdultoService } from './plantilla-historia-personal-adulto.service';

@Component({
    selector: 'jhi-plantilla-historia-personal-adulto-update',
    templateUrl: './plantilla-historia-personal-adulto-update.component.html'
})
export class PlantillaHistoriaPersonalAdultoUpdateComponent implements OnInit {
    plantillaHistoriaPersonalAdulto: IPlantillaHistoriaPersonalAdulto;
    isSaving: boolean;
    historia: any = {
        id: 1,
        archivo: "a",
        primerNombre: "a",
        segundoNombre: "a",
        primerApellido: "a",
        segundoApellido: "a",
        edad:1,
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
        direccionTrabajo: "a",
        telefonoTrabajo: "a",
        faxTrabajo: "a",
        familiarMedico: true,
        carnetFamiliarMedico: "a",
        especialidadFamiliarMedico: "a",
        lugarTrabajoFamiliarMedico: "a",
        sexo: "a",
        notas: "a",
        referidoPor: "a",
        estadoCivil: "a",
        tipoConsulta: "a",
        especialidad: "a",
    };
    valores: any[];

    constructor(
        protected plantillaHistoriaPersonalAdultoService: PlantillaHistoriaPersonalAdultoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalAdulto }) => {
            this.plantillaHistoriaPersonalAdulto = plantillaHistoriaPersonalAdulto;
        });
        this.valores = Object.keys(this.historia);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaHistoriaPersonalAdulto.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaHistoriaPersonalAdultoService.update(this.plantillaHistoriaPersonalAdulto));
        } else {
            this.subscribeToSaveResponse(this.plantillaHistoriaPersonalAdultoService.create(this.plantillaHistoriaPersonalAdulto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaHistoriaPersonalAdulto>>) {
        result.subscribe(
            (res: HttpResponse<IPlantillaHistoriaPersonalAdulto>) => this.onSaveSuccess(),
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
