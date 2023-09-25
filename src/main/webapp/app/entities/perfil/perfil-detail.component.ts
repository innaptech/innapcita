import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPerfil } from 'app/shared/model/perfil.model';

@Component({
    selector: 'jhi-perfil-detail',
    templateUrl: './perfil-detail.component.html'
})
export class PerfilDetailComponent implements OnInit {
    perfil: IPerfil;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ perfil }) => {
            this.perfil = perfil;
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
