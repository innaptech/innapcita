import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';

@Component({
    selector: 'jhi-horario-disponibilidad-detail',
    templateUrl: './horario-disponibilidad-detail.component.html'
})
export class HorarioDisponibilidadDetailComponent implements OnInit {
    horarioDisponibilidad: IHorarioDisponibilidad;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ horarioDisponibilidad }) => {
            this.horarioDisponibilidad = horarioDisponibilidad;
        });
    }

    previousState() {
        window.history.back();
    }
}
