import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmailCita } from 'app/shared/model/email-cita.model';

type EntityResponseType = HttpResponse<IEmailCita>;
type EntityArrayResponseType = HttpResponse<IEmailCita[]>;

@Injectable({ providedIn: 'root' })
export class EmailCitaService {
    public resourceUrl = SERVER_API_URL + 'api/email-citas';

    constructor(protected http: HttpClient) {}

    create(emailCita: IEmailCita): Observable<EntityResponseType> {
        return this.http.post<IEmailCita>(this.resourceUrl, emailCita, { observe: 'response' });
    }

    update(emailCita: IEmailCita): Observable<EntityResponseType> {
        return this.http.put<IEmailCita>(this.resourceUrl, emailCita, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmailCita>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmailCita[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
