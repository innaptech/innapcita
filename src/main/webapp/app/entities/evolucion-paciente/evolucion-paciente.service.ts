import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';

type EntityResponseType = HttpResponse<IEvolucionPaciente>;
type EntityArrayResponseType = HttpResponse<IEvolucionPaciente[]>;

@Injectable({ providedIn: 'root' })
export class EvolucionPacienteService {
    public resourceUrl = SERVER_API_URL + 'api/evolucion-pacientes';

    constructor(protected http: HttpClient) {}

    create(evolucionPaciente: IEvolucionPaciente): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evolucionPaciente);
        return this.http
            .post<IEvolucionPaciente>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(evolucionPaciente: IEvolucionPaciente): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(evolucionPaciente);
        return this.http
            .put<IEvolucionPaciente>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEvolucionPaciente>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEvolucionPaciente[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(evolucionPaciente: IEvolucionPaciente): IEvolucionPaciente {
        const copy: IEvolucionPaciente = Object.assign({}, evolucionPaciente, {
            fecha: evolucionPaciente.fecha != null && evolucionPaciente.fecha.isValid() ? evolucionPaciente.fecha.toJSON() : null,
            proximaConsulta:
                evolucionPaciente.proximaConsulta != null && evolucionPaciente.proximaConsulta.isValid()
                    ? evolucionPaciente.proximaConsulta.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
            res.body.proximaConsulta = res.body.proximaConsulta != null ? moment(res.body.proximaConsulta) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((evolucionPaciente: IEvolucionPaciente) => {
                evolucionPaciente.fecha = evolucionPaciente.fecha != null ? moment(evolucionPaciente.fecha) : null;
                evolucionPaciente.proximaConsulta =
                    evolucionPaciente.proximaConsulta != null ? moment(evolucionPaciente.proximaConsulta) : null;
            });
        }
        return res;
    }

    getInforme(id: number, individual: boolean): Observable<EntityResponseType> {
        return this.http.get<IEvolucionPaciente>(`${this.resourceUrl}/informe/${id}/${individual}`, { observe: 'response' });
    }

    sendInforme(id: number, recipientes: string, individual: boolean): Observable<EntityResponseType> {
        return this.http.get<IEvolucionPaciente>(`${this.resourceUrl}/informe/enviar/${id}/${recipientes}/${individual}`, {
            observe: 'response'
        });
    }

    getRecipe(id: number, item: number): Observable<EntityResponseType> {
        return this.http.get<IEvolucionPaciente>(`${this.resourceUrl}/recipe/${id}/${item}`, { observe: 'response' });
    }

    getIndicaciones(id: number, item: number): Observable<EntityResponseType> {
        return this.http.get<IEvolucionPaciente>(`${this.resourceUrl}/indicaciones/${id}/${item}`, { observe: 'response' });
    }
}
