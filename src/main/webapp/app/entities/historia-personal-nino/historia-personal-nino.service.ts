import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';

type EntityResponseType = HttpResponse<IHistoriaPersonalNino>;
type EntityArrayResponseType = HttpResponse<IHistoriaPersonalNino[]>;

@Injectable({ providedIn: 'root' })
export class HistoriaPersonalNinoService {
    public resourceUrl = SERVER_API_URL + 'api/historia-personal-ninos';

    constructor(protected http: HttpClient) {}

    create(historiaPersonalNino: IHistoriaPersonalNino): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiaPersonalNino);
        return this.http
            .post<IHistoriaPersonalNino>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(historiaPersonalNino: IHistoriaPersonalNino): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiaPersonalNino);
        return this.http
            .put<IHistoriaPersonalNino>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHistoriaPersonalNino>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHistoriaPersonalNino[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(historiaPersonalNino: IHistoriaPersonalNino): IHistoriaPersonalNino {
        const copy: IHistoriaPersonalNino = Object.assign({}, historiaPersonalNino, {
            fechaNacimiento:
                historiaPersonalNino.fechaNacimiento != null && historiaPersonalNino.fechaNacimiento.isValid()
                    ? historiaPersonalNino.fechaNacimiento.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fechaNacimiento = res.body.fechaNacimiento != null ? moment(res.body.fechaNacimiento) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((historiaPersonalNino: IHistoriaPersonalNino) => {
                historiaPersonalNino.fechaNacimiento =
                    historiaPersonalNino.fechaNacimiento != null ? moment(historiaPersonalNino.fechaNacimiento) : null;
            });
        }
        return res;
    }

    getFile(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaPersonalNino>(`${this.resourceUrl}/file/${id}`, { observe: 'response' });
    }
}
