import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';
import { PieDePaginaInformeService } from './pie-de-pagina-informe.service';
import { PieDePaginaInformeComponent } from './pie-de-pagina-informe.component';
import { PieDePaginaInformeDetailComponent } from './pie-de-pagina-informe-detail.component';
import { PieDePaginaInformeUpdateComponent } from './pie-de-pagina-informe-update.component';
import { PieDePaginaInformeDeletePopupComponent } from './pie-de-pagina-informe-delete-dialog.component';
import { IPieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';

@Injectable({ providedIn: 'root' })
export class PieDePaginaInformeResolve implements Resolve<IPieDePaginaInforme> {
    constructor(private service: PieDePaginaInformeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPieDePaginaInforme> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PieDePaginaInforme>) => response.ok),
                map((pieDePaginaInforme: HttpResponse<PieDePaginaInforme>) => pieDePaginaInforme.body)
            );
        }
        return of(new PieDePaginaInforme());
    }
}

export const pieDePaginaInformeRoute: Routes = [
    {
        path: '',
        component: PieDePaginaInformeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.pieDePaginaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PieDePaginaInformeDetailComponent,
        resolve: {
            pieDePaginaInforme: PieDePaginaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.pieDePaginaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PieDePaginaInformeUpdateComponent,
        resolve: {
            pieDePaginaInforme: PieDePaginaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.pieDePaginaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PieDePaginaInformeUpdateComponent,
        resolve: {
            pieDePaginaInforme: PieDePaginaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.pieDePaginaInforme.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pieDePaginaInformePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PieDePaginaInformeDeletePopupComponent,
        resolve: {
            pieDePaginaInforme: PieDePaginaInformeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.pieDePaginaInforme.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
