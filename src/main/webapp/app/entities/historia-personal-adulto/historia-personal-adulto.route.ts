import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';
import { HistoriaPersonalAdultoService } from './historia-personal-adulto.service';
import { HistoriaPersonalAdultoComponent } from './historia-personal-adulto.component';
import { HistoriaPersonalAdultoDetailComponent } from './historia-personal-adulto-detail.component';
import { HistoriaPersonalAdultoUpdateComponent } from './historia-personal-adulto-update.component';
import { HistoriaPersonalAdultoDeletePopupComponent } from './historia-personal-adulto-delete-dialog.component';
import { IHistoriaPersonalAdulto } from 'app/shared/model/historia-personal-adulto.model';

@Injectable({ providedIn: 'root' })
export class HistoriaPersonalAdultoResolve implements Resolve<IHistoriaPersonalAdulto> {
    constructor(private service: HistoriaPersonalAdultoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistoriaPersonalAdulto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HistoriaPersonalAdulto>) => response.ok),
                map((historiaPersonalAdulto: HttpResponse<HistoriaPersonalAdulto>) => historiaPersonalAdulto.body)
            );
        }
        return of(new HistoriaPersonalAdulto());
    }
}

export const historiaPersonalAdultoRoute: Routes = [
    {
        path: '',
        component: HistoriaPersonalAdultoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.historiaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HistoriaPersonalAdultoDetailComponent,
        resolve: {
            historiaPersonalAdulto: HistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HistoriaPersonalAdultoUpdateComponent,
        resolve: {
            historiaPersonalAdulto: HistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HistoriaPersonalAdultoUpdateComponent,
        resolve: {
            historiaPersonalAdulto: HistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiaPersonalAdultoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HistoriaPersonalAdultoDeletePopupComponent,
        resolve: {
            historiaPersonalAdulto: HistoriaPersonalAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalAdulto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
