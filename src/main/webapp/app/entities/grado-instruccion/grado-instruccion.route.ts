import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GradoInstruccion } from 'app/shared/model/grado-instruccion.model';
import { GradoInstruccionService } from './grado-instruccion.service';
import { GradoInstruccionComponent } from './grado-instruccion.component';
import { GradoInstruccionDetailComponent } from './grado-instruccion-detail.component';
import { GradoInstruccionUpdateComponent } from './grado-instruccion-update.component';
import { GradoInstruccionDeletePopupComponent } from './grado-instruccion-delete-dialog.component';
import { IGradoInstruccion } from 'app/shared/model/grado-instruccion.model';

@Injectable({ providedIn: 'root' })
export class GradoInstruccionResolve implements Resolve<IGradoInstruccion> {
    constructor(private service: GradoInstruccionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGradoInstruccion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GradoInstruccion>) => response.ok),
                map((gradoInstruccion: HttpResponse<GradoInstruccion>) => gradoInstruccion.body)
            );
        }
        return of(new GradoInstruccion());
    }
}

export const gradoInstruccionRoute: Routes = [
    {
        path: '',
        component: GradoInstruccionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.gradoInstruccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: GradoInstruccionDetailComponent,
        resolve: {
            gradoInstruccion: GradoInstruccionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.gradoInstruccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: GradoInstruccionUpdateComponent,
        resolve: {
            gradoInstruccion: GradoInstruccionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.gradoInstruccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: GradoInstruccionUpdateComponent,
        resolve: {
            gradoInstruccion: GradoInstruccionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.gradoInstruccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gradoInstruccionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: GradoInstruccionDeletePopupComponent,
        resolve: {
            gradoInstruccion: GradoInstruccionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.gradoInstruccion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
