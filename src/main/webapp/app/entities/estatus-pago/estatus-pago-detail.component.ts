import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstatusPago } from 'app/shared/model/estatus-pago.model';

@Component({
    selector: 'jhi-estatus-pago-detail',
    templateUrl: './estatus-pago-detail.component.html'
})
export class EstatusPagoDetailComponent implements OnInit {
    estatusPago: IEstatusPago;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estatusPago }) => {
            this.estatusPago = estatusPago;
        });
    }

    previousState() {
        window.history.back();
    }
}
