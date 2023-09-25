import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';

type EntityResponseType = HttpResponse<ITipoConsulta>;
type EntityArrayResponseType = HttpResponse<ITipoConsulta[]>;

@Injectable({ providedIn: 'root' })
export class TipoConsultaService {
    public resourceUrl = SERVER_API_URL + 'api/tipo-consultas';

    constructor(protected http: HttpClient) {}

    create(tipoConsulta: ITipoConsulta): Observable<EntityResponseType> {
        return this.http.post<ITipoConsulta>(this.resourceUrl, tipoConsulta, { observe: 'response' });
    }

    update(tipoConsulta: ITipoConsulta): Observable<EntityResponseType> {
        return this.http.put<ITipoConsulta>(this.resourceUrl, tipoConsulta, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITipoConsulta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITipoConsulta[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
