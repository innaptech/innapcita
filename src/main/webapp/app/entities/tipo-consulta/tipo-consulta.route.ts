import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { TipoConsultaService } from './tipo-consulta.service';
import { TipoConsultaComponent } from './tipo-consulta.component';
import { TipoConsultaDetailComponent } from './tipo-consulta-detail.component';
import { TipoConsultaUpdateComponent } from './tipo-consulta-update.component';
import { TipoConsultaDeletePopupComponent } from './tipo-consulta-delete-dialog.component';
import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';

@Injectable({ providedIn: 'root' })
export class TipoConsultaResolve implements Resolve<ITipoConsulta> {
    constructor(private service: TipoConsultaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITipoConsulta> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoConsulta>) => response.ok),
                map((tipoConsulta: HttpResponse<TipoConsulta>) => tipoConsulta.body)
            );
        }
        return of(new TipoConsulta());
    }
}

export const tipoConsultaRoute: Routes = [
    {
        path: '',
        component: TipoConsultaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.tipoConsulta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TipoConsultaDetailComponent,
        resolve: {
            tipoConsulta: TipoConsultaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoConsulta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TipoConsultaUpdateComponent,
        resolve: {
            tipoConsulta: TipoConsultaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoConsulta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TipoConsultaUpdateComponent,
        resolve: {
            tipoConsulta: TipoConsultaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoConsulta.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoConsultaPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TipoConsultaDeletePopupComponent,
        resolve: {
            tipoConsulta: TipoConsultaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.tipoConsulta.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
