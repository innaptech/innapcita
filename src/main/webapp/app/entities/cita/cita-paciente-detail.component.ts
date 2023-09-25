import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';
import { filter, map } from 'rxjs/operators';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IExamenComplementario, ExamenComplementario } from 'app/shared/model/examen-complementario.model';
import { ExamenComplementarioService } from '../examen-complementario/examen-complementario.service';

import { User } from 'app/core';

@Component({
    selector: 'jhi-cita-pacientes',
    templateUrl: './cita-paciente-detail.component.html'
})
export class CitaPacienteDetailComponent implements OnInit {
    user: User;
    zonaHoraria: string;
    currentEvo: any;

    recipes: any[];
    indicaciones: any[];

    evolucionPaciente: any;

    showDots: boolean;

    constructor(protected activatedRoute: ActivatedRoute, protected examenComplementarioService: ExamenComplementarioService, protected dataUtils: JhiDataUtils) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ user }) => {
            console.log(user);
            this.user = user.body;
        });
        this.zonaHoraria = Intl.DateTimeFormat().resolvedOptions().timeZone;
        this.showDots = false;
    }

    previousState() {
        window.history.back();
    }

    trackId(index: number, item: IEvolucionPaciente) {
        return item.id;
    }

    showEvo(id) {
        return this.currentEvo == id;
    }

    setEvo(evo) {
        if(this.currentEvo == evo.id) {
            this.currentEvo = 0;
        } else {
            this.currentEvo = evo.id;
            this.evolucionPaciente = evo;
            this.updateRecipes();
        }

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
                if(medicina.nombre) {
                    if (!medicina.esPsicotropico) {
                        medicinasNormales += '<p>' + medicina.nombre + '</p>';
                    } else {
                       this.recipes.push( '<p>' + medicina.nombre + '</p>' );
                    }
                    indicacionesMedicinas += '<p>' + medicina.nombre + ': ' + medicina.indicacion + '</p>';
                }
            }

            if(medicinasNormales)
                this.recipes.push(medicinasNormales);
            if(indicacionesMedicinas)
                this.indicaciones.push(indicacionesMedicinas);
        }

        //Examenes indicados
        if (this.evolucionPaciente.examenesIndicadosList) {
            for (var i = 0; i < this.evolucionPaciente.examenesIndicadosList.length; i++) {
                const examen = this.evolucionPaciente.examenesIndicadosList[i];
                if(examen.examen){
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

    listen(one, two) {

    }

    calcularImc() {
        if (this.evolucionPaciente.talla && this.evolucionPaciente.peso) {
            const imc =  parseFloat(this.evolucionPaciente.peso) / (parseFloat(this.evolucionPaciente.talla) * parseFloat(this.evolucionPaciente.talla));
            const result = Math.round(imc*100)/100;
            this.evolucionPaciente.imc = result.toString();
        }
    }

    openFile(contentType, field) {
        if (field.id) {
            this.examenComplementarioService
                .find(field.id)
                .pipe(
                    filter((mayBeOk: HttpResponse<IExamenComplementario>) => mayBeOk.ok),
                    map((response: HttpResponse<IExamenComplementario>) => response.body)
                )
                .subscribe((res: IExamenComplementario) => (this.dataUtils.openFile(contentType, res.archivo)), (res: HttpErrorResponse) => this.onError(res.message));
            return null;
        } else {
            return this.dataUtils.openFile(contentType, field.archivo);
        }
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    protected onError(errorMessage: string) {
        alert(errorMessage);
    }
}
