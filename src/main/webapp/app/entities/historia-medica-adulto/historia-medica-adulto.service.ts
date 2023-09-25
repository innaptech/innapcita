import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';

type EntityResponseType = HttpResponse<IHistoriaMedicaAdulto>;
type EntityArrayResponseType = HttpResponse<IHistoriaMedicaAdulto[]>;

@Injectable({ providedIn: 'root' })
export class HistoriaMedicaAdultoService {
    public resourceUrl = SERVER_API_URL + 'api/historia-medica-adultos';

    constructor(protected http: HttpClient) {}

    create(historiaMedicaAdulto: IHistoriaMedicaAdulto): Observable<EntityResponseType> {
        return this.http.post<IHistoriaMedicaAdulto>(this.resourceUrl, historiaMedicaAdulto, { observe: 'response' });
    }

    update(historiaMedicaAdulto: IHistoriaMedicaAdulto): Observable<EntityResponseType> {
        return this.http.put<IHistoriaMedicaAdulto>(this.resourceUrl, historiaMedicaAdulto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaMedicaAdulto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHistoriaMedicaAdulto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getFile(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaMedicaAdulto>(`${this.resourceUrl}/file/${id}`, { observe: 'response' });
    }
}
