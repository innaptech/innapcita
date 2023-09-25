import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';

type EntityResponseType = HttpResponse<IPlantillaHistoriaMedicaNino>;
type EntityArrayResponseType = HttpResponse<IPlantillaHistoriaMedicaNino[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaMedicaNinoService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-historia-medica-ninos';

    constructor(protected http: HttpClient) {}

    create(plantillaHistoriaMedicaNino: IPlantillaHistoriaMedicaNino): Observable<EntityResponseType> {
        return this.http.post<IPlantillaHistoriaMedicaNino>(this.resourceUrl, plantillaHistoriaMedicaNino, { observe: 'response' });
    }

    update(plantillaHistoriaMedicaNino: IPlantillaHistoriaMedicaNino): Observable<EntityResponseType> {
        return this.http.put<IPlantillaHistoriaMedicaNino>(this.resourceUrl, plantillaHistoriaMedicaNino, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaHistoriaMedicaNino>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaHistoriaMedicaNino[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
