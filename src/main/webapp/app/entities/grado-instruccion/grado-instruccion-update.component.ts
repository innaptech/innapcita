import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';
import { GradoInstruccionService } from './grado-instruccion.service';

@Component({
    selector: 'jhi-grado-instruccion-update',
    templateUrl: './grado-instruccion-update.component.html'
})
export class GradoInstruccionUpdateComponent implements OnInit {
    gradoInstruccion: IGradoInstruccion;
    isSaving: boolean;

    constructor(protected gradoInstruccionService: GradoInstruccionService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gradoInstruccion }) => {
            this.gradoInstruccion = gradoInstruccion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.gradoInstruccion.id !== undefined) {
            this.subscribeToSaveResponse(this.gradoInstruccionService.update(this.gradoInstruccion));
        } else {
            this.subscribeToSaveResponse(this.gradoInstruccionService.create(this.gradoInstruccion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGradoInstruccion>>) {
        result.subscribe((res: HttpResponse<IGradoInstruccion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
