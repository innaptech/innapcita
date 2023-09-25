import { Component, OnInit, Injector } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';
import { IExamenComplementario, ExamenComplementario } from 'app/shared/model/examen-complementario.model';
import { ExamenComplementarioService } from '../examen-complementario/examen-complementario.service';
import { EvolucionPacienteService } from './evolucion-paciente.service';
import { ICita } from 'app/shared/model/cita.model';
import { CitaService } from 'app/entities/cita';
import { UserService, User, IUser } from 'app/core';

import { IEstatusCita } from 'app/shared/model/estatus-cita.model';
import { EstatusCitaService } from 'app/entities/estatus-cita';

import { RxSpeechRecognitionService, resultList } from '@kamiazya/ngx-speech-recognition';

@Component({
    selector: 'jhi-evolucion-paciente-update',
    templateUrl: './evolucion-paciente-update.component.html'
})
export class EvolucionPacienteUpdateComponent implements OnInit {
    evolucionPaciente: IEvolucionPaciente;
    evolucionesAnteriores: IEvolucionPaciente[];
    isSaving: boolean;

    recipientesCorreo: any;

    recipes: any[];
    indicaciones: any[];

    citas: ICita[];
    fecha: string;
    proximaConsultaDp: any;

    datePop: boolean;

    gettingInformeFile: any;
    gettingEvolucionFile: any;
    gettingRecipeFile: any;
    recipeToPrint: any;
    gettingIndicacionesFile: any;
    indicacionToPrint: any;
    sendingInformeFile: any;
    sendingEvolucionFile: any;

    service: any;

    estatusCitas: IEstatusCita[];

    showDots: boolean;

    listen(iten, field) {
        console.log(iten[field]);
        let auxIten = (iten[field] ? iten[field] : '') + ' ';
        this.service
            .listen()
            .pipe(resultList)
            .subscribe((list: SpeechRecognitionResultList) => {
                iten[field] = auxIten + list.item(0).item(0).transcript;
                console.log('RxComponent:onresult', iten, list);
                this.updateRecipes();
            });
    }

    updateRecipes() {
        this.recipes = [];
        this.indicaciones = [];

        //Medicinas
        var medicinasNormales = '';
        var indicacionesMedicinas = '';
        if (this.evolucionPaciente.medicamentos) {
            for (var i = 0; i < this.evolucionPaciente.medicamentos.length; i++) {
                const medicina = this.evolucionPaciente.medicamentos[i];
                if (medicina.nombre) {
                    if (!medicina.esPsicotropico) {
                        medicinasNormales += '<p>' + medicina.nombre + '</p>';
                    } else {
                        this.recipes.push('<p>' + medicina.nombre + '</p>');
                    }
                    indicacionesMedicinas += '<p>' + medicina.nombre + ': ' + medicina.indicacion + '</p>';
                }
            }

            if (medicinasNormales) this.recipes.push(medicinasNormales);
            if (indicacionesMedicinas) this.indicaciones.push(indicacionesMedicinas);
        }

        //Examenes indicados
        if (this.evolucionPaciente.examenesIndicadosList) {
            for (var i = 0; i < this.evolucionPaciente.examenesIndicadosList.length; i++) {
                const examen = this.evolucionPaciente.examenesIndicadosList[i];
                if (examen.examen) {
                    this.indicaciones.push('<p>' + examen.examen + ': ' + examen.referencia + '</p>');
                }
            }
        }

        //indicaciones medicas
        if (this.evolucionPaciente.recomendaciones) {
            this.indicaciones.push('<p>' + this.evolucionPaciente.recomendaciones + '</p>');
        }

        //indicaciones medicas
        if (this.evolucionPaciente.recomendacionesGenerales) {
            this.indicaciones.push('<p>' + this.evolucionPaciente.recomendacionesGenerales + '</p>');
        }

        console.log(this.recipes);
        console.log(this.indicaciones);
    }

    getInformeFile() {
        this.gettingInformeFile = true;
        this.jhiAlertService.success('innapcitasApp.evolucionPaciente.print', null, null);
        this.save();
    }

