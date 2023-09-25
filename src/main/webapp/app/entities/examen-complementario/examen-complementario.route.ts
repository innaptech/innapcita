import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExamenComplementario } from 'app/shared/model/examen-complementario.model';
import { ExamenComplementarioService } from './examen-complementario.service';
import { ExamenComplementarioComponent } from './examen-complementario.component';
import { ExamenComplementarioDetailComponent } from './examen-complementario-detail.component';
import { ExamenComplementarioUpdateComponent } from './examen-complementario-update.component';
import { ExamenComplementarioDeletePopupComponent } from './examen-complementario-delete-dialog.component';
import { IExamenComplementario } from 'app/shared/model/examen-complementario.model';

@Injectable({ providedIn: 'root' })
export class ExamenComplementarioResolve implements Resolve<IExamenComplementario> {
    constructor(private service: ExamenComplementarioService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExamenComplementario> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExamenComplementario>) => response.ok),
                map((examenComplementario: HttpResponse<ExamenComplementario>) => examenComplementario.body)
            );
        }
        return of(new ExamenComplementario());
    }
}

export const examenComplementarioRoute: Routes = [
    {
        path: '',
        component: ExamenComplementarioComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.examenComplementario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExamenComplementarioDetailComponent,
        resolve: {
            examenComplementario: ExamenComplementarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.examenComplementario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExamenComplementarioUpdateComponent,
        resolve: {
            examenComplementario: ExamenComplementarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.examenComplementario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExamenComplementarioUpdateComponent,
        resolve: {
            examenComplementario: ExamenComplementarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.examenComplementario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const examenComplementarioPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExamenComplementarioDeletePopupComponent,
        resolve: {
            examenComplementario: ExamenComplementarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.examenComplementario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
