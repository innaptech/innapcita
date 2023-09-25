import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRangoHoras } from 'app/shared/model/rango-horas.model';

type EntityResponseType = HttpResponse<IRangoHoras>;
type EntityArrayResponseType = HttpResponse<IRangoHoras[]>;

@Injectable({ providedIn: 'root' })
export class RangoHorasService {
    public resourceUrl = SERVER_API_URL + 'api/rango-horas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/rango-horas';

    constructor(protected http: HttpClient) {}

    create(rangoHoras: IRangoHoras): Observable<EntityResponseType> {
        return this.http.post<IRangoHoras>(this.resourceUrl, rangoHoras, { observe: 'response' });
    }

    update(rangoHoras: IRangoHoras): Observable<EntityResponseType> {
        return this.http.put<IRangoHoras>(this.resourceUrl, rangoHoras, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRangoHoras>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRangoHoras[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRangoHoras[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
