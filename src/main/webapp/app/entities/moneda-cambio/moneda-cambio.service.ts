import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMonedaCambio } from 'app/shared/model/moneda-cambio.model';

type EntityResponseType = HttpResponse<IMonedaCambio>;
type EntityArrayResponseType = HttpResponse<IMonedaCambio[]>;

@Injectable({ providedIn: 'root' })
export class MonedaCambioService {
    public resourceUrl = SERVER_API_URL + 'api/moneda-cambios';

    constructor(protected http: HttpClient) {}

    create(monedaCambio: IMonedaCambio): Observable<EntityResponseType> {
        return this.http.post<IMonedaCambio>(this.resourceUrl, monedaCambio, { observe: 'response' });
    }

    update(monedaCambio: IMonedaCambio): Observable<EntityResponseType> {
        return this.http.put<IMonedaCambio>(this.resourceUrl, monedaCambio, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMonedaCambio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMonedaCambio[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
