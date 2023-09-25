import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';

type EntityResponseType = HttpResponse<IHorarioDisponibilidad>;
type EntityArrayResponseType = HttpResponse<IHorarioDisponibilidad[]>;

@Injectable({ providedIn: 'root' })
export class HorarioDisponibilidadService {
    public resourceUrl = SERVER_API_URL + 'api/horario-disponibilidads';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/horario-disponibilidads';

    constructor(protected http: HttpClient) {}

    create(horarioDisponibilidad: IHorarioDisponibilidad): Observable<EntityResponseType> {
        return this.http.post<IHorarioDisponibilidad>(this.resourceUrl, horarioDisponibilidad, { observe: 'response' });
    }

    update(horarioDisponibilidad: IHorarioDisponibilidad): Observable<EntityResponseType> {
        return this.http.put<IHorarioDisponibilidad>(this.resourceUrl, horarioDisponibilidad, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHorarioDisponibilidad>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHorarioDisponibilidad[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHorarioDisponibilidad[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }

    medico(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHorarioDisponibilidad[]>(`${this.resourceUrl}/medico`, { params: options, observe: 'response' });
    }
}
