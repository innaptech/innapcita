import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';
import { PlantillaRecipeService } from './plantilla-recipe.service';
import { PlantillaRecipeComponent } from './plantilla-recipe.component';
import { PlantillaRecipeDetailComponent } from './plantilla-recipe-detail.component';
import { PlantillaRecipeUpdateComponent } from './plantilla-recipe-update.component';
import { PlantillaRecipeDeletePopupComponent } from './plantilla-recipe-delete-dialog.component';
import { IPlantillaRecipe } from 'app/shared/model/plantilla-recipe.model';

@Injectable({ providedIn: 'root' })
export class PlantillaRecipeResolve implements Resolve<IPlantillaRecipe> {
    constructor(private service: PlantillaRecipeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaRecipe> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaRecipe>) => response.ok),
                map((plantillaRecipe: HttpResponse<PlantillaRecipe>) => plantillaRecipe.body)
            );
        }
        return of(new PlantillaRecipe());
    }
}

export const plantillaRecipeRoute: Routes = [
    {
        path: '',
        component: PlantillaRecipeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaRecipe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaRecipeDetailComponent,
        resolve: {
            plantillaRecipe: PlantillaRecipeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaRecipe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaRecipeUpdateComponent,
        resolve: {
            plantillaRecipe: PlantillaRecipeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaRecipe.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaRecipeUpdateComponent,
        resolve: {
            plantillaRecipe: PlantillaRecipeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaRecipe.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaRecipePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaRecipeDeletePopupComponent,
        resolve: {
            plantillaRecipe: PlantillaRecipeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaRecipe.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
