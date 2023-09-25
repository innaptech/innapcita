import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';

@Component({
    selector: 'jhi-especialidad-update',
    templateUrl: './especialidad-update.component.html'
})
export class EspecialidadUpdateComponent implements OnInit {
    especialidad: IEspecialidad;
    isSaving: boolean;

    constructor(protected especialidadService: EspecialidadService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ especialidad }) => {
            this.especialidad = especialidad;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.especialidad.id !== undefined) {
            this.subscribeToSaveResponse(this.especialidadService.update(this.especialidad));
        } else {
            this.subscribeToSaveResponse(this.especialidadService.create(this.especialidad));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspecialidad>>) {
        result.subscribe((res: HttpResponse<IEspecialidad>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
