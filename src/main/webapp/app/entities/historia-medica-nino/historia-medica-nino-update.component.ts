import { Component, OnInit, Injector, KeyValueChanges, KeyValueDiffer, KeyValueDiffers, DoCheck } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';
import { HistoriaMedicaNinoService } from './historia-medica-nino.service';
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from 'app/entities/cita';
import { AccountService } from 'app/core';
import { IUser, UserService } from 'app/core';

import {
  RxSpeechRecognitionService,
  resultList,
} from '@kamiazya/ngx-speech-recognition';

@Component({
    selector: 'jhi-historia-medica-nino-update',
    templateUrl: './historia-medica-nino-update.component.html'
})
export class HistoriaMedicaNinoUpdateComponent implements OnInit, DoCheck {
    historiaMedicaNino: IHistoriaMedicaNino;
    isSaving: boolean;

    historiaDiffer: KeyValueDiffer<string, any>;
    didChange: boolean;

    citas: ICita[];

    isAdmin: any;

    cita: any;

    medicamentos: any[];

    service: any;

    addFieldMedicamento(i) {

        this.medicamentos.push({
            nombre: undefined,
            cantidad: undefined,
            frecuencia: undefined,
            dosis: undefined,
            tipo: i
        });

    }

    deleteFieldMedicamento(index) {
        this.medicamentos.splice(index, 1);
    }

    listen(iten, field) {
        console.log(iten[field]);
        let auxIten = (iten[field] ? iten[field] : "") + " ";
        this.service
          .listen()
          .pipe(resultList)
          .subscribe((list: SpeechRecognitionResultList) => {
            iten[field] = auxIten + list.item(0).item(0).transcript;
            console.log('RxComponent:onresult', iten, list);
          });
    }

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected historiaMedicaNinoService: HistoriaMedicaNinoService,
        protected citaService: CitaService,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected accountService: AccountService,
        private injector:Injector,
        private differs: KeyValueDiffers
            ) {
                let isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
                if (isChrome) {
                    this.service = injector.get(RxSpeechRecognitionService);
                }
            }

    ngOnInit() {
        this.isSaving = false;
        this.didChange = false;
        this.activatedRoute.data.subscribe(({ historiaMedicaNino }) => {
            this.historiaMedicaNino = historiaMedicaNino;

            this.historiaDiffer = this.differs.find(this.historiaMedicaNino).create();

            let medicamentos = undefined;
            try {
                if (historiaMedicaNino.medicamento1){
                    medicamentos = JSON.parse(historiaMedicaNino.medicamento1);
                } else {
                    medicamentos = [];
                    medicamentos.push({
                        nombre: undefined,
                        cantidad: undefined,
                        frecuencia: undefined,
                        dosis: undefined,
                        tipo: 1
                    });
                    medicamentos.push({
                        nombre: undefined,
                        cantidad: undefined,
                        frecuencia: undefined,
                        dosis: undefined,
                        tipo: 2
                    });
                    medicamentos.push({
                        nombre: undefined,
                        cantidad: undefined,
                        frecuencia: undefined,
                        dosis: undefined,
                        tipo: 3
                    });
                }
            } catch (ex) {
                console.log(ex);
                medicamentos = [];
                medicamentos.push({
                    nombre: undefined,
                    cantidad: undefined,
                    frecuencia: undefined,
                    dosis: undefined,
                    tipo: 1
                });
                medicamentos.push({
                    nombre: undefined,
                    cantidad: undefined,
                    frecuencia: undefined,
                    dosis: undefined,
                    tipo: 2
                });
                medicamentos.push({
                    nombre: undefined,
                    cantidad: undefined,
                    frecuencia: undefined,
                    dosis: undefined,
                    tipo: 3
                });
            }
            this.medicamentos = medicamentos;
            this.didChange = false;
        });
        this.citaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICita[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICita[]>) => response.body)
            )
            .subscribe((res: ICita[]) => (this.citas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.accountService.identity().then(account => {
            console.log(account);
            var isAdmin = false;
            for(var i = 0; i < account.authorities.length; i++){
                if(account.authorities[i] == 'ROLE_MEDICO' || account.authorities[i] == 'ROLE_ADMIN') {
                    isAdmin = true;
                }
            }
            this.isAdmin = isAdmin;
            console.log(isAdmin);
            if(!isAdmin)
            {
                this.cita.paciente = account;
            }
        });

        var _this = this;
        setTimeout(function() {_this.didChange = false}, 1000);
    }

    previousState() {
        if(!this.isAdmin) {
            if(window.location.href.includes("#"))
                window.location.replace("/#/cita/user");
            else
                window.location.replace("?/cita/user");
        } else {
            if(window.location.href.includes("#"))
                window.location.replace("/#/evolucion-paciente/" + this.historiaMedicaNino.evolucionId + "/edit");
            else
                window.location.replace("?/evolucion-paciente/" + this.historiaMedicaNino.evolucionId + "/edit");
        }
    }

    save() {
        this.isSaving = true;
        this.historiaMedicaNino.medicamento1 = JSON.stringify(this.medicamentos);
        if (this.historiaMedicaNino.id !== undefined) {
            this.subscribeToSaveResponse(this.historiaMedicaNinoService.update(this.historiaMedicaNino));
        } else {
            this.subscribeToSaveResponse(this.historiaMedicaNinoService.create(this.historiaMedicaNino));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoriaMedicaNino>>) {
        result.subscribe((res: HttpResponse<IHistoriaMedicaNino>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
        window.scrollTo(0, 0);
        this.jhiAlertService.success("innapcitasApp.historiaMedicaNino.exito", null, null);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCitaById(index: number, item: ICita) {
        return item.id;
    }

    getFile(){
        this.historiaMedicaNinoService.getFile(this.historiaMedicaNino.id).pipe(
            filter((mayBeOk: HttpResponse<IHistoriaMedicaNino>) => mayBeOk.ok),
              map((response: HttpResponse<IHistoriaMedicaNino>) => response.body)
            )
            .subscribe((res: IHistoriaMedicaNino) => (this.dataUtils.openFile('application/pdf', res.archivo)), (res: HttpErrorResponse) => this.onError("error"));
    }

    ngDoCheck() {
        const changes = this.historiaDiffer.diff(this.historiaMedicaNino);
        console.log(changes);
        if (changes) {
            this.didChange = true;
        }
    }

    next() {
        if (this.didChange) {
            if (confirm("¿Desea guardar los cambios?")) {
              this.save();
            } else {
              this.previousState();
              window.scrollTo(0, 0);
            }
        } else {
            this.previousState();
            window.scrollTo(0, 0);
        }
    }
}
