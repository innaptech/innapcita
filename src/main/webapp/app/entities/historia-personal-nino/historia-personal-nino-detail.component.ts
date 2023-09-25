import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';

@Component({
    selector: 'jhi-historia-personal-nino-detail',
    templateUrl: './historia-personal-nino-detail.component.html'
})
export class HistoriaPersonalNinoDetailComponent implements OnInit {
    historiaPersonalNino: IHistoriaPersonalNino;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaPersonalNino }) => {
            this.historiaPersonalNino = historiaPersonalNino;
        });
    }

    previousState() {
        window.history.back();
    }
}
