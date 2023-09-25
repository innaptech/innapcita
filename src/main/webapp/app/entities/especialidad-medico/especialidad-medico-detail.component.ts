import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';

@Component({
    selector: 'jhi-especialidad-medico-detail',
    templateUrl: './especialidad-medico-detail.component.html'
})
export class EspecialidadMedicoDetailComponent implements OnInit {
    especialidadMedico: IEspecialidadMedico;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ especialidadMedico }) => {
            this.especialidadMedico = especialidadMedico;
        });
    }

    previousState() {
        window.history.back();
    }
}
