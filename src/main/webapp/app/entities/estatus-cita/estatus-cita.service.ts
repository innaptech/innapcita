import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEstatusCita } from 'app/shared/model/estatus-cita.model';

type EntityResponseType = HttpResponse<IEstatusCita>;
type EntityArrayResponseType = HttpResponse<IEstatusCita[]>;

@Injectable({ providedIn: 'root' })
export class EstatusCitaService {
    public resourceUrl = SERVER_API_URL + 'api/estatus-citas';

    constructor(protected http: HttpClient) {}

    create(estatusCita: IEstatusCita): Observable<EntityResponseType> {
        return this.http.post<IEstatusCita>(this.resourceUrl, estatusCita, { observe: 'response' });
    }

    update(estatusCita: IEstatusCita): Observable<EntityResponseType> {
        return this.http.put<IEstatusCita>(this.resourceUrl, estatusCita, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEstatusCita>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEstatusCita[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
