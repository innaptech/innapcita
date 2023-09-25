import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISexo } from 'app/shared/model/sexo.model';
import { SexoService } from './sexo.service';

@Component({
    selector: 'jhi-sexo-update',
    templateUrl: './sexo-update.component.html'
})
export class SexoUpdateComponent implements OnInit {
    sexo: ISexo;
    isSaving: boolean;

    constructor(protected sexoService: SexoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sexo }) => {
            this.sexo = sexo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sexo.id !== undefined) {
            this.subscribeToSaveResponse(this.sexoService.update(this.sexo));
        } else {
            this.subscribeToSaveResponse(this.sexoService.create(this.sexo));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISexo>>) {
        result.subscribe((res: HttpResponse<ISexo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
