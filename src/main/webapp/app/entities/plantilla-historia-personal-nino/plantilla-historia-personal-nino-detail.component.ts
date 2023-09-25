import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';

@Component({
    selector: 'jhi-plantilla-historia-personal-nino-detail',
    templateUrl: './plantilla-historia-personal-nino-detail.component.html'
})
export class PlantillaHistoriaPersonalNinoDetailComponent implements OnInit {
    plantillaHistoriaPersonalNino: IPlantillaHistoriaPersonalNino;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalNino }) => {
            this.plantillaHistoriaPersonalNino = plantillaHistoriaPersonalNino;
        });
    }

    previousState() {
        window.history.back();
    }
}
