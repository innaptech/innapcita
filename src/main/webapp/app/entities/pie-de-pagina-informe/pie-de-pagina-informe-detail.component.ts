import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';

@Component({
    selector: 'jhi-pie-de-pagina-informe-detail',
    templateUrl: './pie-de-pagina-informe-detail.component.html'
})
export class PieDePaginaInformeDetailComponent implements OnInit {
    pieDePaginaInforme: IPieDePaginaInforme;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pieDePaginaInforme }) => {
            this.pieDePaginaInforme = pieDePaginaInforme;
        });
    }

    previousState() {
        window.history.back();
    }
}
