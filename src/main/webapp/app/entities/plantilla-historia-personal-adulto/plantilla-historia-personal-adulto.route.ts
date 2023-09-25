import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';
import { PlantillaHistoriaPersonalAdultoService } from './plantilla-historia-personal-adulto.service';
import { PlantillaHistoriaPersonalAdultoComponent } from './plantilla-historia-personal-adulto.component';
import { PlantillaHistoriaPersonalAdultoDetailComponent } from './plantilla-historia-personal-adulto-detail.component';
import { PlantillaHistoriaPersonalAdultoUpdateComponent } from './plantilla-historia-personal-adulto-update.component';
import { PlantillaHistoriaPersonalAdultoDeletePopupComponent } from './plantilla-historia-personal-adulto-delete-dialog.component';
import { IPlantillaHistoriaPersonalAdulto } from 'app/shared/model/plantilla-historia-personal-adulto.model';

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaPersonalAdultoResolve implements Resolve<IPlantillaHistoriaPersonalAdulto> {
    constructor(private service: PlantillaHistoriaPersonalAdultoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaHistoriaPersonalAdulto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaHistoriaPersonalAdulto>) => response.ok),
                map(
                    (plantillaHistoriaPersonalAdulto: HttpResponse<PlantillaHistoriaPersonalAdulto>) => plantillaHistoriaPersonalAdulto.body
                )
            );
        }
        return of(new PlantillaHistoriaPersonalAdulto());
    }
}

export const plantillaHistoriaPersonalAdultoRoute: Routes = [
    {
        path: '',
        component: PlantillaHistoriaPersonalAdultoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaHistoriaPersonalAdultoDetailComponent,
        resolve: {
            plantillaHistoriaPersonalAdulto: PlantillaHistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaHistoriaPersonalAdultoUpdateComponent,
        resolve: {
            plantillaHistoriaPersonalAdulto: PlantillaHistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaHistoriaPersonalAdultoUpdateComponent,
        resolve: {
            plantillaHistoriaPersonalAdulto: PlantillaHistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaHistoriaPersonalAdultoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaHistoriaPersonalAdultoDeletePopupComponent,
        resolve: {
            plantillaHistoriaPersonalAdulto: PlantillaHistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
