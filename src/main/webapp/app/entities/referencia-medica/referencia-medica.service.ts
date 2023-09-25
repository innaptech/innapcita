import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';

type EntityResponseType = HttpResponse<IReferenciaMedica>;
type EntityArrayResponseType = HttpResponse<IReferenciaMedica[]>;

@Injectable({ providedIn: 'root' })
export class ReferenciaMedicaService {
    public resourceUrl = SERVER_API_URL + 'api/referencia-medicas';

    constructor(protected http: HttpClient) {}

    create(referenciaMedica: IReferenciaMedica): Observable<EntityResponseType> {
        return this.http.post<IReferenciaMedica>(this.resourceUrl, referenciaMedica, { observe: 'response' });
    }

    update(referenciaMedica: IReferenciaMedica): Observable<EntityResponseType> {
        return this.http.put<IReferenciaMedica>(this.resourceUrl, referenciaMedica, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IReferenciaMedica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IReferenciaMedica[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