    getEvolucionFile() {
        this.gettingEvolucionFile = true;
        this.jhiAlertService.success('innapcitasApp.evolucionPaciente.print', null, null);
        this.save();
    }

    sendInformeFile() {
        console.log(this.evolucionPaciente);
        var text;
        var recipientesCorreo = prompt(
            'Enviar informe:',
            this.evolucionPaciente.cita.paciente.email + ',' + this.evolucionPaciente.cita.especialidadMedico.medico.email + ','
        );
        if (recipientesCorreo) {
            this.recipientesCorreo = recipientesCorreo;
            this.sendingInformeFile = true;
            this.jhiAlertService.success('innapcitasApp.evolucionPaciente.send', null, null);
            this.save();
        }
    }

    sendEvolucionFile() {
        console.log(this.evolucionPaciente);
        var text;
        var recipientesCorreo = prompt(
            'Enviar informe:',
            this.evolucionPaciente.cita.paciente.email + ',' + this.evolucionPaciente.cita.especialidadMedico.medico.email + ','
        );
        if (recipientesCorreo) {
            this.recipientesCorreo = recipientesCorreo;
            this.sendingEvolucionFile = true;
            this.jhiAlertService.success('innapcitasApp.evolucionPaciente.send', null, null);
            this.save();
        }
    }

    getRecipeFile(i) {
        this.gettingRecipeFile = true;
        console.log(i);
        this.recipeToPrint = i;
        this.save();
    }

    getIndicacionesFile(i) {
        this.gettingIndicacionesFile = true;
        console.log(i);
        this.indicacionToPrint = i;
        this.save();
    }

    showDate() {
        this.datePop = !this.datePop;
    }

    calcularImc() {
        if (this.evolucionPaciente.talla && this.evolucionPaciente.peso) {
            const imc =
                parseFloat(this.evolucionPaciente.peso) /
                (parseFloat(this.evolucionPaciente.talla) * parseFloat(this.evolucionPaciente.talla));
            const result = Math.round(imc * 100) / 100;
            this.evolucionPaciente.imc = result.toString();
        }
    }

    addFieldExamen() {
        this.evolucionPaciente.examenesComplementarios.push({
            descripcion: undefined,
            archivo: undefined
        });
    }

    deleteFieldExamen(index) {
        this.evolucionPaciente.examenesComplementarios.splice(index, 1);
    }

    addFieldMedicamento() {
        console.log(this.evolucionPaciente.medicamentos);

        this.evolucionPaciente.medicamentos.push({
            nombre: undefined,
            indicacion: undefined,
            esPsicotropico: undefined
        });
    }

    deleteFieldMedicamento(index) {
        this.evolucionPaciente.medicamentos.splice(index, 1);
        this.updateRecipes();
    }

    addFieldExamenIndicado() {
        console.log(this.evolucionPaciente.examenesIndicadosList);

        this.evolucionPaciente.examenesIndicadosList.push({
            examen: undefined,
            referencia: undefined
        });
    }

