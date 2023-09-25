import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';

type EntityResponseType = HttpResponse<IHistoriaMedicaNino>;
type EntityArrayResponseType = HttpResponse<IHistoriaMedicaNino[]>;

@Injectable({ providedIn: 'root' })
export class HistoriaMedicaNinoService {
    public resourceUrl = SERVER_API_URL + 'api/historia-medica-ninos';

    constructor(protected http: HttpClient) {}

    create(historiaMedicaNino: IHistoriaMedicaNino): Observable<EntityResponseType> {
        return this.http.post<IHistoriaMedicaNino>(this.resourceUrl, historiaMedicaNino, { observe: 'response' });
    }

    update(historiaMedicaNino: IHistoriaMedicaNino): Observable<EntityResponseType> {
        return this.http.put<IHistoriaMedicaNino>(this.resourceUrl, historiaMedicaNino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaMedicaNino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IHistoriaMedicaNino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getFile(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaMedicaNino>(`${this.resourceUrl}/file/${id}`, { observe: 'response' });
    }
}
