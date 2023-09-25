import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';
import { PlantillaHistoriaPersonalNinoService } from './plantilla-historia-personal-nino.service';
import { PlantillaHistoriaPersonalNinoComponent } from './plantilla-historia-personal-nino.component';
import { PlantillaHistoriaPersonalNinoDetailComponent } from './plantilla-historia-personal-nino-detail.component';
import { PlantillaHistoriaPersonalNinoUpdateComponent } from './plantilla-historia-personal-nino-update.component';
import { PlantillaHistoriaPersonalNinoDeletePopupComponent } from './plantilla-historia-personal-nino-delete-dialog.component';
import { IPlantillaHistoriaPersonalNino } from 'app/shared/model/plantilla-historia-personal-nino.model';

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaPersonalNinoResolve implements Resolve<IPlantillaHistoriaPersonalNino> {
    constructor(private service: PlantillaHistoriaPersonalNinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaHistoriaPersonalNino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaHistoriaPersonalNino>) => response.ok),
                map((plantillaHistoriaPersonalNino: HttpResponse<PlantillaHistoriaPersonalNino>) => plantillaHistoriaPersonalNino.body)
            );
        }
        return of(new PlantillaHistoriaPersonalNino());
    }
}

export const plantillaHistoriaPersonalNinoRoute: Routes = [
    {
        path: '',
        component: PlantillaHistoriaPersonalNinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaHistoriaPersonalNinoDetailComponent,
        resolve: {
            plantillaHistoriaPersonalNino: PlantillaHistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaHistoriaPersonalNinoUpdateComponent,
        resolve: {
            plantillaHistoriaPersonalNino: PlantillaHistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaHistoriaPersonalNinoUpdateComponent,
        resolve: {
            plantillaHistoriaPersonalNino: PlantillaHistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaHistoriaPersonalNinoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaHistoriaPersonalNinoDeletePopupComponent,
        resolve: {
            plantillaHistoriaPersonalNino: PlantillaHistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
