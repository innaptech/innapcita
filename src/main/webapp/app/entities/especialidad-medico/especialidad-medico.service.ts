import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';

type EntityResponseType = HttpResponse<IEspecialidadMedico>;
type EntityArrayResponseType = HttpResponse<IEspecialidadMedico[]>;

@Injectable({ providedIn: 'root' })
export class EspecialidadMedicoService {
    public resourceUrl = SERVER_API_URL + 'api/especialidad-medicos';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/especialidad-medicos';

    constructor(protected http: HttpClient) {}

    create(especialidadMedico: IEspecialidadMedico): Observable<EntityResponseType> {
        return this.http.post<IEspecialidadMedico>(this.resourceUrl, especialidadMedico, { observe: 'response' });
    }

    update(especialidadMedico: IEspecialidadMedico): Observable<EntityResponseType> {
        return this.http.put<IEspecialidadMedico>(this.resourceUrl, especialidadMedico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEspecialidadMedico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEspecialidadMedico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    querySearch(query: string): Observable<EntityArrayResponseType> {
        return this.http.get<IEspecialidadMedico[]>(`${this.resourceUrl}/search/${query}`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEspecialidadMedico[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }

    medico(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEspecialidadMedico[]>(`${this.resourceUrl}/medico`, { params: options, observe: 'response' });
    }
}
