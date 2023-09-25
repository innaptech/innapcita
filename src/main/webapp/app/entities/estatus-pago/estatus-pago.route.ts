import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EstatusPago } from 'app/shared/model/estatus-pago.model';
import { EstatusPagoService } from './estatus-pago.service';
import { EstatusPagoComponent } from './estatus-pago.component';
import { EstatusPagoDetailComponent } from './estatus-pago-detail.component';
import { EstatusPagoUpdateComponent } from './estatus-pago-update.component';
import { EstatusPagoDeletePopupComponent } from './estatus-pago-delete-dialog.component';
import { IEstatusPago } from 'app/shared/model/estatus-pago.model';

@Injectable({ providedIn: 'root' })
export class EstatusPagoResolve implements Resolve<IEstatusPago> {
    constructor(private service: EstatusPagoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEstatusPago> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EstatusPago>) => response.ok),
                map((estatusPago: HttpResponse<EstatusPago>) => estatusPago.body)
            );
        }
        return of(new EstatusPago());
    }
}

export const estatusPagoRoute: Routes = [
    {
        path: '',
        component: EstatusPagoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.estatusPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EstatusPagoDetailComponent,
        resolve: {
            estatusPago: EstatusPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EstatusPagoUpdateComponent,
        resolve: {
            estatusPago: EstatusPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EstatusPagoUpdateComponent,
        resolve: {
            estatusPago: EstatusPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const estatusPagoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EstatusPagoDeletePopupComponent,
        resolve: {
            estatusPago: EstatusPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.estatusPago.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
