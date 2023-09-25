import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';

@Component({
    selector: 'jhi-plantilla-historia-medica-nino-detail',
    templateUrl: './plantilla-historia-medica-nino-detail.component.html'
})
export class PlantillaHistoriaMedicaNinoDetailComponent implements OnInit {
    plantillaHistoriaMedicaNino: IPlantillaHistoriaMedicaNino;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaNino }) => {
            this.plantillaHistoriaMedicaNino = plantillaHistoriaMedicaNino;
        });
    }

    previousState() {
        window.history.back();
    }
}
