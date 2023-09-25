import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmailCita } from 'app/shared/model/email-cita.model';

@Component({
    selector: 'jhi-email-cita-detail',
    templateUrl: './email-cita-detail.component.html'
})
export class EmailCitaDetailComponent implements OnInit {
    emailCita: IEmailCita;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ emailCita }) => {
            this.emailCita = emailCita;
        });
    }

    previousState() {
        window.history.back();
    }
}
