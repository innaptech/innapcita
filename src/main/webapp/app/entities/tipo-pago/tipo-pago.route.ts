import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoPago } from 'app/shared/model/tipo-pago.model';
import { TipoPagoService } from './tipo-pago.service';
import { TipoPagoComponent } from './tipo-pago.component';
import { TipoPagoDetailComponent } from './tipo-pago-detail.component';
import { TipoPagoUpdateComponent } from './tipo-pago-update.component';
import { TipoPagoDeletePopupComponent } from './tipo-pago-delete-dialog.component';
import { ITipoPago } from 'app/shared/model/tipo-pago.model';

@Injectable({ providedIn: 'root' })
export class TipoPagoResolve implements Resolve<ITipoPago> {
    constructor(private service: TipoPagoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoPago> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoPago>) => response.ok),
                map((tipoPago: HttpResponse<TipoPago>) => tipoPago.body)
            );
        }
        return of(new TipoPago());
    }
}

export const tipoPagoRoute: Routes = [
    {
        path: '',
        component: TipoPagoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.tipoPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TipoPagoDetailComponent,
        resolve: {
            tipoPago: TipoPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TipoPagoUpdateComponent,
        resolve: {
            tipoPago: TipoPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TipoPagoUpdateComponent,
        resolve: {
            tipoPago: TipoPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoPago.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoPagoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TipoPagoDeletePopupComponent,
        resolve: {
            tipoPago: TipoPagoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoPago.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
