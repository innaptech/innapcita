import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';
import { HistoriaMedicaNinoService } from './historia-medica-nino.service';
import { HistoriaMedicaNinoComponent } from './historia-medica-nino.component';
import { HistoriaMedicaNinoDetailComponent } from './historia-medica-nino-detail.component';
import { HistoriaMedicaNinoUpdateComponent } from './historia-medica-nino-update.component';
import { HistoriaMedicaNinoDeletePopupComponent } from './historia-medica-nino-delete-dialog.component';
import { IHistoriaMedicaNino } from 'app/shared/model/historia-medica-nino.model';

@Injectable({ providedIn: 'root' })
export class HistoriaMedicaNinoResolve implements Resolve<IHistoriaMedicaNino> {
    constructor(private service: HistoriaMedicaNinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistoriaMedicaNino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HistoriaMedicaNino>) => response.ok),
                map((historiaMedicaNino: HttpResponse<HistoriaMedicaNino>) => historiaMedicaNino.body)
            );
        }
        return of(new HistoriaMedicaNino());
    }
}

export const historiaMedicaNinoRoute: Routes = [
    {
        path: '',
        component: HistoriaMedicaNinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.historiaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HistoriaMedicaNinoDetailComponent,
        resolve: {
            historiaMedicaNino: HistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HistoriaMedicaNinoUpdateComponent,
        resolve: {
            historiaMedicaNino: HistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HistoriaMedicaNinoUpdateComponent,
        resolve: {
            historiaMedicaNino: HistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiaMedicaNinoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HistoriaMedicaNinoDeletePopupComponent,
        resolve: {
            historiaMedicaNino: HistoriaMedicaNinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaNino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
