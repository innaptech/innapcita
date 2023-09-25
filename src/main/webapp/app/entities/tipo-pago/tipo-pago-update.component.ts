import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITipoPago } from 'app/shared/model/tipo-pago.model';
import { TipoPagoService } from './tipo-pago.service';

@Component({
    selector: 'jhi-tipo-pago-update',
    templateUrl: './tipo-pago-update.component.html'
})
export class TipoPagoUpdateComponent implements OnInit {
    tipoPago: ITipoPago;
    isSaving: boolean;

    constructor(protected tipoPagoService: TipoPagoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoPago }) => {
            this.tipoPago = tipoPago;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoPago.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoPagoService.update(this.tipoPago));
        } else {
            this.subscribeToSaveResponse(this.tipoPagoService.create(this.tipoPago));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoPago>>) {
        result.subscribe((res: HttpResponse<ITipoPago>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
