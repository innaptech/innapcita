import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICabeceraInforme } from 'app/shared/model/cabecera-informe.model';

@Component({
    selector: 'jhi-cabecera-informe-detail',
    templateUrl: './cabecera-informe-detail.component.html'
})
export class CabeceraInformeDetailComponent implements OnInit {
    cabeceraInforme: ICabeceraInforme;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cabeceraInforme }) => {
            this.cabeceraInforme = cabeceraInforme;
        });
    }

    previousState() {
        window.history.back();
    }
}
