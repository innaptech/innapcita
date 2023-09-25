import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';
import { PlantillaHistoriaMedicaAdultoService } from './plantilla-historia-medica-adulto.service';
import { PlantillaHistoriaMedicaAdultoComponent } from './plantilla-historia-medica-adulto.component';
import { PlantillaHistoriaMedicaAdultoDetailComponent } from './plantilla-historia-medica-adulto-detail.component';
import { PlantillaHistoriaMedicaAdultoUpdateComponent } from './plantilla-historia-medica-adulto-update.component';
import { PlantillaHistoriaMedicaAdultoDeletePopupComponent } from './plantilla-historia-medica-adulto-delete-dialog.component';
import { IPlantillaHistoriaMedicaAdulto } from 'app/shared/model/plantilla-historia-medica-adulto.model';

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaMedicaAdultoResolve implements Resolve<IPlantillaHistoriaMedicaAdulto> {
    constructor(private service: PlantillaHistoriaMedicaAdultoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaHistoriaMedicaAdulto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaHistoriaMedicaAdulto>) => response.ok),
                map((plantillaHistoriaMedicaAdulto: HttpResponse<PlantillaHistoriaMedicaAdulto>) => plantillaHistoriaMedicaAdulto.body)
            );
        }
        return of(new PlantillaHistoriaMedicaAdulto());
    }
}

export const plantillaHistoriaMedicaAdultoRoute: Routes = [
    {
        path: '',
        component: PlantillaHistoriaMedicaAdultoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaHistoriaMedicaAdultoDetailComponent,
        resolve: {
            plantillaHistoriaMedicaAdulto: PlantillaHistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaHistoriaMedicaAdultoUpdateComponent,
        resolve: {
            plantillaHistoriaMedicaAdulto: PlantillaHistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaHistoriaMedicaAdultoUpdateComponent,
        resolve: {
            plantillaHistoriaMedicaAdulto: PlantillaHistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaHistoriaMedicaAdultoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaHistoriaMedicaAdultoDeletePopupComponent,
        resolve: {
            plantillaHistoriaMedicaAdulto: PlantillaHistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
