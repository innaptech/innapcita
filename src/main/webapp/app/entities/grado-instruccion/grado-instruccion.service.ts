import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';

type EntityResponseType = HttpResponse<IGradoInstruccion>;
type EntityArrayResponseType = HttpResponse<IGradoInstruccion[]>;

@Injectable({ providedIn: 'root' })
export class GradoInstruccionService {
    public resourceUrl = SERVER_API_URL + 'api/grado-instruccions';

    constructor(protected http: HttpClient) {}

    create(gradoInstruccion: IGradoInstruccion): Observable<EntityResponseType> {
        return this.http.post<IGradoInstruccion>(this.resourceUrl, gradoInstruccion, { observe: 'response' });
    }

    update(gradoInstruccion: IGradoInstruccion): Observable<EntityResponseType> {
        return this.http.put<IGradoInstruccion>(this.resourceUrl, gradoInstruccion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGradoInstruccion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGradoInstruccion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
