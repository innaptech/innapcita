import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IEmailCita } from 'app/shared/model/email-cita.model';
import { EmailCitaService } from './email-cita.service';

@Component({
    selector: 'jhi-email-cita-update',
    templateUrl: './email-cita-update.component.html'
})
export class EmailCitaUpdateComponent implements OnInit {
    emailCita: IEmailCita;
    isSaving: boolean;
    valores: any[];
    itemsAceptados: any = {
        nombre: 'a',
        apellido: 'b',
        email: 'a',
        nombreDoctor: 'c',
        apellidoDoctor: 'd',
        tipoConsulta: 'q',
        consultaOnline: 'q',
        emailAdministrador: 'a',
        nombreAdministrador: 'a',
        fecha: '2',
        especialidad: 'a',
        nuevaCitaLink: 'a'
    };

    constructor(protected emailCitaService: EmailCitaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ emailCita }) => {
            this.emailCita = emailCita;
        });
        this.valores = Object.keys(this.itemsAceptados);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.emailCita.id !== undefined) {
            this.subscribeToSaveResponse(this.emailCitaService.update(this.emailCita));
        } else {
            this.subscribeToSaveResponse(this.emailCitaService.create(this.emailCita));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailCita>>) {
        result.subscribe((res: HttpResponse<IEmailCita>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
