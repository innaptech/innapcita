import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';
import { PlantillaAntecedentesService } from './plantilla-antecedentes.service';
import { PlantillaAntecedentesComponent } from './plantilla-antecedentes.component';
import { PlantillaAntecedentesDetailComponent } from './plantilla-antecedentes-detail.component';
import { PlantillaAntecedentesUpdateComponent } from './plantilla-antecedentes-update.component';
import { PlantillaAntecedentesDeletePopupComponent } from './plantilla-antecedentes-delete-dialog.component';
import { IPlantillaAntecedentes } from 'app/shared/model/plantilla-antecedentes.model';

@Injectable({ providedIn: 'root' })
export class PlantillaAntecedentesResolve implements Resolve<IPlantillaAntecedentes> {
    constructor(private service: PlantillaAntecedentesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaAntecedentes> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaAntecedentes>) => response.ok),
                map((plantillaAntecedentes: HttpResponse<PlantillaAntecedentes>) => plantillaAntecedentes.body)
            );
        }
        return of(new PlantillaAntecedentes());
    }
}

export const plantillaAntecedentesRoute: Routes = [
    {
        path: '',
        component: PlantillaAntecedentesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaAntecedentes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaAntecedentesDetailComponent,
        resolve: {
            plantillaAntecedentes: PlantillaAntecedentesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaAntecedentes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaAntecedentesUpdateComponent,
        resolve: {
            plantillaAntecedentes: PlantillaAntecedentesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaAntecedentes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaAntecedentesUpdateComponent,
        resolve: {
            plantillaAntecedentes: PlantillaAntecedentesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaAntecedentes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaAntecedentesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaAntecedentesDeletePopupComponent,
        resolve: {
            plantillaAntecedentes: PlantillaAntecedentesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaAntecedentes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
