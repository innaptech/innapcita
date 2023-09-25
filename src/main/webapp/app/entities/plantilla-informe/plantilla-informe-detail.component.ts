import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaInforme } from 'app/shared/model/plantilla-informe.model';

@Component({
    selector: 'jhi-plantilla-informe-detail',
    templateUrl: './plantilla-informe-detail.component.html'
})
export class PlantillaInformeDetailComponent implements OnInit {
    plantillaInforme: IPlantillaInforme;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaInforme }) => {
            this.plantillaInforme = plantillaInforme;
        });
    }

    previousState() {
        window.history.back();
    }
}
