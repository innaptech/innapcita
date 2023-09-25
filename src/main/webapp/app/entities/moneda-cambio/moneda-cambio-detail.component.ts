import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMonedaCambio } from 'app/shared/model/moneda-cambio.model';

@Component({
    selector: 'jhi-moneda-cambio-detail',
    templateUrl: './moneda-cambio-detail.component.html'
})
export class MonedaCambioDetailComponent implements OnInit {
    monedaCambio: IMonedaCambio;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monedaCambio }) => {
            this.monedaCambio = monedaCambio;
        });
    }

    previousState() {
        window.history.back();
    }
}
