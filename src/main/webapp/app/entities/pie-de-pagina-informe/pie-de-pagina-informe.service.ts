import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';

type EntityResponseType = HttpResponse<IPieDePaginaInforme>;
type EntityArrayResponseType = HttpResponse<IPieDePaginaInforme[]>;

@Injectable({ providedIn: 'root' })
export class PieDePaginaInformeService {
    public resourceUrl = SERVER_API_URL + 'api/pie-de-pagina-informes';

    constructor(protected http: HttpClient) {}

    create(pieDePaginaInforme: IPieDePaginaInforme): Observable<EntityResponseType> {
        return this.http.post<IPieDePaginaInforme>(this.resourceUrl, pieDePaginaInforme, { observe: 'response' });
    }

    update(pieDePaginaInforme: IPieDePaginaInforme): Observable<EntityResponseType> {
        return this.http.put<IPieDePaginaInforme>(this.resourceUrl, pieDePaginaInforme, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPieDePaginaInforme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPieDePaginaInforme[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
