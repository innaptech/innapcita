import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';

type EntityResponseType = HttpResponse<IExamenComplementario>;
type EntityArrayResponseType = HttpResponse<IExamenComplementario[]>;

@Injectable({ providedIn: 'root' })
export class ExamenComplementarioService {
    public resourceUrl = SERVER_API_URL + 'api/examen-complementarios';

    constructor(protected http: HttpClient) {}

    create(examenComplementario: IExamenComplementario): Observable<EntityResponseType> {
        return this.http.post<IExamenComplementario>(this.resourceUrl, examenComplementario, { observe: 'response' });
    }

    update(examenComplementario: IExamenComplementario): Observable<EntityResponseType> {
        return this.http.put<IExamenComplementario>(this.resourceUrl, examenComplementario, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IExamenComplementario>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IExamenComplementario[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
