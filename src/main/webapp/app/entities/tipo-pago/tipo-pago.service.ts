import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoPago } from 'app/shared/model/tipo-pago.model';

type EntityResponseType = HttpResponse<ITipoPago>;
type EntityArrayResponseType = HttpResponse<ITipoPago[]>;

@Injectable({ providedIn: 'root' })
export class TipoPagoService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-pagos';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/tipo-pagos';

    constructor(protected http: HttpClient) {}

    create(tipoPago: ITipoPago): Observable<EntityResponseType> {
        return this.http.post<ITipoPago>(this.resourceUrl, tipoPago, { observe: 'response' });
    }

    update(tipoPago: ITipoPago): Observable<EntityResponseType> {
        return this.http.put<ITipoPago>(this.resourceUrl, tipoPago, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoPago>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoPago[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoPago[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
