import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';
import { HistoriaMedicaAdultoService } from './historia-medica-adulto.service';
import { HistoriaMedicaAdultoComponent } from './historia-medica-adulto.component';
import { HistoriaMedicaAdultoDetailComponent } from './historia-medica-adulto-detail.component';
import { HistoriaMedicaAdultoUpdateComponent } from './historia-medica-adulto-update.component';
import { HistoriaMedicaAdultoDeletePopupComponent } from './historia-medica-adulto-delete-dialog.component';
import { IHistoriaMedicaAdulto } from 'app/shared/model/historia-medica-adulto.model';

@Injectable({ providedIn: 'root' })
export class HistoriaMedicaAdultoResolve implements Resolve<IHistoriaMedicaAdulto> {
    constructor(private service: HistoriaMedicaAdultoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistoriaMedicaAdulto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HistoriaMedicaAdulto>) => response.ok),
                map((historiaMedicaAdulto: HttpResponse<HistoriaMedicaAdulto>) => historiaMedicaAdulto.body)
            );
        }
        return of(new HistoriaMedicaAdulto());
    }
}

export const historiaMedicaAdultoRoute: Routes = [
    {
        path: '',
        component: HistoriaMedicaAdultoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.historiaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HistoriaMedicaAdultoDetailComponent,
        resolve: {
            historiaMedicaAdulto: HistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HistoriaMedicaAdultoUpdateComponent,
        resolve: {
            historiaMedicaAdulto: HistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HistoriaMedicaAdultoUpdateComponent,
        resolve: {
            historiaMedicaAdulto: HistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historiaMedicaAdultoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HistoriaMedicaAdultoDeletePopupComponent,
        resolve: {
            historiaMedicaAdulto: HistoriaMedicaAdultoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.historiaMedicaAdulto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
