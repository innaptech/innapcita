import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';

@Component({
    selector: 'jhi-plantilla-historia-medica-adulto-detail',
    templateUrl: './plantilla-historia-medica-adulto-detail.component.html'
})
export class PlantillaHistoriaMedicaAdultoDetailComponent implements OnInit {
    plantillaHistoriaMedicaAdulto: IPlantillaHistoriaMedicaAdulto;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantillaHistoriaMedicaAdulto }) => {
            this.plantillaHistoriaMedicaAdulto = plantillaHistoriaMedicaAdulto;
        });
    }

    previousState() {
        window.history.back();
    }
}
