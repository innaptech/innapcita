import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';

@Component({
    selector: 'jhi-tipo-consulta-detail',
    templateUrl: './tipo-consulta-detail.component.html'
})
export class TipoConsultaDetailComponent implements OnInit {
    tipoConsulta: ITipoConsulta;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoConsulta }) => {
            this.tipoConsulta = tipoConsulta;
        });
    }

    previousState() {
        window.history.back();
    }
}
