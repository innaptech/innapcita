import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';

type EntityResponseType = HttpResponse<IPlantillaHistoriaMedicaAdulto>;
type EntityArrayResponseType = HttpResponse<IPlantillaHistoriaMedicaAdulto[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaMedicaAdultoService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-historia-medica-adultos';

    constructor(protected http: HttpClient) {}

    create(plantillaHistoriaMedicaAdulto: IPlantillaHistoriaMedicaAdulto): Observable<EntityResponseType> {
        return this.http.post<IPlantillaHistoriaMedicaAdulto>(this.resourceUrl, plantillaHistoriaMedicaAdulto, { observe: 'response' });
    }

    update(plantillaHistoriaMedicaAdulto: IPlantillaHistoriaMedicaAdulto): Observable<EntityResponseType> {
        return this.http.put<IPlantillaHistoriaMedicaAdulto>(this.resourceUrl, plantillaHistoriaMedicaAdulto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaHistoriaMedicaAdulto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaHistoriaMedicaAdulto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
