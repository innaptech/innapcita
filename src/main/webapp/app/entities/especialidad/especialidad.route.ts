import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Especialidad } from 'app/shared/model/especialidad.model';
import { EspecialidadService } from './especialidad.service';
import { EspecialidadComponent } from './especialidad.component';
import { EspecialidadDetailComponent } from './especialidad-detail.component';
import { EspecialidadUpdateComponent } from './especialidad-update.component';
import { EspecialidadDeletePopupComponent } from './especialidad-delete-dialog.component';
import { IEspecialidad } from 'app/shared/model/especialidad.model';

@Injectable({ providedIn: 'root' })
export class EspecialidadResolve implements Resolve<IEspecialidad> {
    constructor(private service: EspecialidadService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEspecialidad> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Especialidad>) => response.ok),
                map((especialidad: HttpResponse<Especialidad>) => especialidad.body)
            );
        }
        return of(new Especialidad());
    }
}

export const especialidadRoute: Routes = [
    {
        path: '',
        component: EspecialidadComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.especialidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EspecialidadDetailComponent,
        resolve: {
            especialidad: EspecialidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EspecialidadUpdateComponent,
        resolve: {
            especialidad: EspecialidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EspecialidadUpdateComponent,
        resolve: {
            especialidad: EspecialidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const especialidadPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EspecialidadDeletePopupComponent,
        resolve: {
            especialidad: EspecialidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
