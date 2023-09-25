import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CabeceraInforme } from 'app/shared/model/cabecera-informe.model';
import { CabeceraInformeService } from './cabecera-informe.service';
import { CabeceraInformeComponent } from './cabecera-informe.component';
import { CabeceraInformeDetailComponent } from './cabecera-informe-detail.component';
import { CabeceraInformeUpdateComponent } from './cabecera-informe-update.component';
import { CabeceraInformeDeletePopupComponent } from './cabecera-informe-delete-dialog.component';
import { ICabeceraInforme } from 'app/shared/model/cabecera-informe.model';

@Injectable({ providedIn: 'root' })
export class CabeceraInformeResolve implements Resolve<ICabeceraInforme> {
    constructor(private service: CabeceraInformeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICabeceraInforme> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CabeceraInforme>) => response.ok),
                map((cabeceraInforme: HttpResponse<CabeceraInforme>) => cabeceraInforme.body)
            );
        }
        return of(new CabeceraInforme());
    }
}

export const cabeceraInformeRoute: Routes = [
    {
        path: '',
        component: CabeceraInformeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.cabeceraInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CabeceraInformeDetailComponent,
        resolve: {
            cabeceraInforme: CabeceraInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cabeceraInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CabeceraInformeUpdateComponent,
        resolve: {
            cabeceraInforme: CabeceraInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cabeceraInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CabeceraInformeUpdateComponent,
        resolve: {
            cabeceraInforme: CabeceraInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cabeceraInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cabeceraInformePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CabeceraInformeDeletePopupComponent,
        resolve: {
            cabeceraInforme: CabeceraInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.cabeceraInforme.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
