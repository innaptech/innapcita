import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';

type EntityResponseType = HttpResponse<IHistoriaPersonalAdulto>;
type EntityArrayResponseType = HttpResponse<IHistoriaPersonalAdulto[]>;

@Injectable({ providedIn: 'root' })
export class HistoriaPersonalAdultoService {
    public resourceUrl = SERVER_API_URL + 'api/historia-personal-adultos';

    constructor(protected http: HttpClient) {}

    create(historiaPersonalAdulto: IHistoriaPersonalAdulto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiaPersonalAdulto);
        return this.http
            .post<IHistoriaPersonalAdulto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(historiaPersonalAdulto: IHistoriaPersonalAdulto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(historiaPersonalAdulto);
        return this.http
            .put<IHistoriaPersonalAdulto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHistoriaPersonalAdulto>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHistoriaPersonalAdulto[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(historiaPersonalAdulto: IHistoriaPersonalAdulto): IHistoriaPersonalAdulto {
        const copy: IHistoriaPersonalAdulto = Object.assign({}, historiaPersonalAdulto, {
            fechaNacimiento:
                historiaPersonalAdulto.fechaNacimiento != null && historiaPersonalAdulto.fechaNacimiento.isValid()
                    ? historiaPersonalAdulto.fechaNacimiento.format(DATE_FORMAT)
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
            res.body.forEach((historiaPersonalAdulto: IHistoriaPersonalAdulto) => {
                historiaPersonalAdulto.fechaNacimiento =
                    historiaPersonalAdulto.fechaNacimiento != null ? moment(historiaPersonalAdulto.fechaNacimiento) : null;
            });
        }
        return res;
    }

    getFile(id: number): Observable<EntityResponseType> {
        return this.http.get<IHistoriaPersonalAdulto>(`${this.resourceUrl}/file/${id}`, { observe: 'response' });
    }
}
