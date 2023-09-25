import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';
import { DomSanitizer } from '@angular/platform-browser';

import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';

@Component({
    selector: 'jhi-examen-complementario-detail',
    templateUrl: './examen-complementario-detail.component.html'
})
export class ExamenComplementarioDetailComponent implements OnInit {
    examenComplementario: IExamenComplementario;

    itemLink: any;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute, private _sanitizationService: DomSanitizer) {}

    ngOnInit() {
        this.itemLink = '';
        this.activatedRoute.data.subscribe(({ examenComplementario }) => {
            this.examenComplementario = examenComplementario;
            this.itemLink = this._sanitizationService.bypassSecurityTrustUrl(
                'data:' + examenComplementario.archivoContentType + ';base64,' + examenComplementario.archivo
            );
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
