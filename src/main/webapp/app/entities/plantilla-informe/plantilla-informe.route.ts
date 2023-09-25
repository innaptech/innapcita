import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaInforme } from 'app/shared/model/plantilla-informe.model';
import { PlantillaInformeService } from './plantilla-informe.service';
import { PlantillaInformeComponent } from './plantilla-informe.component';
import { PlantillaInformeDetailComponent } from './plantilla-informe-detail.component';
import { PlantillaInformeUpdateComponent } from './plantilla-informe-update.component';
import { PlantillaInformeDeletePopupComponent } from './plantilla-informe-delete-dialog.component';
import { IPlantillaInforme } from 'app/shared/model/plantilla-informe.model';

@Injectable({ providedIn: 'root' })
export class PlantillaInformeResolve implements Resolve<IPlantillaInforme> {
    constructor(private service: PlantillaInformeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaInforme> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaInforme>) => response.ok),
                map((plantillaInforme: HttpResponse<PlantillaInforme>) => plantillaInforme.body)
            );
        }
        return of(new PlantillaInforme());
    }
}

export const plantillaInformeRoute: Routes = [
    {
        path: '',
        component: PlantillaInformeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaInformeDetailComponent,
        resolve: {
            plantillaInforme: PlantillaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaInformeUpdateComponent,
        resolve: {
            plantillaInforme: PlantillaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaInformeUpdateComponent,
        resolve: {
            plantillaInforme: PlantillaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaInformePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaInformeDeletePopupComponent,
        resolve: {
            plantillaInforme: PlantillaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaInforme.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
