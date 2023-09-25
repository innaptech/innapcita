import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRangoHoras } from 'app/shared/model/rango-horas.model';

@Component({
    selector: 'jhi-rango-horas-detail',
    templateUrl: './rango-horas-detail.component.html'
})
export class RangoHorasDetailComponent implements OnInit {
    rangoHoras: IRangoHoras;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ rangoHoras }) => {
            this.rangoHoras = rangoHoras;
        });
    }

    previousState() {
        window.history.back();
    }
}
