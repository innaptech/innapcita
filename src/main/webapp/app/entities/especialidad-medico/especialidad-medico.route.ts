import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EspecialidadMedico } from 'app/shared/model/especialidad-medico.model';
import { EspecialidadMedicoService } from './especialidad-medico.service';
import { EspecialidadMedicoComponent } from './especialidad-medico.component';
import { EspecialidadMedicoMedicoComponent } from './especialidad-medico-medico.component';
import { EspecialidadMedicoDetailComponent } from './especialidad-medico-detail.component';
import { EspecialidadMedicoUpdateComponent } from './especialidad-medico-update.component';
import { EspecialidadMedicoDeletePopupComponent } from './especialidad-medico-delete-dialog.component';
import { IEspecialidadMedico } from 'app/shared/model/especialidad-medico.model';

@Injectable({ providedIn: 'root' })
export class EspecialidadMedicoResolve implements Resolve<IEspecialidadMedico> {
    constructor(private service: EspecialidadMedicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEspecialidadMedico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EspecialidadMedico>) => response.ok),
                map((especialidadMedico: HttpResponse<EspecialidadMedico>) => especialidadMedico.body)
            );
        }
        return of(new EspecialidadMedico());
    }
}

export const especialidadMedicoRoute: Routes = [
    {
        path: '',
        component: EspecialidadMedicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EspecialidadMedicoDetailComponent,
        resolve: {
            especialidadMedico: EspecialidadMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EspecialidadMedicoUpdateComponent,
        resolve: {
            especialidadMedico: EspecialidadMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EspecialidadMedicoUpdateComponent,
        resolve: {
            especialidadMedico: EspecialidadMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico',
        component: EspecialidadMedicoMedicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const especialidadMedicoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EspecialidadMedicoDeletePopupComponent,
        resolve: {
            especialidadMedico: EspecialidadMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.especialidadMedico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
