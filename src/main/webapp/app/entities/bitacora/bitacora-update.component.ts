import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IBitacora } from 'app/shared/model/bitacora.model';
import { BitacoraService } from './bitacora.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-bitacora-update',
    templateUrl: './bitacora-update.component.html'
})
export class BitacoraUpdateComponent implements OnInit {
    bitacora: IBitacora;
    isSaving: boolean;

    users: IUser[];
    fecha: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected bitacoraService: BitacoraService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bitacora }) => {
            this.bitacora = bitacora;
            this.fecha = this.bitacora.fecha != null ? this.bitacora.fecha.format(DATE_TIME_FORMAT) : null;
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.bitacora.fecha = this.fecha != null ? moment(this.fecha, DATE_TIME_FORMAT) : null;
        if (this.bitacora.id !== undefined) {
            this.subscribeToSaveResponse(this.bitacoraService.update(this.bitacora));
        } else {
            this.subscribeToSaveResponse(this.bitacoraService.create(this.bitacora));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBitacora>>) {
        result.subscribe((res: HttpResponse<IBitacora>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
