import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';

type EntityResponseType = HttpResponse<IPlantillaHistoriaPersonalNino>;
type EntityArrayResponseType = HttpResponse<IPlantillaHistoriaPersonalNino[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaPersonalNinoService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-historia-personal-ninos';

    constructor(protected http: HttpClient) {}

    create(plantillaHistoriaPersonalNino: IPlantillaHistoriaPersonalNino): Observable<EntityResponseType> {
        return this.http.post<IPlantillaHistoriaPersonalNino>(this.resourceUrl, plantillaHistoriaPersonalNino, { observe: 'response' });
    }

    update(plantillaHistoriaPersonalNino: IPlantillaHistoriaPersonalNino): Observable<EntityResponseType> {
        return this.http.put<IPlantillaHistoriaPersonalNino>(this.resourceUrl, plantillaHistoriaPersonalNino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaHistoriaPersonalNino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaHistoriaPersonalNino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
