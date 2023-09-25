import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonedaCambio } from 'app/shared/model/moneda-cambio.model';
import { MonedaCambioService } from './moneda-cambio.service';
import { MonedaCambioComponent } from './moneda-cambio.component';
import { MonedaCambioDetailComponent } from './moneda-cambio-detail.component';
import { MonedaCambioUpdateComponent } from './moneda-cambio-update.component';
import { MonedaCambioDeletePopupComponent } from './moneda-cambio-delete-dialog.component';
import { IMonedaCambio } from 'app/shared/model/moneda-cambio.model';

@Injectable({ providedIn: 'root' })
export class MonedaCambioResolve implements Resolve<IMonedaCambio> {
    constructor(private service: MonedaCambioService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMonedaCambio> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MonedaCambio>) => response.ok),
                map((monedaCambio: HttpResponse<MonedaCambio>) => monedaCambio.body)
            );
        }
        return of(new MonedaCambio());
    }
}

export const monedaCambioRoute: Routes = [
    {
        path: '',
        component: MonedaCambioComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.monedaCambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: MonedaCambioDetailComponent,
        resolve: {
            monedaCambio: MonedaCambioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.monedaCambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: MonedaCambioUpdateComponent,
        resolve: {
            monedaCambio: MonedaCambioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.monedaCambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: MonedaCambioUpdateComponent,
        resolve: {
            monedaCambio: MonedaCambioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.monedaCambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monedaCambioPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: MonedaCambioDeletePopupComponent,
        resolve: {
            monedaCambio: MonedaCambioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.monedaCambio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
