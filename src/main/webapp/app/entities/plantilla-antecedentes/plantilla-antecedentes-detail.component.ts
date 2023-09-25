import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';

@Component({
    selector: 'jhi-plantilla-antecedentes-detail',
    templateUrl: './plantilla-antecedentes-detail.component.html'
})
export class PlantillaAntecedentesDetailComponent implements OnInit {
    plantillaAntecedentes: IPlantillaAntecedentes;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
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
    previousState() {
        window.history.back();
    }
}
