import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaInforme } from 'app/shared/model/plantilla-informe.model';

type EntityResponseType = HttpResponse<IPlantillaInforme>;
type EntityArrayResponseType = HttpResponse<IPlantillaInforme[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaInformeService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-informes';

    constructor(protected http: HttpClient) {}

    create(plantillaInforme: IPlantillaInforme): Observable<EntityResponseType> {
        return this.http.post<IPlantillaInforme>(this.resourceUrl, plantillaInforme, { observe: 'response' });
    }

    update(plantillaInforme: IPlantillaInforme): Observable<EntityResponseType> {
        return this.http.put<IPlantillaInforme>(this.resourceUrl, plantillaInforme, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaInforme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaInforme[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
