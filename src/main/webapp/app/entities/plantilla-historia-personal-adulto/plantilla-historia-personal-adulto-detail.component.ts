import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';

@Component({
    selector: 'jhi-plantilla-historia-personal-adulto-detail',
    templateUrl: './plantilla-historia-personal-adulto-detail.component.html'
})
export class PlantillaHistoriaPersonalAdultoDetailComponent implements OnInit {
    plantillaHistoriaPersonalAdulto: IPlantillaHistoriaPersonalAdulto;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaPersonalAdulto }) => {
            this.plantillaHistoriaPersonalAdulto = plantillaHistoriaPersonalAdulto;
        });
    }

    previousState() {
        window.history.back();
    }
}
