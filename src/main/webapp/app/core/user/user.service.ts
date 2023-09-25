import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUser } from './user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
    public resourceUrl = SERVER_API_URL + 'api/users';

    constructor(private http: HttpClient) {}

    create(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.post<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    update(user: IUser): Observable<HttpResponse<IUser>> {
        return this.http.put<IUser>(this.resourceUrl, user, { observe: 'response' });
    }

    find(login: string): Observable<HttpResponse<IUser>> {
        return this.http.get<IUser>(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<IUser[]>> {
        const options = createRequestOption(req);
        return this.http.get<IUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    getPatients(req?: any): Observable<HttpResponse<IUser[]>> {
        const options = createRequestOption(req);
        return this.http.get<IUser[]>(`${this.resourceUrl}/patients/`, { params: options, observe: 'response' });
    }

    getPatient(login: string): Observable<HttpResponse<IUser>> {
        return this.http.get<IUser>(`${this.resourceUrl}/patient/${login}`, { observe: 'response' });
    }

    queryPatients(query: string): Observable<HttpResponse<IUser[]>> {
        return this.http.get<IUser[]>(`${this.resourceUrl}/patients/search/${query}`, { observe: 'response' });
    }

    querySearch(query: string): Observable<HttpResponse<IUser[]>> {
        return this.http.get<IUser[]>(`${this.resourceUrl}/search/${query}`, { observe: 'response' });
    }

    querySearchMedic(query: string): Observable<HttpResponse<IUser[]>> {
        return this.http.get<IUser[]>(`${this.resourceUrl}/search/medic/${query}`, { observe: 'response' });
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }

    search(req?: any, criteria?: string): Observable<HttpResponse<IUser[]>> {
        const options = createRequestOption(req);
        return this.http.get<IUser[]>(`${this.resourceUrl}/search/${criteria}`, { params: options, observe: 'response' });
    }
}
