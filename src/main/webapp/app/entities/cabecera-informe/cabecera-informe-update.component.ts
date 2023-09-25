import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICabeceraInforme } from 'app/shared/model/cabecera-informe.model';
import { CabeceraInformeService } from './cabecera-informe.service';

@Component({
    selector: 'jhi-cabecera-informe-update',
    templateUrl: './cabecera-informe-update.component.html'
})
export class CabeceraInformeUpdateComponent implements OnInit {
    cabeceraInforme: ICabeceraInforme;
    isSaving: boolean;

    constructor(protected cabeceraInformeService: CabeceraInformeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cabeceraInforme }) => {
            this.cabeceraInforme = cabeceraInforme;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cabeceraInforme.id !== undefined) {
            this.subscribeToSaveResponse(this.cabeceraInformeService.update(this.cabeceraInforme));
        } else {
            this.subscribeToSaveResponse(this.cabeceraInformeService.create(this.cabeceraInforme));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICabeceraInforme>>) {
        result.subscribe((res: HttpResponse<ICabeceraInforme>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
