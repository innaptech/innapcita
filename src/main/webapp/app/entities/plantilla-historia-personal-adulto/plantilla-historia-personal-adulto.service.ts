import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';

type EntityResponseType = HttpResponse<IPlantillaHistoriaPersonalAdulto>;
type EntityArrayResponseType = HttpResponse<IPlantillaHistoriaPersonalAdulto[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaPersonalAdultoService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-historia-personal-adultos';

    constructor(protected http: HttpClient) {}

    create(plantillaHistoriaPersonalAdulto: IPlantillaHistoriaPersonalAdulto): Observable<EntityResponseType> {
        return this.http.post<IPlantillaHistoriaPersonalAdulto>(this.resourceUrl, plantillaHistoriaPersonalAdulto, { observe: 'response' });
    }

    update(plantillaHistoriaPersonalAdulto: IPlantillaHistoriaPersonalAdulto): Observable<EntityResponseType> {
        return this.http.put<IPlantillaHistoriaPersonalAdulto>(this.resourceUrl, plantillaHistoriaPersonalAdulto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaHistoriaPersonalAdulto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaHistoriaPersonalAdulto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
