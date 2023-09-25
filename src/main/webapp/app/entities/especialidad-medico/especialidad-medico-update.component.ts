import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { EspecialidadMedicoService } from './especialidad-medico.service';
import { IUser, UserService } from 'app/core';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from 'app/entities/especialidad';
import { AccountService, Account } from 'app/core';

@Component({
    selector: 'jhi-especialidad-medico-update',
    templateUrl: './especialidad-medico-update.component.html'
})
export class EspecialidadMedicoUpdateComponent implements OnInit {
    especialidadMedico: IEspecialidadMedico;
    isSaving: boolean;

    users: IUser[];

    especialidads: IEspecialidad[];

    account: any;

    keyword2 = 'email';

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected especialidadMedicoService: EspecialidadMedicoService,
        protected userService: UserService,
        protected especialidadService: EspecialidadService,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService
    ) {}

    selectEventPaciente(item) {
        // do something with selected item
        console.log(item);
        if (item) {
            this.especialidadMedico.medico = item;
        }
    }

    onChangeSearchUsers(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
        if (val && val != '') {
            this.userService
                .querySearchMedic(val)
                .pipe(
                    filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                    map((response: HttpResponse<IUser[]>) => response.body)
                )
                .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    onFocused(e) {
    // do something when input is focused
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ especialidadMedico }) => {
            this.especialidadMedico = especialidadMedico;
        });
        this.especialidadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEspecialidad[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEspecialidad[]>) => response.body)
            )
            .subscribe((res: IEspecialidad[]) => (this.especialidads = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.especialidadMedico.id !== undefined) {
            this.subscribeToSaveResponse(this.especialidadMedicoService.update(this.especialidadMedico));
        } else {
            this.subscribeToSaveResponse(this.especialidadMedicoService.create(this.especialidadMedico));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEspecialidadMedico>>) {
        result.subscribe((res: HttpResponse<IEspecialidadMedico>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEspecialidadById(index: number, item: IEspecialidad) {
        return item.id;
    }

    protected setUser(users) {
        console.log('LLEGA');
        this.users = users;
        this.accountService.identity().then((account: Account) => {
            this.account = account;
            console.log(account);
            for (let i = 0; i < users.length; i++) {
                console.log(users[i].login);
                if (users[i].login === account.login) {
                    this.especialidadMedico.medico = users[i];
                    console.log('Asignando usuario: ', users[i]);
                }
            }
        });
    }
}
