import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPago } from 'app/shared/model/pago.model';
import { PagoService } from './pago.service';
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from 'app/entities/cita';
import { IEstatusPago } from 'app/shared/model/estatus-pago.model';
import { EstatusPagoService } from 'app/entities/estatus-pago';
import { ITipoPago } from 'app/shared/model/tipo-pago.model';
import { TipoPagoService } from 'app/entities/tipo-pago';

@Component({
    selector: 'jhi-pago-update',
    templateUrl: './pago-update.component.html'
})
export class PagoUpdateComponent implements OnInit {
    pago: IPago;
    isSaving: boolean;

    citas: ICita[];

    estatuspagos: IEstatusPago[];

    tipopagos: ITipoPago[];
    fechaEmision: string;
    fechaEstatus: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected pagoService: PagoService,
        protected citaService: CitaService,
        protected estatusPagoService: EstatusPagoService,
        protected tipoPagoService: TipoPagoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pago }) => {
            this.pago = pago;
            this.fechaEmision = this.pago.fechaEmision != null ? this.pago.fechaEmision.format(DATE_TIME_FORMAT) : null;
            this.fechaEstatus = this.pago.fechaEstatus != null ? this.pago.fechaEstatus.format(DATE_TIME_FORMAT) : null;
        });
        this.citaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICita[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICita[]>) => response.body)
            )
            .subscribe((res: ICita[]) => (this.citas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.estatusPagoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEstatusPago[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEstatusPago[]>) => response.body)
            )
            .subscribe((res: IEstatusPago[]) => (this.estatuspagos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.tipoPagoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITipoPago[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITipoPago[]>) => response.body)
            )
            .subscribe((res: ITipoPago[]) => (this.tipopagos = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pago.fechaEmision = this.fechaEmision != null ? moment(this.fechaEmision, DATE_TIME_FORMAT) : null;
        this.pago.fechaEstatus = this.fechaEstatus != null ? moment(this.fechaEstatus, DATE_TIME_FORMAT) : null;
        if (this.pago.id !== undefined) {
            this.subscribeToSaveResponse(this.pagoService.update(this.pago));
        } else {
            this.subscribeToSaveResponse(this.pagoService.create(this.pago));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPago>>) {
        result.subscribe((res: HttpResponse<IPago>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCitaById(index: number, item: ICita) {
        return item.id;
    }

    trackEstatusPagoById(index: number, item: IEstatusPago) {
        return item.id;
    }

    trackTipoPagoById(index: number, item: ITipoPago) {
        return item.id;
    }
}
