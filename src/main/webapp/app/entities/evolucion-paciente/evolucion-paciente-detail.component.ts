import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';

@Component({
    selector: 'jhi-evolucion-paciente-detail',
    templateUrl: './evolucion-paciente-detail.component.html'
})
export class EvolucionPacienteDetailComponent implements OnInit {
    evolucionPaciente: IEvolucionPaciente;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evolucionPaciente }) => {
            this.evolucionPaciente = evolucionPaciente;
        });
    }

    previousState() {
        window.history.back();
    }
}
