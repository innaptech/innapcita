import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEstatusCita } from 'app/shared/model/estatus-cita.model';

@Component({
    selector: 'jhi-estatus-cita-detail',
    templateUrl: './estatus-cita-detail.component.html'
})
export class EstatusCitaDetailComponent implements OnInit {
    estatusCita: IEstatusCita;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ estatusCita }) => {
            this.estatusCita = estatusCita;
        });
    }

    previousState() {
        window.history.back();
    }
}
