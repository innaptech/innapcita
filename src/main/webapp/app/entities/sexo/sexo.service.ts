import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISexo } from 'app/shared/model/sexo.model';

type EntityResponseType = HttpResponse<ISexo>;
type EntityArrayResponseType = HttpResponse<ISexo[]>;

@Injectable({ providedIn: 'root' })
export class SexoService {
    public resourceUrl = SERVER_API_URL + 'api/sexos';

    constructor(protected http: HttpClient) {}

    create(sexo: ISexo): Observable<EntityResponseType> {
        return this.http.post<ISexo>(this.resourceUrl, sexo, { observe: 'response' });
    }

    update(sexo: ISexo): Observable<EntityResponseType> {
        return this.http.put<ISexo>(this.resourceUrl, sexo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISexo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISexo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
