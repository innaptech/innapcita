import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from './estado-civil.service';

@Component({
    selector: 'jhi-estado-civil-update',
    templateUrl: './estado-civil-update.component.html'
})
export class EstadoCivilUpdateComponent implements OnInit {
    estadoCivil: IEstadoCivil;
    isSaving: boolean;

    constructor(protected estadoCivilService: EstadoCivilService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ estadoCivil }) => {
            this.estadoCivil = estadoCivil;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.estadoCivil.id !== undefined) {
            this.subscribeToSaveResponse(this.estadoCivilService.update(this.estadoCivil));
        } else {
            this.subscribeToSaveResponse(this.estadoCivilService.create(this.estadoCivil));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadoCivil>>) {
        result.subscribe((res: HttpResponse<IEstadoCivil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
