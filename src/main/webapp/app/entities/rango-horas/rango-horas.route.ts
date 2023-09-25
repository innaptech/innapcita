import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RangoHoras } from 'app/shared/model/rango-horas.model';
import { RangoHorasService } from './rango-horas.service';
import { RangoHorasComponent } from './rango-horas.component';
import { RangoHorasDetailComponent } from './rango-horas-detail.component';
import { RangoHorasUpdateComponent } from './rango-horas-update.component';
import { RangoHorasDeletePopupComponent } from './rango-horas-delete-dialog.component';
import { IRangoHoras } from 'app/shared/model/rango-horas.model';

@Injectable({ providedIn: 'root' })
export class RangoHorasResolve implements Resolve<IRangoHoras> {
    constructor(private service: RangoHorasService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IRangoHoras> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RangoHoras>) => response.ok),
                map((rangoHoras: HttpResponse<RangoHoras>) => rangoHoras.body)
            );
        }
        return of(new RangoHoras());
    }
}

export const rangoHorasRoute: Routes = [
    {
        path: '',
        component: RangoHorasComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.rangoHoras.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: RangoHorasDetailComponent,
        resolve: {
            rangoHoras: RangoHorasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.rangoHoras.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: RangoHorasUpdateComponent,
        resolve: {
            rangoHoras: RangoHorasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.rangoHoras.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: RangoHorasUpdateComponent,
        resolve: {
            rangoHoras: RangoHorasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.rangoHoras.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rangoHorasPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: RangoHorasDeletePopupComponent,
        resolve: {
            rangoHoras: RangoHorasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.rangoHoras.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
