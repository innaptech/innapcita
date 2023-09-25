import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';
import { HistoriaPersonalNinoService } from './historia-personal-nino.service';
import { HistoriaPersonalNinoComponent } from './historia-personal-nino.component';
import { HistoriaPersonalNinoDetailComponent } from './historia-personal-nino-detail.component';
import { HistoriaPersonalNinoUpdateComponent } from './historia-personal-nino-update.component';
import { HistoriaPersonalNinoDeletePopupComponent } from './historia-personal-nino-delete-dialog.component';
import { IHistoriaPersonalNino } from 'app/shared/model/historia-personal-nino.model';

@Injectable({ providedIn: 'root' })
export class HistoriaPersonalNinoResolve implements Resolve<IHistoriaPersonalNino> {
    constructor(private service: HistoriaPersonalNinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistoriaPersonalNino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HistoriaPersonalNino>) => response.ok),
                map((historiaPersonalNino: HttpResponse<HistoriaPersonalNino>) => historiaPersonalNino.body)
            );
        }
        return of(new HistoriaPersonalNino());
    }
}

export const historiaPersonalNinoRoute: Routes = [
    {
        path: '',
        component: HistoriaPersonalNinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.historiaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HistoriaPersonalNinoDetailComponent,
        resolve: {
            historiaPersonalNino: HistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HistoriaPersonalNinoUpdateComponent,
        resolve: {
            historiaPersonalNino: HistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HistoriaPersonalNinoUpdateComponent,
        resolve: {
            historiaPersonalNino: HistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiaPersonalNinoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HistoriaPersonalNinoDeletePopupComponent,
        resolve: {
            historiaPersonalNino: HistoriaPersonalNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaPersonalNino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
