import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiDataUtils } from 'ng-jhipster';
import { IPlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';
import { PlantillaAntecedentesService } from './plantilla-antecedentes.service';

@Component({
    selector: 'jhi-plantilla-antecedentes-update',
    templateUrl: './plantilla-antecedentes-update.component.html'
})
export class PlantillaAntecedentesUpdateComponent implements OnInit {
    plantillaAntecedentes: IPlantillaAntecedentes;
    isSaving: boolean;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected plantillaAntecedentesService: PlantillaAntecedentesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantillaAntecedentes }) => {
            this.plantillaAntecedentes = plantillaAntecedentes;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantillaAntecedentes.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaAntecedentesService.update(this.plantillaAntecedentes));
        } else {
            this.subscribeToSaveResponse(this.plantillaAntecedentesService.create(this.plantillaAntecedentes));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPlantillaAntecedentes>>) {
        result.subscribe(
            (res: HttpResponse<IPlantillaAntecedentes>) => this.onSaveSuccess(),
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
}
