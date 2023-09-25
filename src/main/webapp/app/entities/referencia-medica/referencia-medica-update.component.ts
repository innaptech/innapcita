import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';
import { ReferenciaMedicaService } from './referencia-medica.service';

@Component({
    selector: 'jhi-referencia-medica-update',
    templateUrl: './referencia-medica-update.component.html'
})
export class ReferenciaMedicaUpdateComponent implements OnInit {
    referenciaMedica: IReferenciaMedica;
    isSaving: boolean;

    constructor(protected referenciaMedicaService: ReferenciaMedicaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ referenciaMedica }) => {
            this.referenciaMedica = referenciaMedica;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.referenciaMedica.id !== undefined) {
            this.subscribeToSaveResponse(this.referenciaMedicaService.update(this.referenciaMedica));
        } else {
            this.subscribeToSaveResponse(this.referenciaMedicaService.create(this.referenciaMedica));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IReferenciaMedica>>) {
        result.subscribe((res: HttpResponse<IReferenciaMedica>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
