import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';

@Component({
    selector: 'jhi-historia-medica-adulto-detail',
    templateUrl: './historia-medica-adulto-detail.component.html'
})
export class HistoriaMedicaAdultoDetailComponent implements OnInit {
    historiaMedicaAdulto: IHistoriaMedicaAdulto;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historiaMedicaAdulto }) => {
            this.historiaMedicaAdulto = historiaMedicaAdulto;
        });
    }

    previousState() {
        window.history.back();
    }
}
