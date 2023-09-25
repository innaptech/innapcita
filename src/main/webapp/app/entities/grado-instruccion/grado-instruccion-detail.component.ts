import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';

@Component({
    selector: 'jhi-grado-instruccion-detail',
    templateUrl: './grado-instruccion-detail.component.html'
})
export class GradoInstruccionDetailComponent implements OnInit {
    gradoInstruccion: IGradoInstruccion;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gradoInstruccion }) => {
            this.gradoInstruccion = gradoInstruccion;
        });
    }

    previousState() {
        window.history.back();
    }
}
