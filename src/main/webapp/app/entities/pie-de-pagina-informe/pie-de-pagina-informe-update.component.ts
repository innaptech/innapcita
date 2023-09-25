import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';
import { PieDePaginaInformeService } from './pie-de-pagina-informe.service';

@Component({
    selector: 'jhi-pie-de-pagina-informe-update',
    templateUrl: './pie-de-pagina-informe-update.component.html'
})
export class PieDePaginaInformeUpdateComponent implements OnInit {
    pieDePaginaInforme: IPieDePaginaInforme;
    isSaving: boolean;

    constructor(protected pieDePaginaInformeService: PieDePaginaInformeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pieDePaginaInforme }) => {
            this.pieDePaginaInforme = pieDePaginaInforme;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pieDePaginaInforme.id !== undefined) {
            this.subscribeToSaveResponse(this.pieDePaginaInformeService.update(this.pieDePaginaInforme));
        } else {
            this.subscribeToSaveResponse(this.pieDePaginaInformeService.create(this.pieDePaginaInforme));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPieDePaginaInforme>>) {
        result.subscribe((res: HttpResponse<IPieDePaginaInforme>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
