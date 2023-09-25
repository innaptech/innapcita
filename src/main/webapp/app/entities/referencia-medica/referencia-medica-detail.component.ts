import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';

@Component({
    selector: 'jhi-referencia-medica-detail',
    templateUrl: './referencia-medica-detail.component.html'
})
export class ReferenciaMedicaDetailComponent implements OnInit {
    referenciaMedica: IReferenciaMedica;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ referenciaMedica }) => {
            this.referenciaMedica = referenciaMedica;
        });
    }

    previousState() {
        window.history.back();
    }
}
