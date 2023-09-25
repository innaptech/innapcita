import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEstatusCita } from 'app/shared/model/estatus-cita.model';
import { EstatusCitaService } from './estatus-cita.service';

@Component({
    selector: 'jhi-estatus-cita-update',
    templateUrl: './estatus-cita-update.component.html'
})
export class EstatusCitaUpdateComponent implements OnInit {
    estatusCita: IEstatusCita;
    isSaving: boolean;

    constructor(protected estatusCitaService: EstatusCitaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estatusCita }) => {
            this.estatusCita = estatusCita;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.estatusCita.id !== undefined) {
            this.subscribeToSaveResponse(this.estatusCitaService.update(this.estatusCita));
        } else {
            this.subscribeToSaveResponse(this.estatusCitaService.create(this.estatusCita));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstatusCita>>) {
        result.subscribe((res: HttpResponse<IEstatusCita>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
