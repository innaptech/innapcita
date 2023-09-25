import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICabeceraInforme } from 'app/shared/model/cabecera-informe.model';

type EntityResponseType = HttpResponse<ICabeceraInforme>;
type EntityArrayResponseType = HttpResponse<ICabeceraInforme[]>;

@Injectable({ providedIn: 'root' })
export class CabeceraInformeService {
    public resourceUrl = SERVER_API_URL + 'api/cabecera-informes';

    constructor(protected http: HttpClient) {}

    create(cabeceraInforme: ICabeceraInforme): Observable<EntityResponseType> {
        return this.http.post<ICabeceraInforme>(this.resourceUrl, cabeceraInforme, { observe: 'response' });
    }

    update(cabeceraInforme: ICabeceraInforme): Observable<EntityResponseType> {
        return this.http.put<ICabeceraInforme>(this.resourceUrl, cabeceraInforme, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICabeceraInforme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICabeceraInforme[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
