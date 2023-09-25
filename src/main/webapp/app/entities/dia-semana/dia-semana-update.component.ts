import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from './dia-semana.service';

@Component({
    selector: 'jhi-dia-semana-update',
    templateUrl: './dia-semana-update.component.html'
})
export class DiaSemanaUpdateComponent implements OnInit {
    diaSemana: IDiaSemana;
    isSaving: boolean;

    constructor(protected diaSemanaService: DiaSemanaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ diaSemana }) => {
            this.diaSemana = diaSemana;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.diaSemana.id !== undefined) {
            this.subscribeToSaveResponse(this.diaSemanaService.update(this.diaSemana));
        } else {
            this.subscribeToSaveResponse(this.diaSemanaService.create(this.diaSemana));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDiaSemana>>) {
        result.subscribe((res: HttpResponse<IDiaSemana>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
