import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EstatusCita } from 'app/shared/model/estatus-cita.model';
import { EstatusCitaService } from './estatus-cita.service';
import { EstatusCitaComponent } from './estatus-cita.component';
import { EstatusCitaDetailComponent } from './estatus-cita-detail.component';
import { EstatusCitaUpdateComponent } from './estatus-cita-update.component';
import { EstatusCitaDeletePopupComponent } from './estatus-cita-delete-dialog.component';
import { IEstatusCita } from 'app/shared/model/estatus-cita.model';

@Injectable({ providedIn: 'root' })
export class EstatusCitaResolve implements Resolve<IEstatusCita> {
    constructor(private service: EstatusCitaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEstatusCita> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EstatusCita>) => response.ok),
                map((estatusCita: HttpResponse<EstatusCita>) => estatusCita.body)
            );
        }
        return of(new EstatusCita());
    }
}

export const estatusCitaRoute: Routes = [
    {
        path: '',
        component: EstatusCitaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.estatusCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EstatusCitaDetailComponent,
        resolve: {
            estatusCita: EstatusCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EstatusCitaUpdateComponent,
        resolve: {
            estatusCita: EstatusCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EstatusCitaUpdateComponent,
        resolve: {
            estatusCita: EstatusCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusCita.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const estatusCitaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EstatusCitaDeletePopupComponent,
        resolve: {
            estatusCita: EstatusCitaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusCita.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
