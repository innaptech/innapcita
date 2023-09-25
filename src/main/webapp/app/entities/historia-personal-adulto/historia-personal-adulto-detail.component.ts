import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';

@Component({
    selector: 'jhi-historia-personal-adulto-detail',
    templateUrl: './historia-personal-adulto-detail.component.html'
})
export class HistoriaPersonalAdultoDetailComponent implements OnInit {
    historiaPersonalAdulto: IHistoriaPersonalAdulto;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaPersonalAdulto }) => {
            this.historiaPersonalAdulto = historiaPersonalAdulto;
        });
    }

    previousState() {
        window.history.back();
    }
}
