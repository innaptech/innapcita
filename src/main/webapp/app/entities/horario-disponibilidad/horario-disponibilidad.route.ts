import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';
import { HorarioDisponibilidadService } from './horario-disponibilidad.service';
import { HorarioDisponibilidadComponent } from './horario-disponibilidad.component';
import { HorarioDisponibilidadMedicoComponent } from './horario-disponibilidad-medico.component';
import { HorarioDisponibilidadDetailComponent } from './horario-disponibilidad-detail.component';
import { HorarioDisponibilidadUpdateComponent } from './horario-disponibilidad-update.component';
import { HorarioDisponibilidadDeletePopupComponent } from './horario-disponibilidad-delete-dialog.component';
import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';

@Injectable({ providedIn: 'root' })
export class HorarioDisponibilidadResolve implements Resolve<IHorarioDisponibilidad> {
    constructor(private service: HorarioDisponibilidadService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHorarioDisponibilidad> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HorarioDisponibilidad>) => response.ok),
                map((horarioDisponibilidad: HttpResponse<HorarioDisponibilidad>) => horarioDisponibilidad.body)
            );
        }
        return of(new HorarioDisponibilidad());
    }
}

export const horarioDisponibilidadRoute: Routes = [
    {
        path: '',
        component: HorarioDisponibilidadComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: HorarioDisponibilidadDetailComponent,
        resolve: {
            horarioDisponibilidad: HorarioDisponibilidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: HorarioDisponibilidadUpdateComponent,
        resolve: {
            horarioDisponibilidad: HorarioDisponibilidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: HorarioDisponibilidadUpdateComponent,
        resolve: {
            horarioDisponibilidad: HorarioDisponibilidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'medico',
        component: HorarioDisponibilidadMedicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const horarioDisponibilidadPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: HorarioDisponibilidadDeletePopupComponent,
        resolve: {
            horarioDisponibilidad: HorarioDisponibilidadResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.horarioDisponibilidad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
