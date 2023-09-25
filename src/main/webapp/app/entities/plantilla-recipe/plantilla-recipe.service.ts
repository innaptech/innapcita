import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';

type EntityResponseType = HttpResponse<IPlantillaRecipe>;
type EntityArrayResponseType = HttpResponse<IPlantillaRecipe[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaRecipeService {
    public resourceUrl = SERVER_API_URL + 'api/plantilla-recipes';

    constructor(protected http: HttpClient) {}

    create(plantillaRecipe: IPlantillaRecipe): Observable<EntityResponseType> {
        return this.http.post<IPlantillaRecipe>(this.resourceUrl, plantillaRecipe, { observe: 'response' });
    }

    update(plantillaRecipe: IPlantillaRecipe): Observable<EntityResponseType> {
        return this.http.put<IPlantillaRecipe>(this.resourceUrl, plantillaRecipe, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantillaRecipe>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantillaRecipe[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
