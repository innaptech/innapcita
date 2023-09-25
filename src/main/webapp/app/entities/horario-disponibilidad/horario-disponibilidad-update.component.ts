import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';
import { HorarioDisponibilidadService } from './horario-disponibilidad.service';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from 'app/entities/dia-semana';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { EspecialidadMedicoService } from 'app/entities/especialidad-medico';
import { IRangoHoras } from 'app/shared/model/rango-horas.model';

@Component({
    selector: 'jhi-horario-disponibilidad-update',
    templateUrl: './horario-disponibilidad-update.component.html'
})
export class HorarioDisponibilidadUpdateComponent implements OnInit {
    horarioDisponibilidad: IHorarioDisponibilidad;
    isSaving: boolean;

    diasemanas: IDiaSemana[];

    especialidadmedicos: IEspecialidadMedico[];

    rango: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected horarioDisponibilidadService: HorarioDisponibilidadService,
        protected diaSemanaService: DiaSemanaService,
        protected especialidadMedicoService: EspecialidadMedicoService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ horarioDisponibilidad }) => {
            this.horarioDisponibilidad = horarioDisponibilidad;
        });
        this.diaSemanaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDiaSemana[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDiaSemana[]>) => response.body)
            )
            .subscribe((res: IDiaSemana[]) => (this.diasemanas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.especialidadMedicoService
            .medico()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidadMedico[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidadMedico[]>) => response.body)
            )
            .subscribe(
                (res: IEspecialidadMedico[]) => (this.especialidadmedicos = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );

        this.rango = [];
        this.rango.push({
            key: 0,
            horaInicio: undefined,
            minutoInicio: undefined,
            horaFin: undefined,
            minutoFin: undefined
        });
    }

    nuevoRango() {
        this.rango.push({
            key: this.rango.length,
            horaInicio: undefined,
            minutoInicio: undefined,
            horaFin: undefined,
            minutoFin: undefined
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.horarioDisponibilidad.horas = this.rango;
        if (this.horarioDisponibilidad.id !== undefined) {
            this.subscribeToSaveResponse(this.horarioDisponibilidadService.update(this.horarioDisponibilidad));
        } else {
            this.subscribeToSaveResponse(this.horarioDisponibilidadService.create(this.horarioDisponibilidad));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHorarioDisponibilidad>>) {
        result.subscribe(
            (res: HttpResponse<IHorarioDisponibilidad>) => this.onSaveSuccess(),
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDiaSemanaById(index: number, item: IDiaSemana) {
        return item.id;
    }

    trackEspecialidadMedicoById(index: number, item: IEspecialidadMedico) {
        return item.id;
    }
}
