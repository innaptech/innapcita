import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ReferenciaMedica } from 'app/shared/model/referencia-medica.model';
import { ReferenciaMedicaService } from './referencia-medica.service';
import { ReferenciaMedicaComponent } from './referencia-medica.component';
import { ReferenciaMedicaDetailComponent } from './referencia-medica-detail.component';
import { ReferenciaMedicaUpdateComponent } from './referencia-medica-update.component';
import { ReferenciaMedicaDeletePopupComponent } from './referencia-medica-delete-dialog.component';
import { IReferenciaMedica } from 'app/shared/model/referencia-medica.model';

@Injectable({ providedIn: 'root' })
export class ReferenciaMedicaResolve implements Resolve<IReferenciaMedica> {
    constructor(private service: ReferenciaMedicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IReferenciaMedica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ReferenciaMedica>) => response.ok),
                map((referenciaMedica: HttpResponse<ReferenciaMedica>) => referenciaMedica.body)
            );
        }
        return of(new ReferenciaMedica());
    }
}

export const referenciaMedicaRoute: Routes = [
    {
        path: '',
        component: ReferenciaMedicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.referenciaMedica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ReferenciaMedicaDetailComponent,
        resolve: {
            referenciaMedica: ReferenciaMedicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.referenciaMedica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ReferenciaMedicaUpdateComponent,
        resolve: {
            referenciaMedica: ReferenciaMedicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.referenciaMedica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ReferenciaMedicaUpdateComponent,
        resolve: {
            referenciaMedica: ReferenciaMedicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.referenciaMedica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const referenciaMedicaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ReferenciaMedicaDeletePopupComponent,
        resolve: {
            referenciaMedica: ReferenciaMedicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.referenciaMedica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
