import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';
import { ExamenComplementarioService } from './examen-complementario.service';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';
import { EvolucionPacienteService } from 'app/entities/evolucion-paciente';

@Component({
    selector: 'jhi-examen-complementario-update',
    templateUrl: './examen-complementario-update.component.html'
})
export class ExamenComplementarioUpdateComponent implements OnInit {
    examenComplementario: IExamenComplementario;
    isSaving: boolean;

    evolucionpacientes: IEvolucionPaciente[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected examenComplementarioService: ExamenComplementarioService,
        protected evolucionPacienteService: EvolucionPacienteService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ examenComplementario }) => {
            this.examenComplementario = examenComplementario;
        });
        this.evolucionPacienteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEvolucionPaciente[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEvolucionPaciente[]>) => response.body)
            )
            .subscribe(
                (res: IEvolucionPaciente[]) => (this.evolucionpacientes = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.examenComplementario.id !== undefined) {
            this.subscribeToSaveResponse(this.examenComplementarioService.update(this.examenComplementario));
        } else {
            this.subscribeToSaveResponse(this.examenComplementarioService.create(this.examenComplementario));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExamenComplementario>>) {
        result.subscribe(
            (res: HttpResponse<IExamenComplementario>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEvolucionPacienteById(index: number, item: IEvolucionPaciente) {
        return item.id;
    }
}
