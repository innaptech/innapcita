import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEstatusPago } from 'app/shared/model/estatus-pago.model';
import { EstatusPagoService } from './estatus-pago.service';

@Component({
    selector: 'jhi-estatus-pago-update',
    templateUrl: './estatus-pago-update.component.html'
})
export class EstatusPagoUpdateComponent implements OnInit {
    estatusPago: IEstatusPago;
    isSaving: boolean;

    constructor(protected estatusPagoService: EstatusPagoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estatusPago }) => {
            this.estatusPago = estatusPago;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.estatusPago.id !== undefined) {
            this.subscribeToSaveResponse(this.estatusPagoService.update(this.estatusPago));
        } else {
            this.subscribeToSaveResponse(this.estatusPagoService.create(this.estatusPago));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstatusPago>>) {
        result.subscribe((res: HttpResponse<IEstatusPago>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
