import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';

type EntityResponseType = HttpResponse<IPlantillaAntecedentes>;
type EntityArrayResponseType = HttpResponse<IPlantillaAntecedentes[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaAntecedentesService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-antecedentes';

    constructor(protected http: HttpClient) {}

    create(plantillaAntecedentes: IPlantillaAntecedentes): Observable<EntityResponseType> {
        return this.http.post<IPlantillaAntecedentes>(this.resourceUrl, plantillaAntecedentes, { observe: 'response' });
    }

    update(plantillaAntecedentes: IPlantillaAntecedentes): Observable<EntityResponseType> {
        return this.http.put<IPlantillaAntecedentes>(this.resourceUrl, plantillaAntecedentes, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaAntecedentes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaAntecedentes[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
