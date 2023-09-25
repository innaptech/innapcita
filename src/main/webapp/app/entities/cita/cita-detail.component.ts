import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';

import { ICita } from 'app/shared/model/cita.model';

@Component({
    selector: 'jhi-cita-detail',
    templateUrl: './cita-detail.component.html'
})
export class CitaDetailComponent implements OnInit {
    cita: ICita;

    zonaHoraria: string;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cita }) => {
            this.cita = cita;
        });
        this.zonaHoraria = Intl.DateTimeFormat().resolvedOptions().timeZone;
    }

    previousState() {
        window.history.back();
    }
}
