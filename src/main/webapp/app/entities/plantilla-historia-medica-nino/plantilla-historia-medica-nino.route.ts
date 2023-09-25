import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';
import { PlantillaHistoriaMedicaNinoService } from './plantilla-historia-medica-nino.service';
import { PlantillaHistoriaMedicaNinoComponent } from './plantilla-historia-medica-nino.component';
import { PlantillaHistoriaMedicaNinoDetailComponent } from './plantilla-historia-medica-nino-detail.component';
import { PlantillaHistoriaMedicaNinoUpdateComponent } from './plantilla-historia-medica-nino-update.component';
import { PlantillaHistoriaMedicaNinoDeletePopupComponent } from './plantilla-historia-medica-nino-delete-dialog.component';
import { IPlantillaHistoriaMedicaNino } from 'app/shared/model/plantilla-historia-medica-nino.model';

@Injectable({ providedIn: 'root' })
export class PlantillaHistoriaMedicaNinoResolve implements Resolve<IPlantillaHistoriaMedicaNino> {
    constructor(private service: PlantillaHistoriaMedicaNinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPlantillaHistoriaMedicaNino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlantillaHistoriaMedicaNino>) => response.ok),
                map((plantillaHistoriaMedicaNino: HttpResponse<PlantillaHistoriaMedicaNino>) => plantillaHistoriaMedicaNino.body)
            );
        }
        return of(new PlantillaHistoriaMedicaNino());
    }
}

export const plantillaHistoriaMedicaNinoRoute: Routes = [
    {
        path: '',
        component: PlantillaHistoriaMedicaNinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PlantillaHistoriaMedicaNinoDetailComponent,
        resolve: {
            plantillaHistoriaMedicaNino: PlantillaHistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PlantillaHistoriaMedicaNinoUpdateComponent,
        resolve: {
            plantillaHistoriaMedicaNino: PlantillaHistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PlantillaHistoriaMedicaNinoUpdateComponent,
        resolve: {
            plantillaHistoriaMedicaNino: PlantillaHistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaHistoriaMedicaNinoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PlantillaHistoriaMedicaNinoDeletePopupComponent,
        resolve: {
            plantillaHistoriaMedicaNino: PlantillaHistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.plantillaHistoriaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
