import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoPago } from 'app/shared/model/tipo-pago.model';

@Component({
    selector: 'jhi-tipo-pago-detail',
    templateUrl: './tipo-pago-detail.component.html'
})
export class TipoPagoDetailComponent implements OnInit {
    tipoPago: ITipoPago;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoPago }) => {
            this.tipoPago = tipoPago;
        });
    }

    previousState() {
        window.history.back();
    }
}
