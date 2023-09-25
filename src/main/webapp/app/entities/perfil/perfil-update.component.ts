import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from './perfil.service';
import { IUser, UserService } from 'app/core';
import { EspecialidadMedicoService } from 'app/entities/especialidad-medico';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';

@Component({
    selector: 'jhi-perfil-update',
    templateUrl: './perfil-update.component.html'
})
export class PerfilUpdateComponent implements OnInit {
    perfil: IPerfil;
    isSaving: boolean;

    users: IUser[];

    especialidadmedicos: IEspecialidadMedico[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected perfilService: PerfilService,
        protected userService: UserService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute,
        protected especialidadMedicoService: EspecialidadMedicoService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ perfil }) => {
            this.perfil = perfil;
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.especialidadMedicoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidadMedico[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidadMedico[]>) => response.body)
            )
            .subscribe(
                (res: IEspecialidadMedico[]) => {
                    for (let i = 0; i < res.length; i++) {
                        let esp = res[i];
                        console.log(esp);
                        esp.descripcionConcat =
                            esp.descripcion +
                            ' ' +
                            esp.medico.firstName +
                            ' ' +
                            esp.medico.lastName +
                            ' ' +
                            esp.medico.login +
                            ' ' +
                            esp.especialidad.especialidad;
                    }
                    this.especialidadmedicos = res;
                },
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

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.perfil, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.perfil.id !== undefined) {
            this.subscribeToSaveResponse(this.perfilService.update(this.perfil));
        } else {
            this.subscribeToSaveResponse(this.perfilService.create(this.perfil));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfil>>) {
        result.subscribe((res: HttpResponse<IPerfil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackEspecialidadMedicoById(index: number, item: IEspecialidadMedico) {
        return item.id;
    }
}