    deleteFieldExamenIndicado(index) {
        this.evolucionPaciente.examenesIndicadosList.splice(index, 1);
        this.updateRecipes();
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        if (field.id) {
            this.examenComplementarioService
                .find(field.id)
                .pipe(
                    filter((mayBeOk: HttpResponse<IExamenComplementario>) => mayBeOk.ok),
                    map((response: HttpResponse<IExamenComplementario>) => response.body)
                )
                .subscribe(
                    (res: IExamenComplementario) => this.dataUtils.openFile(contentType, res.archivo),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return null;
        } else {
            return this.dataUtils.openFile(contentType, field.archivo);
        }
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected evolucionPacienteService: EvolucionPacienteService,
        protected examenComplementarioService: ExamenComplementarioService,
        protected citaService: CitaService,
        protected activatedRoute: ActivatedRoute,
        private injector: Injector,
        protected estatusCitaService: EstatusCitaService,
        protected userService: UserService
    ) {
        let isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
        if (isChrome) {
            this.service = injector.get(RxSpeechRecognitionService);
        }
    }

    ngOnInit() {
        this.isSaving = false;
        this.showDots = false;
        window.scrollTo(0, 0);
        this.activatedRoute.data.subscribe(({ evolucionPaciente }) => {
            this.evolucionPaciente = evolucionPaciente;
            this.fecha = this.evolucionPaciente.fecha != null ? this.evolucionPaciente.fecha.format(DATE_TIME_FORMAT) : null;
            if (evolucionPaciente.id) {
                let medicamentos = undefined;
                try {
                    if (evolucionPaciente.tratamiento) {
                        medicamentos = JSON.parse(evolucionPaciente.tratamiento);
                    } else {
                        medicamentos = [];
                        medicamentos.push({
                            nombre: undefined,
                            indicacion: undefined,
                            esPsicotropico: undefined
                        });
                    }
                } catch (ex) {
                    console.log(ex);
                    medicamentos = [];
                    medicamentos.push({
                        nombre: undefined,
                        indicacion: undefined,
                        esPsicotropico: undefined
                    });
                }
                evolucionPaciente.medicamentos = medicamentos;

                let examenesIndicados = undefined;
                try {
                    if (evolucionPaciente.examenesIndicados) {
                        examenesIndicados = JSON.parse(evolucionPaciente.examenesIndicados);
                    } else {
                        examenesIndicados = [];
                        examenesIndicados.push({
                            examen: undefined,
                            referencia: undefined
                        });
                    }
                } catch (ex) {
                    console.log(ex);
                    examenesIndicados = [];
                    examenesIndicados.push({
                        examen: undefined,
                        referencia: undefined
                    });
                }
                evolucionPaciente.examenesIndicadosList = examenesIndicados;

                this.updateRecipes();

                this.userService
                    .getPatient(evolucionPaciente.cita.paciente.login)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IUser>) => mayBeOk.ok),
                        map((response: HttpResponse<IUser>) => response.body)
                    )
                    .subscribe(
                        (res: IUser) => {
                            let agregados = [];
                            for (let i = 0; i < res.evolucionPacientes.length; i++) {
                                let evo = res.evolucionPacientes[i];
                                if (evo.id != this.evolucionPaciente.id) {
                                    agregados.push(evo);
                                }
                            }
                            this.evolucionesAnteriores = agregados;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            }
        });
        this.citaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICita[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICita[]>) => response.body)
            )
            .subscribe((res: ICita[]) => (this.citas = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.estatusCitaService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IEstatusCita[]>) => mayBeOk.ok),
                map((response: HttpResponse<IEstatusCita[]>) => response.body)
            )
            .subscribe((res: IEstatusCita[]) => (this.estatusCitas = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.scrollTo(0, 0);
        /*if(window.location.href.includes("#"))
            window.location.replace("/#/cita/user");
        else
            window.location.replace("?/cita/user");*/
    }

    save() {
        this.isSaving = true;
        this.evolucionPaciente.fecha = this.fecha != null ? moment(this.fecha, DATE_TIME_FORMAT) : null;

        this.evolucionPaciente.tratamiento = JSON.stringify(this.evolucionPaciente.medicamentos);
        this.evolucionPaciente.examenesIndicados = JSON.stringify(this.evolucionPaciente.examenesIndicadosList);
        this.evolucionPaciente.recipe = JSON.stringify(this.recipes);
        this.evolucionPaciente.indicaciones = JSON.stringify(this.indicaciones);

        if (this.evolucionPaciente.id !== undefined) {
            this.subscribeToSaveResponse(this.evolucionPacienteService.update(this.evolucionPaciente));
        } else {
            this.subscribeToSaveResponse(this.evolucionPacienteService.create(this.evolucionPaciente));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvolucionPaciente>>) {
        result.subscribe((res: HttpResponse<IEvolucionPaciente>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        const uA = navigator.userAgent;
        const vendor = navigator.vendor;
        var isSafari = /Safari/i.test(uA) && /Apple Computer/.test(vendor) && !/Mobi|Android/i.test(uA);

        if (this.gettingInformeFile) {
            if (!isSafari) {
                this.evolucionPacienteService
                    .getInforme(this.evolucionPaciente.id, true)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingInformeFile = false;
                            this.isSaving = false;
                            this.dataUtils.openFile('application/pdf', res.informeArchivo);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            } else {
                this.evolucionPacienteService
                    .getInforme(this.evolucionPaciente.id, true)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingInformeFile = false;
                            this.isSaving = false;
                            this.dataUtils.downloadFile('application/pdf', res.informeArchivo, 'informe' + this.evolucionPaciente.id);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            }
        }
        if (this.gettingEvolucionFile) {
            if (!isSafari) {
                this.evolucionPacienteService
                    .getInforme(this.evolucionPaciente.id, false)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingInformeFile = false;
                            this.isSaving = false;
                            this.dataUtils.openFile('application/pdf', res.informeArchivo);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            } else {
                this.evolucionPacienteService
                    .getInforme(this.evolucionPaciente.id, true)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingInformeFile = false;
                            this.isSaving = false;
                            this.dataUtils.downloadFile('application/pdf', res.informeArchivo, 'informe' + this.evolucionPaciente.id);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            }
        }
        if (this.sendingInformeFile) {
            this.evolucionPacienteService
                .sendInforme(this.evolucionPaciente.id, this.recipientesCorreo, true)
                .pipe(
                    filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                    map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                )
                .subscribe(
                    (res: IEvolucionPaciente) => {
                        this.sendingInformeFile = false;
                        this.isSaving = false;
                        this.dataUtils.openFile('application/pdf', res.informeArchivo);
                    },
                    (res: HttpErrorResponse) => this.onError('error')
                );
        } else if (this.sendingEvolucionFile) {
            this.evolucionPacienteService
                .sendInforme(this.evolucionPaciente.id, this.recipientesCorreo, false)
                .pipe(
                    filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                    map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                )
                .subscribe(
                    (res: IEvolucionPaciente) => {
                        this.sendingInformeFile = false;
                        this.isSaving = false;
                        this.dataUtils.openFile('application/pdf', res.informeArchivo);
                    },
                    (res: HttpErrorResponse) => this.onError('error')
                );
        } else if (this.gettingRecipeFile) {
            if (!isSafari) {
                this.evolucionPacienteService
                    .getRecipe(this.evolucionPaciente.id, this.recipeToPrint)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingRecipeFile = false;
                            this.isSaving = false;
                            this.dataUtils.openFile('application/pdf', res.recipeArchivo);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            } else {
                this.evolucionPacienteService
                    .getRecipe(this.evolucionPaciente.id, this.recipeToPrint)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingRecipeFile = false;
                            this.isSaving = false;
                            this.dataUtils.downloadFile('application/pdf', res.recipeArchivo, 'recipe');
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            }
        } else if (this.gettingIndicacionesFile) {
            if (!isSafari) {
                this.evolucionPacienteService
                    .getIndicaciones(this.evolucionPaciente.id, this.indicacionToPrint)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingIndicacionesFile = false;
                            this.isSaving = false;
                            this.dataUtils.openFile('application/pdf', res.indicacionesArchivo);
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            } else {
                this.evolucionPacienteService
                    .getIndicaciones(this.evolucionPaciente.id, this.indicacionToPrint)
                    .pipe(
                        filter((mayBeOk: HttpResponse<IEvolucionPaciente>) => mayBeOk.ok),
                        map((response: HttpResponse<IEvolucionPaciente>) => response.body)
                    )
                    .subscribe(
                        (res: IEvolucionPaciente) => {
                            this.gettingIndicacionesFile = false;
                            this.isSaving = false;
                            this.dataUtils.downloadFile('application/pdf', res.indicacionesArchivo, 'indicaciones');
                        },
                        (res: HttpErrorResponse) => this.onError('error')
                    );
            }
        } else {
            this.isSaving = false;
            this.previousState();
        }
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

    updateEstatus(cita, finalizar) {
        if (finalizar) {
            console.log(finalizar);
            for (var i = 0; i < this.estatusCitas.length; i++) {
                if (this.estatusCitas[i].id == 12251) {
                    cita.estatusCita = this.estatusCitas[i];
                    console.log(cita);
                }
            }
        }
        this.save();
    }

    trackId(index: number, item: IEvolucionPaciente) {
        return item.id;
    }
}
