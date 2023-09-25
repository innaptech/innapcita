import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { TipoConsultaService } from './tipo-consulta.service';

@Component({
    selector: 'jhi-tipo-consulta-update',
    templateUrl: './tipo-consulta-update.component.html'
})
export class TipoConsultaUpdateComponent implements OnInit {
    tipoConsulta: ITipoConsulta;
    isSaving: boolean;

    constructor(protected tipoConsultaService: TipoConsultaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoConsulta }) => {
            this.tipoConsulta = tipoConsulta;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tipoConsulta.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoConsultaService.update(this.tipoConsulta));
        } else {
            this.subscribeToSaveResponse(this.tipoConsultaService.create(this.tipoConsulta));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoConsulta>>) {
        result.subscribe((res: HttpResponse<ITipoConsulta>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
