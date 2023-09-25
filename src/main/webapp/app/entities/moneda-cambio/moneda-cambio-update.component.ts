import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IMonedaCambio } from 'app/shared/model/moneda-cambio.model';
import { MonedaCambioService } from './moneda-cambio.service';

@Component({
    selector: 'jhi-moneda-cambio-update',
    templateUrl: './moneda-cambio-update.component.html'
})
export class MonedaCambioUpdateComponent implements OnInit {
    monedaCambio: IMonedaCambio;
    isSaving: boolean;

    constructor(protected monedaCambioService: MonedaCambioService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ monedaCambio }) => {
            this.monedaCambio = monedaCambio;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.monedaCambio.id !== undefined) {
            this.subscribeToSaveResponse(this.monedaCambioService.update(this.monedaCambio));
        } else {
            this.subscribeToSaveResponse(this.monedaCambioService.create(this.monedaCambio));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMonedaCambio>>) {
        result.subscribe((res: HttpResponse<IMonedaCambio>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
