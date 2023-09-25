import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';

@Component({
    selector: 'jhi-historia-medica-nino-detail',
    templateUrl: './historia-medica-nino-detail.component.html'
})
export class HistoriaMedicaNinoDetailComponent implements OnInit {
    historiaMedicaNino: IHistoriaMedicaNino;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaMedicaNino }) => {
            this.historiaMedicaNino = historiaMedicaNino;
        });
    }

    previousState() {
        window.history.back();
    }
}
