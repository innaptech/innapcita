import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IRangoHoras } from 'app/shared/model/rango-horas.model';
import { RangoHorasService } from './rango-horas.service';
import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';
import { HorarioDisponibilidadService } from 'app/entities/horario-disponibilidad';

@Component({
    selector: 'jhi-rango-horas-update',
    templateUrl: './rango-horas-update.component.html'
})
export class RangoHorasUpdateComponent implements OnInit {
    rangoHoras: IRangoHoras;
    isSaving: boolean;

    horariodisponibilidads: IHorarioDisponibilidad[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected rangoHorasService: RangoHorasService,
        protected horarioDisponibilidadService: HorarioDisponibilidadService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rangoHoras }) => {
            this.rangoHoras = rangoHoras;
        });
        this.horarioDisponibilidadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IHorarioDisponibilidad[]>) => mayBeOk.ok),
                map((response: HttpResponse<IHorarioDisponibilidad[]>) => response.body)
            )
            .subscribe(
                (res: IHorarioDisponibilidad[]) => (this.horariodisponibilidads = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rangoHoras.id !== undefined) {
            this.subscribeToSaveResponse(this.rangoHorasService.update(this.rangoHoras));
        } else {
            this.subscribeToSaveResponse(this.rangoHorasService.create(this.rangoHoras));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRangoHoras>>) {
        result.subscribe((res: HttpResponse<IRangoHoras>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackHorarioDisponibilidadById(index: number, item: IHorarioDisponibilidad) {
        return item.id;
    }
}
